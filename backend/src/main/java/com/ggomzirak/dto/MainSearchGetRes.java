package com.ggomzirak.dto;

import java.util.List;

import com.ggomzirak.db.entity.Shorts;
import com.ggomzirak.db.entity.Tips;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Main Search Get Request")
public class MainSearchGetRes {
	
	@ApiModelProperty(name="유저 name", example="김싸피")
	List<ShortsListGetRes> shortsList;
	@ApiModelProperty(name="유저 Position", example="manager")
	List<TipsListGetRes> tipList;
	
	public static MainSearchGetRes of(List<ShortsListGetRes> shortsList, List<TipsListGetRes> tipList) {

		MainSearchGetRes res = new MainSearchGetRes();
		res.setShortsList(shortsList);
		res.setTipList(tipList);
		return res;
	}

}
