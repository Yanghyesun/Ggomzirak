package com.ggomzirak.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 꿀팁글 생성 API ([POST] /api/v1/tips) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("ShortsRegisterPostRequest")
public class ShortsRegisterPostReq {
	@ApiModelProperty(name="유저아이디", example="1")
	Long userId;
	
	@ApiModelProperty(name="제목", example="ssafy_web")
	String title;
	
	@ApiModelProperty(name="내용", example="ssafy_web")
	String contents;

	@ApiModelProperty(name="썸네일주소", example="ssafy_web",hidden=true)
	String thumbnail;
	
	@ApiModelProperty(name="태그 string", example="ssafy_web",hidden=true)
	String tags;
	
	@ApiModelProperty(name="영상주소", example="ssafy_web",hidden=true)
	String video;
	
	@ApiModelProperty(name="태그")
	List<String> tagList;
	
	@ApiModelProperty(name="영상")
	MultipartFile videoFile;
	
	@ApiModelProperty(name="썸네일")
	MultipartFile thumbnailFile;
}