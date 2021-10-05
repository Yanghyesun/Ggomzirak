package com.ggomzirak.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel("LiveViewerRequest")
public class LiveSessionReq {
	@ApiModelProperty(name="라이브 ID", example="1")
	Long liveid;
	
	@ApiModelProperty(name="시청자 수", example="1")
	Integer viewers;
}
