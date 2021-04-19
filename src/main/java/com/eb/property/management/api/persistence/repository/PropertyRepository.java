package com.eb.property.management.api.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eb.property.management.api.persistence.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, String> {

	@Query(value = " SELECT * FROM property p WHERE MATCH(p.address, p.city, p.name, p.phone_number, p.postal_code, p.state) AGAINST(?1 IN BOOLEAN MODE)", nativeQuery = true)
	List<Property> fullTextSearch(String search);
}