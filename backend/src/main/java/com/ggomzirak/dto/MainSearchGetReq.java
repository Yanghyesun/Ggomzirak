package com.ggomzirak.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Main Search Get Request")
public class MainSearchGetReq {
	@ApiModelProperty(name="keyword", example="tips")
	String keyword;
	@ApiModelProperty(name="page", example="1")
	int page;
	
	public MainSearchGetReq() {
		
	}
	
	public MainSearchGetReq(String keyword, int page) {
		super();
		this.keyword = keyword;
		this.page = page;
	}

}