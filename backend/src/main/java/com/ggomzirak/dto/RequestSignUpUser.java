package com.ggomzirak.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Main Search Get Request")
public class RequestSignUpUser {
	@ApiModelProperty(name="userId", example="userId")
	String userid;
	@ApiModelProperty(name="password", example="password")
	String password;
	@ApiModelProperty(name="userId", example="userId")
	String name;
	@ApiModelProperty(name="userId", example="userId")
	String email;
	@ApiModelProperty(name="gender", example="0 = girl, 1 = boy")
	int gender;
}
