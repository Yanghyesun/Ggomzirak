package com.ggomzirak.dto;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestChangeProfile {
	@ApiModelProperty(name="유저아이디")
    private String userId;
    
    @ApiModelProperty(name="영상")
	MultipartFile profile;
}