package com.ggomzirak.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ggomzirak.db.entity.Comments;
import com.ggomzirak.db.entity.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
/**
 * 댓글 목록 조회 API ([GET] /api/v1/comments/) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("CommentsResponse")
public class CommentsListGetRes{
	@ApiModelProperty(name="내용")
	String contents;
	
	@ApiModelProperty(name="글 작성자")
	User user;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy.MM.dd HH:mm", timezone="Asia/Seoul")
	@ApiModelProperty(name="작성 시간")
	Timestamp time;

	public CommentsListGetRes() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CommentsListGetRes(Comments comments)
	{
		this.contents = comments.getContents();
		this.user = comments.getUser();
		this.time = comments.getTime();
	}
}
