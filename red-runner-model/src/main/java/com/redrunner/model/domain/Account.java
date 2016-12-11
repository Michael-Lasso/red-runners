package com.redrunner.model.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Account {

	@Id
	@GeneratedValue
	private Long id;

	public Long getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	@JsonIgnore
	private String password;
	private String username;

	public Account(String name, String password) {
		this.username = name;
		this.password = password;
	}

	public Account() { // jpa only
	}
}