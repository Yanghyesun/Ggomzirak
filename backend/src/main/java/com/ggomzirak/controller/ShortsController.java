package com.ggomzirak.controller;

import java.io.IOException;
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
import com.ggomzirak.db.entity.Shorts;
import com.ggomzirak.db.entity.User;
import com.ggomzirak.dto.CommentsListGetRes;
import com.ggomzirak.dto.CommentsRegisterPostReq;
import com.ggomzirak.dto.ShortsDetailGetRes;
import com.ggomzirak.dto.ShortsListGetRes;
import com.ggomzirak.dto.ShortsRegisterPostReq;
import com.ggomzirak.dto.ShortsSearchGetReq;
import com.ggomzirak.service.CommentsService;
import com.ggomzirak.service.S3Uploader;
import com.ggomzirak.service.ShortsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * shorts 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "Shorts API", tags = { "Shorts" })
@RestController
@RequestMapping("/api/v1/shorts")
public class ShortsController {

	@Autowired
	ShortsService shortsService;

	@Autowired
	S3Uploader s3Uploader;
	
	@Autowired
	CommentsService commentsService;

	@ApiOperation(value = "모든 shorts글의 목록을 반환한다.", response = List.class)
	@GetMapping()
	public ResponseEntity<List<ShortsListGetRes>> getShorts(@RequestParam("page") int page) {
		List<ShortsListGetRes> shorts = shortsService.getShorts(page);
		return ResponseEntity.status(200).body(shorts);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "shorts 상세", notes = "shorts의 정보를 반환한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"), @ApiResponse(code = 500, message = "서버 오류") })
//	public ResponseEntity<Shorts> getShortsInfo(@PathVariable Long id) {
	public ResponseEntity<ShortsDetailGetRes> getShortsInfo(@PathVariable Long id, @RequestParam Long userId) {
		
		//shorts객체 반환
		Shorts shorts = shortsService.getShortsById(id);
		
		//likes 정보 반환
		Likes likes = new Likes();
		likes.setPostId(id);
		likes.setUser(new User(userId));
		PostType postType = new PostType();
		postType.setId((long) 2);
		likes.setPostType(postType);

		ShortsDetailGetRes res = new ShortsDetailGetRes();
		res.setShorts(shorts);
		res.setLikes(shortsService.isNotDupLikes(likes));
		//댓글 수 반환
		res.setComments(commentsService.getCommentsCount(shorts.getId(),(long)2));
		//조회수 증가
		shortsService.addViewCount(shorts.getId());
		
		List<String> tagList = Arrays.asList(shorts.getTags().split(";"));
		res.setTags(tagList);
		
		System.out.println("=============" + shorts.getTitle() + " 반환===============");
		return ResponseEntity.status(200).body(res);
	}

	@PutMapping("/likes/{id}")
	@ApiOperation(value = "좋아요 수정", notes = "졸아요를 누른 회원 중복확인 후 shorts의 좋아요 수를 수정한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<String> modifyShortsLikes(@PathVariable("id") Long id,
			@RequestBody @ApiParam(value = "수정 정보", required = true) User userInfo) {

		// 중복확인 안함
		System.out.println("=============" + id + " 수정===============");

		Likes likes = new Likes();
		likes.setPostId(id);
		likes.setUser(userInfo);
		PostType postType = new PostType();
		postType.setId((long) 2);
		likes.setPostType(postType);
		
		if (shortsService.isNotDupLikes(likes)) {
			shortsService.modifyShortsLikes(likes);
			return ResponseEntity.status(200).body("success");
		} else {
			// 중복 코드 뭘로 보낼지 작성해야함
			return ResponseEntity.status(409).body("fail");
		}
	}

	@GetMapping("/search")
	@ApiOperation(value = "keyword 검색 결과", notes = "key에 해당하는 word를 shorts에서 검색한 후 page에 해당하는 결과를 각 16개 씩 반환")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<List<ShortsListGetRes>> getCategories(@RequestParam String key, @RequestParam String word,
			@RequestParam int page) {
		ShortsSearchGetReq req = new ShortsSearchGetReq(key, word, page);
		List<ShortsListGetRes> res = shortsService.getSearchResult(req);
		return new ResponseEntity<List<ShortsListGetRes>>(res, HttpStatus.OK);
	}

	@PostMapping("/register")
	@ApiOperation(value = "shorts 생성", notes = "shorts 생성 한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<Long> upload(ShortsRegisterPostReq registerInfo) throws IOException {
		System.out.println(registerInfo.getTitle());
		String imgPath = s3Uploader.upload(registerInfo.getVideoFile(), "static");
		registerInfo.setVideo(imgPath);
		
		imgPath = s3Uploader.upload(registerInfo.getThumbnailFile(), "static");
		registerInfo.setThumbnail(imgPath);
		
		//태그 배열로 받아와서 string으로-------------------------
		StringBuilder tagList = new StringBuilder();
		for(String str : registerInfo.getTagList())
			tagList.append(str+";");
		registerInfo.setTags(tagList+"");
		
		Shorts Shorts = shortsService.createShorts(registerInfo);
		System.out.println("=============" + Shorts.getTitle() + " 생성===============");
		return ResponseEntity.status(200).body(Shorts.getId());
	}
	
	@GetMapping("/comments/{id}")
	@ApiOperation(value = "shorts 댓글 목록", notes = "shorts의 댓글목록을 반환한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<List<CommentsListGetRes>> getShortsComments(@PathVariable Long id, @RequestParam("page") int page) {
		try {
			List<CommentsListGetRes> list = commentsService.getComments(id, page, (long) 2);
			return ResponseEntity.status(200).body(list);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PutMapping("/comments/register")
	@ApiOperation(value = "shorts 댓글 생성", notes = "shorts에 댓글을 생성한다.")
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
}
