package com.huihui.aligo.dto;

import lombok.Data;

@Data
public class VerifyCodeResponse {
	
	private String code;

	public VerifyCodeResponse( String code ) {
		this.code = code;
	}
	public VerifyCodeResponse( ) {
	}

}