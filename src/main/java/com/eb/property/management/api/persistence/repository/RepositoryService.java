package com.eb.property.management.api.persistence.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import lombok.Data;

@Data
@Service
@Configuration
public class RepositoryService {

	@Autowired
	private RegisterRepository registerRepository;

	@Autowired
	private PropertyRepository propertyRepository;
}
