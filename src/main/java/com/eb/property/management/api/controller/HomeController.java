package com.eb.property.management.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eb.property.management.api.persistence.dto.GenericRes;
import com.eb.property.management.api.utils.ResponseUtils;

@RestController
@RequestMapping("/")
public class HomeController {

	@GetMapping("/")
	public GenericRes<?> index() {
		return ResponseUtils.success("Property Management is up and running");
	}
}
