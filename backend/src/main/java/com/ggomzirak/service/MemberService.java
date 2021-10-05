package com.ggomzirak.service;

import org.springframework.stereotype.Service;

import com.ggomzirak.db.entity.User;
import com.ggomzirak.dto.UserResponse;

@Service
public interface MemberService {


	UserResponse getUserInfo(String userId);

	boolean IsDupUserId(String userId);

	UserResponse updateProfile(String userId, String imgPath);

 
}
