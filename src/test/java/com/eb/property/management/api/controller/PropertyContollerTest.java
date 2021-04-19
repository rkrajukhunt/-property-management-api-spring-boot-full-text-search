package com.eb.property.management.api.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.eb.property.management.api.persistence.Property;
import com.eb.property.management.api.service.IPropertyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PropertyContollerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private IPropertyService propertyService;
	
	String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcmF0aWsiLCJleHAiOjE2MTg1Nzg1NTUsImlhdCI6MTYxODU2MDU1NX0.8AzF5GR-5qreek9_nrHrBMjfptIQjOr3vR2wFL4BxmK6OPTU4ZbMt2s1aDhQmdMy3Lrk8sZiMJLr-evoflkdhg";
	
	
	@Test
	public void testAddProperty() throws Exception {
		
		Property property = new Property();
		property.setPropertyId("1");
		property.setName("Shiv Aparment");
		property.setAddress("madhuram road");
		property.setPhoneNumber("9723648598");
		property.setCity("Junagadh");
		property.setState("Gujarat");
		property.setPostalCode("362001");
		property.setIsApproved(false);
		
		String inputInJson = this.mapToJson(property);
		
		String URI = "/property/";
		
		Mockito.when(propertyService.addProperty(Mockito.any(Property.class))).thenReturn(property);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(URI)
				.accept(MediaType.APPLICATION_JSON).content(inputInJson)
				.contentType(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION,token);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		String outputInJson = response.getContentAsString();
		
		assertThat(outputInJson).isEqualTo(inputInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	public void testUpdateProperty() throws Exception {
		
		Property property = new Property();
		property.setPropertyId("1");
		property.setName("OM Aparment");
		property.setAddress("Zanzarda road");
		property.setPhoneNumber("8780372532");
		property.setCity("Junagadh");
		property.setState("Gujarat");
		property.setPostalCode("362025");
		property.setIsApproved(false);
		
		String inputInJson = this.mapToJson(property);
		
		String URI = "/property/1";
		
		Mockito.when(propertyService.updateProperty(Mockito.anyString(),Mockito.any(Property.class))).thenReturn(property);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(URI)
				.accept(MediaType.APPLICATION_JSON).content(inputInJson)
				.contentType(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION,token);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		String outputInJson = response.getContentAsString();
		
		assertThat(outputInJson).isEqualTo(inputInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	public void testGetPropertys() throws Exception {

		Property property1 = new Property();
		property1.setPropertyId("1");
		property1.setName("Shiv Aparment");
		property1.setAddress("madhuram road");
		property1.setPhoneNumber("9723648598");
		property1.setCity("Junagadh");
		property1.setState("Gujarat");
		property1.setPostalCode("362001");
		property1.setIsApproved(false);
		
		Property property2 = new Property();
		property2.setPropertyId("2");
		property2.setName("OM Aparment");
		property2.setAddress("Zanzarda road");
		property2.setPhoneNumber("8780372532");
		property2.setCity("Junagadh");
		property2.setState("Gujarat");
		property2.setPostalCode("362025");
		property2.setIsApproved(false);
		
		List<Property> propertyList = new ArrayList<>();
		propertyList.add(property1);
		propertyList.add(property2);
		
		Mockito.when(propertyService.getPropertys()).thenReturn(propertyList);
		
		String URI = "/property/";
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				URI).accept(
				MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION,token);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expectedJson = this.mapToJson(propertyList);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}		
	
//	@Test
//	public void testSearchProperty() throws Exception {
//		
//		Property property = new Property();
//		property.setPropertyId("1");
//		property.setName("Shiv Aparment");
//		property.setAddress("madhuram road");
//		property.setPhoneNumber("9723648598");
//		property.setCity("Junagadh");
//		property.setState("Gujarat");
//		property.setPostalCode("362001");
//		property.setIsApproved(false);
//		
//		String expectedJson = this.mapToJson(property);
//		
//		Mockito.when(propertyService.searchProperty((Mockito.anyString())).thenReturn(property);
//		
//		String URI = "/property/Shiv Apartment";
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
//				URI).accept(
//				MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION,token);
//
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//		String outputInJson = result.getResponse().getContentAsString();
//		assertThat(outputInJson).isEqualTo(expectedJson);
//	
//	}
	
	@Test
	public void testApprovedProperty() throws Exception {
		
		Property property = new Property();
		property.setPropertyId("1");
		property.setName("OM Aparment");
		property.setAddress("Zanzarda road");
		property.setPhoneNumber("8780372532");
		property.setCity("Junagadh");
		property.setState("Gujarat");
		property.setPostalCode("362025");
		property.setIsApproved(true);
		
		String inputInJson = this.mapToJson(property);
		
		String URI = "/property/approveProperty/1";
		
		Mockito.when(propertyService.approvedProperty(Mockito.anyString())).thenReturn(property);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(URI)
				.accept(MediaType.APPLICATION_JSON).content(inputInJson)
				.contentType(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION,token);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		String outputInJson = response.getContentAsString();
		
		assertThat(outputInJson).isEqualTo(inputInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}
