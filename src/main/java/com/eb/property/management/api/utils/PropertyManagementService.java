package com.eb.property.management.api.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.eb.property.management.api.persistence.repository.RepositoryService;

public abstract class PropertyManagementService {

	@Autowired
	public HttpServletRequest httpServletReq;

	@Autowired
	public RepositoryService repoServ;
	
}
