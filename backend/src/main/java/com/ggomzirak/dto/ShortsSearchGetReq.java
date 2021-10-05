package com.ggomzirak.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Shorts Search Get Request")
public class ShortsSearchGetReq {
	@ApiModelProperty(name="key", example="title")
	String key;
	@ApiModelProperty(name="word", example="bugs")
	String word;
	@ApiModelProperty(name="page", example="1")
	int page;
	
	public ShortsSearchGetReq() {
		
	}
	
	public ShortsSearchGetReq(String key, String word, int page) {
		super();
		this.key = key;
		this.word = word;
		this.page = page;
	}

}