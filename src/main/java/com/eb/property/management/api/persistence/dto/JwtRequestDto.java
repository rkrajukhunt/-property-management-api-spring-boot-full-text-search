package com.eb.property.management.api.persistence.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequestDto implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;

	private String username;
	private String password;
	
}
