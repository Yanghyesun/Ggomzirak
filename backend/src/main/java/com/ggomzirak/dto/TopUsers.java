package com.ggomzirak.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopUsers {
	int total;
	int likes;
	long user_id;

}
