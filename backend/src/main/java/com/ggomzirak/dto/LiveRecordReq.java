package com.ggomzirak.dto;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("LiveRecordRequest")
public class LiveRecordReq {
	
	@ApiModelProperty(name="라이브 ID", example="1")
	Long liveid;
	
	@ApiModelProperty(name="녹화파일 주소", example="녹화본")
	String record;
}
