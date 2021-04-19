package com.eb.property.management.api.service.impl;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eb.property.management.api.config.JwtTokenUtil;
import com.eb.property.management.api.persistence.Register;
import com.eb.property.management.api.persistence.dto.GenericRes;
import com.eb.property.management.api.persistence.dto.JwtRequestDto;
import com.eb.property.management.api.persistence.dto.JwtResponseDto;
import com.eb.property.management.api.service.IRegisterService;
import com.eb.property.management.api.utils.PropertyManagementService;
import com.eb.property.management.api.utils.ResponseUtils;

@Service
public class RegisterServiceImpl extends PropertyManagementService implements IRegisterService, UserDetailsService {

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Register user = (Register) repoServ.getRegisterRepository().findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}

	@Override
	public ResponseEntity<?> createUser(@Valid Register register) {
		try {
			register.setPassword(bcryptEncoder.encode(register.getPassword()));
			return new ResponseEntity<GenericRes<?>>(
					ResponseUtils.success(repoServ.getRegisterRepository().save(register)), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<GenericRes<?>>(ResponseUtils.error(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public Register loadUserByUsernameAll(String username) {
		Register register = (Register) repoServ.getRegisterRepository().findByUsername(username);
		if (register == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return register;
	}

	@Override
	public ResponseEntity<?> createAuthenticationToken(JwtRequestDto authenticationRequest) {

		try {
			authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

			final UserDetails userDetails = loadUserByUsername(authenticationRequest.getUsername());

			final String token = jwtTokenUtil.generateToken(userDetails);

			Register register = loadUserByUsernameAll(authenticationRequest.getUsername());

			return new ResponseEntity<GenericRes<?>>(
					ResponseUtils.success(new JwtResponseDto(token, register), "Login successfully"), HttpStatus.OK);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<GenericRes<?>>(ResponseUtils.error(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
