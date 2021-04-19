package com.eb.property.management.api.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.eb.property.management.api.persistence.Property;
import com.eb.property.management.api.persistence.repository.PropertyRepository;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class PropertyRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private PropertyRepository propertyRepository;
	
	@Test
	public void testAddProperty(){
		Property property = getProperty();
		Property savedInDb = entityManager.persist(property);
		Optional<Property> getFromDb = propertyRepository.findById(savedInDb.getPropertyId());
		
		assertThat(getFromDb).isEqualTo(savedInDb);
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
		
		entityManager.persist(property);
		
		Optional<Property> getFromDb = propertyRepository.findById("1");
		
		Property property2 = new Property();
		property2.setName("OM Aparment");
		property2.setAddress("Zanzarda road");
		property2.setPhoneNumber("8780372532");
		property2.setCity("Junagadh");
		property2.setState("Gujarat");
		property2.setPostalCode("362025");
		property2.setIsApproved(false);
		entityManager.persist(property2);
		
		assertThat(property.getPropertyId()).isEqualTo(property2);			
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
				
		entityManager.persist(property1);
		entityManager.persist(property2);
		
		Iterable<Property> allPropertyFromDb = propertyRepository.findAll();
		List<Property> propertyList = new ArrayList<>();
		
		for (Property property : allPropertyFromDb) {
			propertyList.add(property);
		}
		assertThat(propertyList.size()).isEqualTo(2);
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
		
		entityManager.persist(property);
		
		List<Property> getFromDb = propertyRepository.fullTextSearch("Shiv Aparment");
		assertThat(property.getName()).isEqualTo("Shiv Aparment");
	
	}
	
	private Property getProperty() {
		Property property = new Property();
		property.setPropertyId("1");
		property.setName("Shiv Aparment");
		property.setAddress("madhuram road");
		property.setPhoneNumber("9723648598");
		property.setCity("Junagadh");
		property.setState("Gujarat");
		property.setPostalCode("362001");
		property.setIsApproved(false);
		return property;
	}
}
