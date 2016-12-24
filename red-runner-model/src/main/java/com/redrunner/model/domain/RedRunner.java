package com.redrunner.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class RedRunner {

	@Id
	@GeneratedValue
	private Long red_runner_id;

//	@Column
//	private Long streetid;

	@Column
	private Long timestamp;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="streetid")
	private StreetIntersection streetIntersection;

	public Long getRed_runner_id() {
		return red_runner_id;
	}

	public void setRed_runner_id(Long red_runner_id) {
		this.red_runner_id = red_runner_id;
	}

//	public Long getStreetid() {
//		return streetid;
//	}
//
//	public void setStreetid(Long streetid) {
//		this.streetid = streetid;
//	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public RedRunner() { // jpa only
	}

	public RedRunner( Long timestamp) {
//		this.streetid = streetid;
		this.timestamp = timestamp;
	}

	public StreetIntersection getStreetIntersection() {
		return streetIntersection;
	}

	public void setStreetIntersection(StreetIntersection streetIntersection) {
		this.streetIntersection = streetIntersection;
	}

}