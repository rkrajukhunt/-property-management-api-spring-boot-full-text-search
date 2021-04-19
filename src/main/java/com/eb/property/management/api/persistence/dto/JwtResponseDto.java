package com.eb.property.management.api.persistence.dto;

import java.io.Serializable;

import com.eb.property.management.api.persistence.Register;

public class JwtResponseDto implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private final Register register;

	public JwtResponseDto(String jwttoken, Register register) {
		this.jwttoken = jwttoken;
		this.register = register;
	}

	public String getToken() {
		return this.jwttoken;
	}
	
	public Register getRegister() {
		return this.register;
	}
}