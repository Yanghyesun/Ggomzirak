package com.ggomzirak.dto;

import com.ggomzirak.db.entity.Qna;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
/**
 * 질문글 조회 API ([GET] /api/v1/qna/{id}) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("QnaResponse")
public class QnaListGetRes{
	@ApiModelProperty(name="번호")
	Long id;
	
	@ApiModelProperty(name="질문글 제목")
	String title;
	
	@ApiModelProperty(name="질문글 내용")
	String contents;
	
	@ApiModelProperty(name="댓글수")
	Long comments;
	
	public QnaListGetRes() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QnaListGetRes(Qna qna, long count)
	{
		this.id = qna.getId();
		this.title = qna.getTitle();
		this.contents = qna.getContents();
		this.comments = count;
	}
}
