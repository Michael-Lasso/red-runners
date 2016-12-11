package com.redrunner.model.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Red_Runner {

	@Id
	@GeneratedValue
	private Long red_runner_id;

	private Long streetId;

	private Long timestamp;

	public Long getRed_runner_id() {
		return red_runner_id;
	}

	public void setRed_runner_id(Long red_runner_id) {
		this.red_runner_id = red_runner_id;
	}

	public Long getStreetId() {
		return streetId;
	}

	public void setStreetId(Long streetId) {
		this.streetId = streetId;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Red_Runner() { // jpa only
	}
}