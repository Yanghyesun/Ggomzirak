package com.ggomzirak.dto;

import java.util.List;

import com.ggomzirak.db.entity.Tips;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
/**
 * 꿀팁글 조회 API ([GET] /api/v1/tips/{id}) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("TipsResponse")
public class TipsRes{
	@ApiModelProperty(name="tips")
	Tips tips;
	
	@ApiModelProperty(name="글 태그")
	List<String> tags;
	
	@ApiModelProperty(name="사진")
	List<String> images;

	@ApiModelProperty(name="댓글 수")
	Long comments;
	
	@ApiModelProperty(name="좋아요 여부")
	Boolean likes;

	public TipsRes() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public TipsRes(Tips tips, List<String> tags, List<String> images,Long comments, Boolean likes)
	{
		this.tips = tips;
		this.tags = tags;
		this.images = images;
		this.comments=comments;
		this.likes = likes;
	}
}
