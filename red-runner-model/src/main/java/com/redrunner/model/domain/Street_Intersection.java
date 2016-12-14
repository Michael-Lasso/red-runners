package com.redrunner.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Street_Intersection {

	@Id
	@GeneratedValue
	private Long street_intersection_id;

	@Column
	private String street_intersection;
	@Column
	private String longitude;
	@Column
	private String latitude;
	@Column
	private Long timestamp;

	public Street_Intersection() { // jpa only
	}

	public Long getStreet_intersection_id() {
		return street_intersection_id;
	}

	public void setStreet_intersection_id(Long street_intersection_id) {
		this.street_intersection_id = street_intersection_id;
	}

	public Street_Intersection(String street_intersection, String longitude, String latitude, Long timestamp) {
		this.street_intersection = street_intersection;
		this.longitude = longitude;
		this.latitude = latitude;
		this.timestamp = timestamp;
	}

	public String getStreet_intersection() {
		return street_intersection;
	}

	public void setStreet_intersection(String street_intersection) {
		this.street_intersection = street_intersection;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return street_intersection_id + "\t" + street_intersection + "\t" + longitude + "\t" + latitude;
	}

}