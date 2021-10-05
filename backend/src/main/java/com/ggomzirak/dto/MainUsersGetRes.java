package com.ggomzirak.dto;

import com.ggomzirak.db.entity.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Main TopUsers Get Request")
public class MainUsersGetRes {
	
	@ApiModelProperty(name="작성")
	User user;

	int views;
	int likes;
	int posts;
	int ranking;
	
	public MainUsersGetRes() {};
	public MainUsersGetRes(User user, int views, int likes, int posts, int ranking) {
		super();
		this.user = user;
		this.views = views;
		this.likes = likes;
		this.posts = posts;
		this.ranking = ranking;
	}

	

}