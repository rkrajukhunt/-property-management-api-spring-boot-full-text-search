package com.eb.property.management.api.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import com.eb.property.management.api.persistence.Register;
import com.eb.property.management.api.persistence.dto.JwtRequestDto;

public interface IRegisterService {

	public UserDetails loadUserByUsername(String username);

	public ResponseEntity<?> createUser(Register register);

	public Register loadUserByUsernameAll(String username);

	/**
	 * @param authenticationRequest
	 * @return ResponseEntity<?> as JwtResponseDto
	 */
	public ResponseEntity<?> createAuthenticationToken(JwtRequestDto authenticationRequest);

}
