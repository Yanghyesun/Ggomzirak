package com.ggomzirak.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ggomzirak.db.entity.Comments;
import com.ggomzirak.db.entity.Live;
import com.ggomzirak.dto.CommentsRegisterPostReq;
import com.ggomzirak.dto.LiveInfoPostReq;
import com.ggomzirak.dto.LiveRecordReq;
import com.ggomzirak.dto.LiveSessionReq;
import com.ggomzirak.service.LiveService;
import com.ggomzirak.service.S3Uploader;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


/**
 * Live 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "Live API", tags = { "Live" })
@RestController
@RequestMapping("/api/v1/live")
public class LiveController {

	@Autowired
	S3Uploader s3Uploader;
	
	@Autowired
	LiveService liveService;
	
	@PostMapping("")
	@ApiOperation(value = "라이브 정보 생성", notes = "라이브 상세 정보를 생성한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
		@ApiResponse(code = 404, message = "정보 없음"), @ApiResponse(code = 500, message = "서버 오류"),
		@ApiResponse(code = 409, message = "이미 라이브가 존재.")})
	public ResponseEntity<Long> createLiveInfo(LiveInfoPostReq liveInfo) throws IOException{
		System.out.println(liveInfo.toString());
		int condition = liveService.checkLive(liveInfo.getUserId());
		
		if(condition == 0) {
			String imgPath = s3Uploader.upload(liveInfo.getThumbnailFile(), "static");
			liveInfo.setThumbnail(imgPath);
			Live live = liveService.createLive(liveInfo);
			
			return ResponseEntity.status(200).body(live.getId());
		}else {
			return ResponseEntity.status(409).body(0l);
		}
	}
	
	@GetMapping("/{liveid}")
	@ApiOperation(value = "라이브 중인 세션 정보", notes = "라이브 중인 세션 정보를 가져온다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "정보 없음"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<Live> getLiveInfo(@PathVariable Long liveid) {
		try {
			Live live = liveService.getliveInfo(liveid);

			return ResponseEntity.status(200).body(live);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PutMapping("/{liveid}")
	@ApiOperation(value = "라이브 종료", notes = "라이브를 종료하려는 세션 정보에 종료 시간을 기입해준다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "정보 없음"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<String> exitLive(@PathVariable Long liveid) {
		try {
			Live live = liveService.exitLive(liveid);

			return ResponseEntity.status(200).body(String.valueOf(live.getEndtime()));
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PutMapping("/viewers")
	@ApiOperation(value = "라이브 시청자 수 반영", notes = "라이브 세션에서 시청자 수를 반영한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "정보 없음"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<String> calcViewers(@RequestBody LiveSessionReq sessionInfo) {
		try {
			Live live = liveService.calcViewers(sessionInfo);

			return ResponseEntity.status(200).body(String.valueOf(live.getViewers()));
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/{liveid}/viewers")
	@ApiOperation(value = "시청자 수 조회", notes = "해당 사용자의 라이브 시청자 수를 조회한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "정보 없음"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<String> getViewerNum(@PathVariable Long liveid) {
		try {
			int viewers = liveService.getViewerNum(liveid).getViewers();

			return ResponseEntity.status(200).body(String.valueOf(viewers));
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("")
	@ApiOperation(value = "라이브 방송 리스트", notes = "현재 라이브 중인 모든 방송을 가져온다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "정보 없음"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<List<Live>> getLiveAll() {
		try {
			List<Live> lives = liveService.getLiveAll();

			return ResponseEntity.status(200).body(lives);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping("/recording")
	@ApiOperation(value = "라이브 녹화 저장", notes = "라이브 했던 영상을 저장한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "정보 없음"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<String> saveRecord(LiveRecordReq recordInfo){
		try {
			Live live = liveService.saveRecord(recordInfo);

			return ResponseEntity.status(200).body(live.getVideo());
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/recording/{liveid}")
	@ApiOperation(value = "한 개의 라이브 녹화 정보 가져오기", notes = "라이브 ID를 통해 라이브 정보 1개를 가져온다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "정보 없음"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<String> getRecord(@PathVariable Long liveid){
		try {
			String link = liveService.getRecordByLiveId(liveid).getVideo();

			return ResponseEntity.status(200).body(link);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/recording/user/{id}")
	@ApiOperation(value = "사용자의 라이브 녹화 정보 가져오기", notes = "사용자가 저장했던 라이브 정보들을 가져온다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "정보 없음"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<List<Live>> getRecord(@PathVariable String id){
		try {
			System.out.println(id);
			List<Live> live = liveService.getRecordByUserId(id);

			return ResponseEntity.status(200).body(live);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/recording")
	@ApiOperation(value = "모든 라이브 녹화 정보 가져오기", notes = "모든 사용자의 라이브 녹화 정보들을 가져온다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "정보 없음"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<List<Live>> getRecordAll(){
		try {
			List<Live> live = liveService.getRecordAll();

			return ResponseEntity.status(200).body(live);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@DeleteMapping("/recording/{liveId}")
	@ApiOperation(value = "라이브 녹화 정보 삭제하기", notes = "라이브 녹화파일을 DB에서 삭제한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "정보 없음"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<Live> removeRecordByLiveId(@PathVariable Long liveId){
		try {
			Live live = liveService.removeRecordByLiveId(liveId);

			return ResponseEntity.status(200).body(live);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}

