package com.ggomzirak.dto;

import java.util.Arrays;
import java.util.List;

import com.ggomzirak.db.entity.Shorts;
import com.ggomzirak.db.entity.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
/**
 * 꿀팁글 목록 조회 API ([GET] /api/v1/shorts/) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("ShortsResponse")
public class ShortsListGetRes{
	@ApiModelProperty(name="번호")
	Long id;
	
	@ApiModelProperty(name="대표이미지")
	String thumbnail;
	
	@ApiModelProperty(name="글 제목")
	String title;
	
	@ApiModelProperty(name="글 내용")
	String contents;
	
	@ApiModelProperty(name="글 작성자")
	UserResponse user;
	
	@ApiModelProperty(name="글 좋아요 수")
	Integer likes;
	
	@ApiModelProperty(name="글 태그")
	List<String> tags;
	
	@ApiModelProperty(name="댓글 수")
	Long comments;

	@ApiModelProperty(name="조회수")
	Long views;
	
	public ShortsListGetRes(Shorts shorts, long count)
	{
		this.id = shorts.getId();
		this.thumbnail = shorts.getThumbnail();
		this.title = shorts.getTitle();
		this.contents = shorts.getContents();
		this.user = new UserResponse().of(shorts.getUser());
		this.likes = shorts.getLikes();
		this.tags =  Arrays.asList(shorts.getTags().split(";"));
		this.comments = count;
		this.views = shorts.getViews();
	}

}
