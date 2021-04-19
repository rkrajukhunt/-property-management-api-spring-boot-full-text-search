package com.eb.property.management.api.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Indexed;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Indexed
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "property")
public class Property {
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", updatable = false, nullable = false)
	private String propertyId;

	@Column
	private String name;

	@Column
	private String address;

	@Column
	private String phoneNumber;

	@Column
	private String city;

	@Column
	private String state;

	@Column
	private String postalCode;

	@Column
	private Boolean isApproved;

}
