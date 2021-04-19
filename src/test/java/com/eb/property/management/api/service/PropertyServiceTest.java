package com.eb.property.management.api.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.eb.property.management.api.persistence.Property;
import com.eb.property.management.api.persistence.repository.PropertyRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertyServiceTest {
	@Autowired
	private IPropertyService propertyService;
	
	@MockBean
	private PropertyRepository propertyRepository;
	
	@Test
	public void testAddProperty(){

		Property property = new Property();
		property.setPropertyId("1");
		property.setName("Shiv Aparment");
		property.setAddress("madhuram road");
		property.setPhoneNumber("9723648598");
		property.setCity("Junagadh");
		property.setState("Gujarat");
		property.setPostalCode("362001");
		property.setIsApproved(false);
		
	    Mockito.when(propertyRepository.save(property)).thenReturn(property);
	    
	    assertThat(propertyService.addProperty(property)).isEqualTo(property);
	
	}
	
	@Test
	public void testUpdateProperty(){

		Property property = new Property();
		property.setPropertyId("1");
		property.setName("Shiv Aparment");
		property.setAddress("madhuram road");
		property.setPhoneNumber("9723648598");
		property.setCity("Junagadh");
		property.setState("Gujarat");
		property.setPostalCode("362001");
		property.setIsApproved(false);

		Optional<Property> propertyOptional = Optional.of(property);
		
		Mockito.when(propertyRepository.findById("1")).thenReturn(propertyOptional);
		
		property.setName("OM Aparment");
		property.setAddress("Zanzarda road");
		property.setPhoneNumber("8780372532");
		property.setCity("Junagadh");
		property.setState("Gujarat");
		property.setPostalCode("362025");
		property.setIsApproved(false);
		
		Mockito.when(propertyRepository.save(property)).thenReturn(property);
		
		assertThat(propertyService.updateProperty("1", property)).isEqualTo(property);
	
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
		
		Mockito.when(propertyRepository.findAll()).thenReturn(propertyList);
		
		assertThat(propertyService.getPropertys()).isEqualTo(propertyList);
	}
	
	@Test
	public void testSearchProperty() throws Exception {
		
		Property property = new Property();
		property.setPropertyId("1");
		property.setName("Shiv Aparment");
		property.setAddress("madhuram road");
		property.setPhoneNumber("9723648598");
		property.setCity("Junagadh");
		property.setState("Gujarat");
		property.setPostalCode("362001");
		property.setIsApproved(false);
		
		List<Property> propertyList = new ArrayList<>();
		propertyList.add(property);
		
		Mockito.when(propertyRepository.fullTextSearch("Shiv Aparment")).thenReturn(propertyList);
	    
	    assertThat(propertyService.searchProperty("Shiv Aparment")).isEqualTo(property);
	
	}
		
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
		property.setIsApproved(false);
		
		Optional<Property> propertyOptional = Optional.of(property);
		
		Mockito.when(propertyRepository.findById("1")).thenReturn(propertyOptional);
				
		property.setIsApproved(true);
		
		Mockito.when(propertyRepository.save(property)).thenReturn(property);
		
		assertThat(propertyService.approvedProperty("1")).isEqualTo(property);
	}
}
