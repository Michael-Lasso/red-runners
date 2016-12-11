package com.redrunner.model.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Street_Intersection {

	@Id
	@GeneratedValue
	private Long id;

	private String uri;

	private String description;

	public Street_Intersection() { // jpa only
	}

	public Street_Intersection(String uri, String description) {
		this.uri = uri;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public String getUri() {
		return uri;
	}

	public String getDescription() {
		return description;
	}
}