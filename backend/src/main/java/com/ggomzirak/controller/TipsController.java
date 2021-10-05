package com.ggomzirak.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ggomzirak.db.entity.Comments;
import com.ggomzirak.db.entity.Likes;
import com.ggomzirak.db.entity.PostType;
import com.ggomzirak.db.entity.Tips;
import com.ggomzirak.db.entity.User;
import com.ggomzirak.dto.CommentsListGetRes;
import com.ggomzirak.dto.CommentsRegisterPostReq;
import com.ggomzirak.dto.TipsListGetRes;
import com.ggomzirak.dto.TipsRegisterPostReq;
import com.ggomzirak.dto.TipsRes;
import com.ggomzirak.service.CommentsService;
import com.ggomzirak.service.S3Uploader;
import com.ggomzirak.service.TipsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 유저 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "꿀팁글 API", tags = { "Tips" })
@RestController
@RequestMapping("/api/v1/tips")
public class TipsController {

	@Autowired
	TipsService tipsService;
	
	@Autowired
	CommentsService commentsService;
	
	@Autowired
	S3Uploader s3Uploader;

	@GetMapping()
	@ApiOperation(value = "모든 꿀팁글의 목록을 반환한다.", response = List.class)
	public ResponseEntity<List<TipsListGetRes>> getTips(@RequestParam("page") int page) {
		try {
			List<TipsListGetRes> list = tipsService.getTips(page);
			return ResponseEntity.status(200).body(list);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("/register")
	@ApiOperation(value = "꿀팁글 생성", notes = "꿀팁글을 생성 한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<Long> register(TipsRegisterPostReq registerInfo) throws IOException {// 리턴타입 바꿀지말지 고민
		try {
			// 썸네일 string으로---------------------------------
			String imgPath = s3Uploader.upload(registerInfo.getThumbnailFile(), "static");
			registerInfo.setThumbnail(imgPath);
//			System.out.println("[썸네일]: "+imgPath);
			
			if (registerInfo.getImageFiles() != null) {// 이미지 없을 때
				// 사진 string으로---------------------------------
				StringBuilder imageLink = new StringBuilder();
				for (int i = 0; i < registerInfo.getImageFiles().size(); i++) {
					if (registerInfo.getImageFiles().get(i).isEmpty() == true)
						break;// 비었을때

					imgPath = s3Uploader.upload(registerInfo.getImageFiles().get(i), "static");
					imageLink.append(imgPath + ";");
//				System.out.println("[썸네일]: "+imgPath);
//				System.out.println("[image]: "+imageLink);
				}
				registerInfo.setImage(imageLink + "");
			}
			
			//태그 배열로 받아와서 string으로-------------------------
			StringBuilder tagList = new StringBuilder();
			for(String str : registerInfo.getTagList())
				tagList.append(str+";");
			registerInfo.setTags(tagList+"");
			
			Tips tips = tipsService.createTips(registerInfo);
			System.out.println("============="+tips.getId()+" : " + tips.getTitle() + " 생성===============");
			return ResponseEntity.status(200).body(tips.getId());
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "꿀팁글 상세", notes = "꿀팁글의 정보를 반환한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<TipsRes> getTipsInfo(@PathVariable Long id, @RequestParam Long userId) {
		try {
			Tips tips = tipsService.getTipsById(id);
			
			//likes 정보 반환
			Likes likes = new Likes();
			likes.setPostId(id);
			likes.setUser(new User(userId));
			PostType postType = new PostType();
			postType.setId((long) 1);
			likes.setPostType(postType);
			
			Long comments = commentsService.getCommentsCount(tips.getId(),(long)1);//댓글 개수
			List<String> tagList = Arrays.asList(tips.getTags().split(";"));
			List<String> imageList = new ArrayList<>();
			if (tips.getImage().isEmpty() == false)
				imageList = Arrays.asList(tips.getImage().split(";"));
			
			TipsRes res = new TipsRes(tips,tagList,imageList,comments,tipsService.isNotDupLikes(likes));
			return ResponseEntity.status(200).body(res);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PutMapping("/likes/{id}")
	@ApiOperation(value = "좋아요 수정", notes = "졸아요를 누른 회원 중복확인 후 글의 좋아요 수를 수정한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<String> modifyTipsLikes(@PathVariable("id") Long id,
			@RequestBody @ApiParam(value = "좋아요 누른 유저 정보", required = true) User userInfo) {
		
		Likes likes = new Likes();
		likes.setPostId(id);
		likes.setUser(userInfo);
		PostType postType = new PostType();
		postType.setId((long) 1);
		likes.setPostType(postType);

		System.out.println("=============" + id + " 수정===============");
		if (tipsService.isNotDupLikes(likes)) {//좋아요를 누른적이 없다
			tipsService.modifyTipsLikes(likes);//좋아요+1, 테이블에 등록
			return ResponseEntity.status(200).body("success");
		} else {
			// 중복 코드 뭘로 보낼지 작성해야함
			return ResponseEntity.status(409).body("fail");
		}
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "꿀팁글 수정", notes = "꿀팁글을 수정한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<String> modifyTips(@PathVariable("id") Long id,
			@RequestBody @ApiParam(value = "수정 정보", required = true) TipsRegisterPostReq registerInfo) {
			
		return ResponseEntity.status(200).body("");
	}

	@GetMapping("/comments/{id}")
	@ApiOperation(value = "꿀팁글 댓글 목록", notes = "꿀팁글의 댓글목록을 반환한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<List<CommentsListGetRes>> getTipsComments(@PathVariable Long id, @RequestParam("page") int page) {
		try {
			List<CommentsListGetRes> list = commentsService.getComments(id, page, (long) 1);
			return ResponseEntity.status(200).body(list);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping("/comments/register")
	@ApiOperation(value = "꿀팁글 댓글 생성", notes = "꿀팁글에 댓글을 생성한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<String> postTipsComments(@RequestBody CommentsRegisterPostReq registerInfo) {
		try {
			Comments comments = commentsService.createComments(registerInfo);

			return ResponseEntity.status(200).body(comments.getId() + " 생성");
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/search")
	@ApiOperation(value = "꿀팁글 검색", notes = "key(title, contents, tags, name)에 해당하는 word를 꿀팁글에서 검색한 후 page에 해당하는 결과를 각 16개 씩 반환한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<List<TipsListGetRes>> getSearchTips(@RequestParam String key, @RequestParam String word, @RequestParam int page) {
		try {
			List<TipsListGetRes> list = tipsService.getSearchTips(key, word, page);

			return ResponseEntity.status(200).body(list);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
