package com.ggomzirak.dto;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("LiveInfoPostRequest")
public class LiveInfoPostReq {
	@ApiModelProperty(name="유저아이디", example="ssafy")
	String userId;
	
	@ApiModelProperty(name="제목", example="라이브 제목")
	String title;
	
	@ApiModelProperty(name="설명", example="라이브 설명")
	String contents;
	
	@ApiModelProperty(name="라이브 유형", example="1 : 일반, 2: 훈수 요청")
	Integer type;
	
	@ApiModelProperty(name="썸네일주소", example="썸네일",hidden=true)
	String thumbnail;
	
	@ApiModelProperty(name="썸네일")
	MultipartFile thumbnailFile;

	@Override
	public String toString() {
		return "LiveInfoPostReq [userId=" + userId + ", title=" + title + ", contents=" + contents + ", type=" + type
				+ ", thumbnail=" + thumbnail + ", thumbnailFile=" + thumbnailFile + "]";
	}
}
