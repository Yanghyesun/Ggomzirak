package com.ggomzirak.dto;

import java.util.List;

import com.ggomzirak.db.entity.Shorts;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
/**
 * Shorts 조회 API ([GET] /api/v1/shorts/{id}) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("ShortsResponse")
public class ShortsDetailGetRes{
	@ApiModelProperty(name="shorts")
	Shorts shorts;
	
	@ApiModelProperty(name="글 태그")
	List<String> tags;
	
	@ApiModelProperty(name="댓글 수")
	Long comments;
	
	@ApiModelProperty(name="좋아요 여부")
	Boolean likes;
}
