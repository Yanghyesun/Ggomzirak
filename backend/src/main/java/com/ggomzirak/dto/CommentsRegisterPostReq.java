package com.ggomzirak.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 꿀팁글 생성 API ([POST] /api/v1/tips) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("CommentsRegisterPostRequest")
public class CommentsRegisterPostReq {
	@ApiModelProperty(name="작성자 번호", example="1")
	Long userId;
	
	@ApiModelProperty(name="글 번호", example="1")
	Long postId;
	
	@ApiModelProperty(name="글 타입", example="1")
	Long typeId;
	
	@ApiModelProperty(name="내용", example="내용")
	String contents;
}