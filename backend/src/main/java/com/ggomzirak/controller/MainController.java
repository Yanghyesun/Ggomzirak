package com.ggomzirak.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ggomzirak.db.entity.Live;
import com.ggomzirak.db.entity.Shorts;
import com.ggomzirak.dto.MainUsersGetRes;
import com.ggomzirak.dto.SessionUser;
import com.ggomzirak.dto.TipsListGetRes;
import com.ggomzirak.service.MainService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/v1/main")
@Api(value = "ggomzirak")
public class MainController {
	
	private HttpSession httpSession;
	
	@Autowired
	private MainService service;
	
	@ApiOperation(value = "추천 shorts 리스트 (4개)", notes = "추천하는 shorts 리스트 4개 반환", response = List.class)
	@GetMapping("/shorts")
	public ResponseEntity<List<Shorts>> topShorts() {
		try {
			List<Shorts> list = service.getTopShorts();
			return new ResponseEntity<List<Shorts>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value = "Top User 리스트 (순서대로 3명)", notes = "이주의 추천 유저 3명을 높은 순으로 반환", response = List.class)
	@GetMapping("/users")
	public ResponseEntity<List<MainUsersGetRes>> topUsers() {
		try {
			List<MainUsersGetRes> list = service.getCalcTopUsers();
			return new ResponseEntity<List<MainUsersGetRes>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value = "베스트 꿀팁 리스트 (4개)", notes = "이 주의 베스트 꿀팁 4개 반환", response = List.class)
	@GetMapping("/tips")
	public ResponseEntity<List<TipsListGetRes>> topTips() {
		try {
			List<TipsListGetRes> list = service.getTopTips();

			return new ResponseEntity<List<TipsListGetRes>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
	
	@ApiOperation(value = "베스트 라이브 리스트 (4개)", notes = "현재 베스트 라이브 4개 반환. 라이브가 4개가 안될 시, 최대 시청인원이 높았던 녹화 라이브를 전달", response = List.class)
	@GetMapping("/live")
	public ResponseEntity<List<Live>> topLive() {
		try {
			List<Live> list = service.getTopLive();

			return new ResponseEntity<List<Live>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
