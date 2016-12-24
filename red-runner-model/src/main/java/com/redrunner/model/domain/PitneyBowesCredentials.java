package com.redrunner.model.domain;

public class PitneyBowesCredentials {
	private String api_key;
	private String secret;

	public String getApi_key() {
		return api_key;
	}

	public void setApi_key(String api_key) {
		this.api_key = api_key;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public PitneyBowesCredentials(String api_key, String secret) {
		this.api_key = api_key;
		this.secret = secret;
	}

	@Override
	public String toString() {
		return api_key + "\t" + secret;
	}

}
