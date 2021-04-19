package com.eb.property.management.api.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eb.property.management.api.persistence.Register;

@Repository
public interface RegisterRepository extends JpaRepository<Register, Long> {
	
	Register findByUsername(String username);

}