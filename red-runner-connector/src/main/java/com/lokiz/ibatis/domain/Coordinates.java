package com.lokiz.ibatis.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.lokiz.ibatis.service.PersistService;

public class Coordinates implements PersistService {

	private String latitude;
	private String longitude;
	private String name;
	private String timeStamp;

	public Coordinates() {

	}

	public Coordinates(String latitude, String longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Coordinates(String latitude, String longitude, String name, String timeStamp) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.name = name;
		this.timeStamp = timeStamp;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

		String date = timeStamp == null ? null : df.format(new Date(Long.parseLong(timeStamp)));
		// TODO Auto-generated method stub
		return "-" + name + ", Milliseconds: " + timeStamp + ", Date: " + date + ", Latitude: " + latitude
				+ ", Longitude: " + longitude;
	}

	@Override
	public String getObjectData() {
		// TODO Auto-generated method stub
		return null;
	}

}
