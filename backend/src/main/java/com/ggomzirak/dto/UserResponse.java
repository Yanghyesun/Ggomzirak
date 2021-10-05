package com.ggomzirak.dto;

import javax.persistence.Column;

import com.ggomzirak.db.entity.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("User wirhout pwd,token")
public class UserResponse {
	@ApiModelProperty(name="id", example="id")
	Long id;
	@ApiModelProperty(name="userId", example="userId")
	String userid;
	@ApiModelProperty(name="name", example="name")
	String name;
	@ApiModelProperty(name="email", example="email")
	String email;
	@ApiModelProperty(name="image", example="image")
	String image;
	@ApiModelProperty(name="level", example="level")
	String level;
    @ApiModelProperty(name="points", example="points")
    Integer points;
    
	public UserResponse of(User userInfo) {
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
}

