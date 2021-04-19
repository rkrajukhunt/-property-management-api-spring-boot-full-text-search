package com.eb.property.management.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eb.property.management.api.persistence.Property;
import com.eb.property.management.api.service.IPropertyService;

@RestController
@RequestMapping(value = "/property")
public class PropertyController {

	@Autowired
	private IPropertyService propertyService;
	
	@GetMapping("/")
	public List<Property> getPropertys() {
		return propertyService.getPropertys();
	}
	
	@GetMapping("/{search}")
	public List<Property> searchProperty(@PathVariable("search") String search) {
		return propertyService.searchProperty(search);
	}
	
	@PostMapping("/")
	public Property addProperty(@RequestBody Property property) {
		return propertyService.addProperty(property);
	}

	@PutMapping("/{propertyId}")
	public Property updateProperty(@PathVariable("propertyId") String propertyId,
			@RequestBody Property property) {
		return propertyService.updateProperty(propertyId, property);
	}

	@PutMapping("/approveProperty/{propertyId}")
	public Property approvedProperty(@PathVariable("propertyId") String propertyId) {
		return propertyService.approvedProperty(propertyId);
	}
}
