package com.ggomzirak.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 태그 생성 API ([POST] /api/v1/tag) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("TagRegisterPostRequest")
public class TagRegisterPostReq {
	@ApiModelProperty(name="제목", example="제목")
	String title;
	
	@ApiModelProperty(name="내용", example="내용")
	String contents;
}