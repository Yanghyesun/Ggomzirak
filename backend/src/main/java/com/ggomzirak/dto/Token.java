package com.ggomzirak.dto;

public class Token {

	private String accessJwt;
	private String refreshJwt;
	public String getAccessJwt() {
		return accessJwt;
	}
	public void setAccessJwt(String accessJwt) {
		this.accessJwt = accessJwt;
	}
	public String getRefreshJwt() {
		return refreshJwt;
	}
	public void setRefreshJwt(String refreshJwt) {
		this.refreshJwt = refreshJwt;
	}
}
