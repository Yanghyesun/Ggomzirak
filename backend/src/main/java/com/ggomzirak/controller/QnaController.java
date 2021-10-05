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
import org.springframework.web.multipart.MultipartFile;

import com.ggomzirak.db.entity.Comments;
import com.ggomzirak.db.entity.Qna;
import com.ggomzirak.dto.CommentsListGetRes;
import com.ggomzirak.dto.CommentsRegisterPostReq;
import com.ggomzirak.dto.QnaListGetRes;
import com.ggomzirak.dto.QnaRegisterPostReq;
import com.ggomzirak.dto.QnaRes;
import com.ggomzirak.service.CommentsService;
import com.ggomzirak.service.QnaService;
import com.ggomzirak.service.S3Uploader;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 질문게시판 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "질문글 API", tags = { "Qna" })
@RestController
@RequestMapping("/api/v1/qna")
public class QnaController {

	@Autowired
	QnaService qnaService;
	
	@Autowired
	CommentsService commentsService;
	
	@Autowired
	S3Uploader s3Uploader;

	@ApiOperation(value = "모든 질문글의 목록을 반환한다.", response = List.class)
	@GetMapping()
	public ResponseEntity<List<QnaListGetRes>> getQna(@RequestParam("page") int page) {
		try {
			List<QnaListGetRes> list = qnaService.getQna(page);
			return ResponseEntity.status(200).body(list);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("/register")
	@ApiOperation(value = "질문글 생성", notes = "질문글을 생성 한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<Long> register(QnaRegisterPostReq registerInfo) throws IOException{//
		try {
			if (registerInfo.getImageFiles() != null) {//이미지 없을 때

				// 사진 string으로---------------------------------
				StringBuilder imageLink = new StringBuilder();
				for (MultipartFile file : registerInfo.getImageFiles()) {
					if (file.isEmpty() == true)
						break;// 비었을때

					String imgPath = s3Uploader.upload(file, "static");
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
			
			Qna qna = qnaService.createQna(registerInfo);
			System.out.println("=============" + qna.getTitle() + " 생성===============");
			return ResponseEntity.status(200).body(qna.getId());
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "질문글 상세", notes = "질문글의 정보를 반환한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<QnaRes> getQnaInfo(@PathVariable Long id) {
		try {
			Qna qna = qnaService.getQnaById(id);

			Long comments = commentsService.getCommentsCount(qna.getId(), (long) 3);// 댓글 개수
			List<String> tagList = Arrays.asList(qna.getTags().split(";"));
			List<String> imageList = new ArrayList<>();
			if (qna.getImage().isEmpty() == false)
				imageList = Arrays.asList(qna.getImage().split(";"));

			QnaRes res = new QnaRes(qna, tagList, imageList, comments);
			return ResponseEntity.status(200).body(res);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "질문글 수정", notes = "질문글을 수정한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<String> modifyQna(@PathVariable("id") Long id,
			@RequestBody @ApiParam(value = "수정 정보", required = true) QnaRegisterPostReq registerInfo) {

		return ResponseEntity.status(200).body("");
	}

	@GetMapping("/comments/{id}")
	@ApiOperation(value = "질문글 댓글 목록", notes = "질문글의 댓글목록을 반환한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<List<CommentsListGetRes>> getQnaComments(@PathVariable("id") Long id, @RequestParam("page") int page) {
		try {
			List<CommentsListGetRes> list = commentsService.getComments(id, page,(long)3);
			return ResponseEntity.status(200).body(list);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping("/comments/register")
	@ApiOperation(value = "질문글 댓글 생성", notes = "질문글에 댓글을 생성한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<String> postQnaComments(@RequestBody CommentsRegisterPostReq  registerInfo) {
		try {
			Comments comments = commentsService.createComments(registerInfo);

			return ResponseEntity.status(200).body(comments.getId()+" 생성");
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/search")
	@ApiOperation(value = "질문글 검색", notes = "key(title, contents, tags, name)에 해당하는 word를 질문글에서 검색한 후 page에 해당하는 결과를 각 16개 씩 반환한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<List<QnaListGetRes>> getSearchQna(@RequestParam String key, @RequestParam String word, @RequestParam int page) {
		try {
			List<QnaListGetRes> list = qnaService.getSearchQna(key, word, page);

			return ResponseEntity.status(200).body(list);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}