package com.ggomzirak.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ggomzirak.db.entity.User;
import com.ggomzirak.db.repository.UserRepository;
import com.ggomzirak.dto.RequestLoginUser;
import com.ggomzirak.dto.UserResponse;
import com.google.common.base.Optional;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserResponse getUserInfo(String id) {
		User userInfo = userRepository.findByUserId(id);
		UserResponse userRes = new UserResponse();
		userRes.setId(userInfo.getId());
		userRes.setEmail(userInfo.getEmail());
		userRes.setImage(userInfo.getImage());
		userRes.setLevel(userInfo.getLevel());
		userRes.setName(userInfo.getName());
		userRes.setUserid(userInfo.getUserId());
		userRes.setPoints(userInfo.getPoints());
		return userRes;
	}

	@Override
	public boolean IsDupUserId(String userId) {
		return userRepository.existsByUserId(userId);
	}

	@Override
	public UserResponse updateProfile(String userId, String imgPath) {
		User userInfo = userRepository.findByUserId(userId);
		userInfo.setImage(imgPath);
		User u = userRepository.save(userInfo);
		return new UserResponse().of(u);
	}


}
