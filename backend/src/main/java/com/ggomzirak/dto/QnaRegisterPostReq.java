package com.ggomzirak.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 질문글 생성 API ([POST] /api/v1/tips) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("QnaRegisterPostRequest")
public class QnaRegisterPostReq {
	@ApiModelProperty(name="작성자 번호", example="1")
	Long userId;
	
	@ApiModelProperty(name="제목", example="제목")
	String title;
	
	@ApiModelProperty(name="내용", example="내용")
	String contents;
	
	@ApiModelProperty(name="사진 주소", example=";사진;사진;사진",hidden=true)
	String image="";
	
	@ApiModelProperty(name="태그 string", example=";태그;태그;태그",hidden=true)
	String tags;
	
	@ApiModelProperty(name="태그")
	List<String> tagList;
	
	@ApiModelProperty(name="사진")
	List<MultipartFile> imageFiles;
}