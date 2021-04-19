package com.eb.property.management.api.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.eb.property.management.api.persistence.Property;

public interface IPropertyService {

	/**
	 * @param property
	 * @return
	 */
	Property addProperty(Property property);

	/**
	 * @param propertyId
	 * @param property
	 * @return
	 */
	Property updateProperty(String propertyId, Property property);

	/**
	 * @param propertyId
	 * @param isApproved
	 * @return
	 */
	Property approvedProperty(String propertyId);

	/**
	 * @param search
	 * @return
	 */
	List<Property> searchProperty(String search);

	/**
	 * @return
	 */
	List<Property> getPropertys();

}
