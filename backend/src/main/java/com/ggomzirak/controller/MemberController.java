package com.ggomzirak.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ggomzirak.db.entity.User;

import javax.servlet.http.Cookie;
import com.ggomzirak.dto.RequestSignUpUser;
import com.ggomzirak.dto.ShortsListGetRes;
import com.ggomzirak.dto.ShortsRegisterPostReq;
import com.ggomzirak.dto.TipsListGetRes;
import com.ggomzirak.dto.MainSearchGetRes;
import com.ggomzirak.dto.QnaListGetRes;
import com.ggomzirak.dto.RequestChangeProfile;
import com.ggomzirak.dto.RequestLoginUser;
import com.ggomzirak.dto.Token;
import com.ggomzirak.dto.UserResponse;
import com.ggomzirak.service.AuthService;
import com.ggomzirak.service.JWTService;
import com.ggomzirak.service.MemberService;
import com.ggomzirak.service.QnaService;
//import com.ggomzirak.service.MemberService;
import com.ggomzirak.service.RedisUtil;
import com.ggomzirak.service.S3Uploader;
import com.ggomzirak.service.ShortsService;
import com.ggomzirak.service.TipsService;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/v1/user")
public class MemberController {

    @Autowired
    private AuthService authService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private ShortsService shortsService;
    @Autowired
    private TipsService tipsService;
    @Autowired
    private QnaService qnaService;
    @Autowired
	S3Uploader s3Uploader;

	
	@Autowired
	private JWTService jwtService;


    @PostMapping("/reg")
    public ResponseEntity<String> signUpUser(@RequestBody RequestSignUpUser user){
    	System.out.println(user.getUserid());
        try{
            authService.signUpUser(user);
            return ResponseEntity.status(200).body("성공");
        }
        catch(Exception e){
        	return ResponseEntity.status(204).body(e.toString());
        }
    }
    
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody RequestLoginUser user,
//                          HttpServletRequest req,
//                          HttpServletResponse res) {
//        try {
//            final Member member = authService.loginUser(user.getUsername(), user.getPassword());
//            final String token = jwtUtil.generateToken(member);
//            final String refreshJwt = jwtUtil.generateRefreshToken(member);
//            Cookie accessToken = cookieUtil.createCookie(JwtUtil.ACCESS_TOKEN_NAME, token);
//            Cookie refreshToken = cookieUtil.createCookie(JwtUtil.REFRESH_TOKEN_NAME, refreshJwt);
//            System.out.println("여기까진 오니");
//            redisUtil.setDataExpire(refreshJwt, member.getUsername(), JwtUtil.REFRESH_TOKEN_VALIDATION_SECOND);
//            res.addCookie(accessToken);
//            res.addCookie(refreshToken);
////            return new Response("success", "로그인에 성공했습니다.", token);
//            return ResponseEntity.status(200).body(token);
//        } catch (Exception e) {
//        	return ResponseEntity.status(204).body(e.toString());
//        }
//    }
    
    @ApiOperation(value = "로그인", notes = "로그인 합니다.")
	@PostMapping("/login")
	public ResponseEntity<Map<String,Object>> memberLogin(@RequestBody RequestLoginUser loginreq , HttpServletResponse res) throws Exception{
		Map<String,Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		System.out.println(loginreq.getUserid());
		try {
			
			User loginUser = authService.loginUser(loginreq.getUserid(), loginreq.getPassword());
			Token token = jwtService.create(loginUser);
			res.setHeader("jwt-auth-access-token", token.getAccessJwt());
			res.setHeader("jwt-auth-refresh-token", token.getRefreshJwt());
			resultMap.put("auth_token", token);
			
			resultMap.put("status", true);
			status = HttpStatus.ACCEPTED;
		}catch(RuntimeException e) {
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String,Object>>(resultMap, status);
	}
    
    @GetMapping("/detail/{userId}")
    public ResponseEntity<UserResponse> getUserInfo(@PathVariable String userId){
    	try {
			UserResponse userRes = memberService.getUserInfo(userId);
			return ResponseEntity.status(200).body(userRes);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
    }
    
    @GetMapping("/shorts/{userId}")
    public ResponseEntity<List<ShortsListGetRes>> getUserShorts(@PathVariable Long userId){
    	try {
			List<ShortsListGetRes> list = shortsService.getUserShorts(userId);
			return ResponseEntity.status(200).body(list);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
    }
    
    @GetMapping("/tips/{userId}")
    public ResponseEntity<List<TipsListGetRes>> getUserTips(@PathVariable Long userId){
    	try {
			List<TipsListGetRes> list = tipsService.getUserTips(userId);
			return ResponseEntity.status(200).body(list);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
    }
    
    @GetMapping("/qna/{userId}")
    public ResponseEntity<List<QnaListGetRes>> getUserQna(@PathVariable Long userId){
    	try {
			List<QnaListGetRes> list = qnaService.getUserQna(userId);
			return ResponseEntity.status(200).body(list);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
    }
    
    @GetMapping("/likes/{userId}")
    public ResponseEntity<MainSearchGetRes> getUserLiked(@PathVariable Long userId){
    	try {
			List<ShortsListGetRes> slist = shortsService.getUserLiked(userId);
			List<TipsListGetRes> tlist = tipsService.getUserLiked(userId);
			MainSearchGetRes res = new MainSearchGetRes();
			res.setShortsList(slist);
			res.setTipList(tlist);
			return ResponseEntity.status(200).body(res);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
    }
    
    @GetMapping("/check/{userId}")
    public ResponseEntity<String> IsDuplicateUserId(@PathVariable String userId) {
    	try {
    		if(!memberService.IsDupUserId(userId))
    			return ResponseEntity.status(200).body("success");
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
    	return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
    
    @PutMapping("/changepwd")
    public ResponseEntity<String> updatePwd(@RequestBody RequestLoginUser loginreq) {
    	try {
    		User loginUser = authService.updatePwd(loginreq.getUserid(), loginreq.getPassword());
    		return ResponseEntity.status(200).body("success");
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
    }

    @PutMapping("/changeprofile")
    public ResponseEntity<UserResponse> updateProfile(@RequestBody RequestChangeProfile profileInfo) {
    	try {
    		String imgPath = s3Uploader.upload(profileInfo.getProfile(), "static");
    		UserResponse res = memberService.updateProfile(profileInfo.getUserId(), imgPath);
    		return ResponseEntity.status(200).body(res);
    	} catch (Exception e) {
    		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    	}
    }

    //    @GetMapping("/me")
//    public ResponseEntity<MemberResponseDto> getMyMemberInfo() {
//        return ResponseEntity.ok(memberService.getMyInfo());
//    }
//
}
