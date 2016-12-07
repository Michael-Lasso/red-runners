package com.redrunner.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Bookmark {

	@Id
	@GeneratedValue
	private Long id;

	private String uri;

	private String description;

	public Bookmark() { // jpa only
	}

	public Bookmark(String uri, String description) {
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