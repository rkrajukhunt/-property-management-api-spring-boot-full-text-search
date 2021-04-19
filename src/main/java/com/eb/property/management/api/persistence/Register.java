package com.eb.property.management.api.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "register")
public class Register {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false, unique = true)
	@NotNull(message = "Username is Required")
	private String username;

	@Column(nullable = false, unique = true)
	@NotNull(message = "Email is Required")
	private String email;

	@Column
	private String phone;

	@Column
	private Boolean isActive;

	@Column(nullable = false)
	private String password;

}
