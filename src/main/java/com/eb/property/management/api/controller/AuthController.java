package com.eb.property.management.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eb.property.management.api.persistence.Register;
import com.eb.property.management.api.persistence.dto.JwtRequestDto;
import com.eb.property.management.api.service.IRegisterService;

@RestController
@CrossOrigin
public class AuthController {

	@Autowired
	private IRegisterService registerService;

	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequestDto authenticationRequest) {
		return registerService.createAuthenticationToken(authenticationRequest);
	}

	@PostMapping("/register")
	public ResponseEntity<?> createUser(@RequestBody Register register) throws Exception {
		return registerService.createUser(register);
	}
}
