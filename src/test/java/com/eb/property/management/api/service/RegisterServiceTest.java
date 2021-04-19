package com.eb.property.management.api.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.eb.property.management.api.persistence.Property;
import com.eb.property.management.api.persistence.Register;
import com.eb.property.management.api.persistence.repository.PropertyRepository;
import com.eb.property.management.api.persistence.repository.RegisterRepository;
import com.eb.property.management.api.service.IPropertyService;
import com.eb.property.management.api.service.IRegisterService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegisterServiceTest {
	@Autowired
	private IRegisterService registerService;
	
	@MockBean
	private RegisterRepository registerRepository;
	
	@Test
	public void testCreateUser(){

		Register register = new Register();
		register.setId(1);
		register.setUsername("admin");
		register.setEmail("admin@gmail.com");
		register.setPhone("9723648598");
		register.setIsActive(false);
		register.setPassword("Password");
		
	    Mockito.when(registerRepository.save(register)).thenReturn(register);
	    
	    assertThat(registerService.createUser(register)).isEqualTo(register);
	
	}	
}
