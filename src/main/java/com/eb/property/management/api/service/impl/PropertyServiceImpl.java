package com.eb.property.management.api.service.impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eb.property.management.api.persistence.Property;
import com.eb.property.management.api.persistence.dto.GenericRes;
import com.eb.property.management.api.service.IPropertyService;
import com.eb.property.management.api.utils.PropertyManagementService;
import com.eb.property.management.api.utils.ResponseUtils;

@Service
public class PropertyServiceImpl extends PropertyManagementService implements IPropertyService {

	@Override
	public Property addProperty(Property property) {
		try {

			property.setIsApproved(false);
			return repoServ.getPropertyRepository().save(property);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return property;
	}

	@Override
	public Property updateProperty(String propertyId, Property property) {
		try {

			Property property1 = repoServ.getPropertyRepository().findById(propertyId)
					.orElseThrow(() -> new EntityNotFoundException("Record not found"));

			property1.setPropertyId(propertyId);
			
			return repoServ.getPropertyRepository().save(property);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return property;
	}

	@Override
	public Property approvedProperty(String propertyId) {
		try {

			Property propertyOpj = repoServ.getPropertyRepository().findById(propertyId)
					.orElseThrow(() -> new EntityNotFoundException("Record not found"));

			propertyOpj.setIsApproved(true);
			repoServ.getPropertyRepository().save(propertyOpj);

			return repoServ.getPropertyRepository().save(propertyOpj);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Property> searchProperty(String search) {
		try {

			return repoServ.getPropertyRepository().fullTextSearch(search);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Property> getPropertys() {
		try {

			return repoServ.getPropertyRepository().findAll();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
