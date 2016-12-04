package com.redrunner.rest.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Coordinates {
	private String latitude;
	private String longitude;
	private String street_name;
	private String timeStamp;

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public String getStreet_name() {
		return street_name;
	}

	public void setStreet_name(String name) {
		this.street_name = name;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		String date = timeStamp == null || timeStamp.isEmpty() ? null : df.format(new Date(Long.parseLong(timeStamp)));
		// TODO Auto-generated method stub
		return "-" + street_name + ", Milliseconds: " + timeStamp + ", Date: " + date + ", Latitude: " + latitude
				+ ", Longitude: " + longitude;
	}

}
