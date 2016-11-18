package com.redrunner.utils;

import com.redrunner.controller.Singleton;

public final class Constants {

	public static final String RESOURCES = "/Resources/data.properties";
	public static final String RESOURCES_PATH = "test.path";
	public static final String TRAFFIC_FILE_PATH = Singleton.INSTANCE.getPath() + "/Resources/traffic_light.txt";
	public static final String TIMESTAMP_FILE_PATH = Singleton.INSTANCE.getPath() + "/Resources/timestamp.txt";
	public static final String COORDS_FILE_PATH = Singleton.INSTANCE.getPath() + "/Resources/coordinates.txt";
	public static final String REVERSE_GEOCODE_REQUEST_SRC = Singleton.INSTANCE.getPath()
			+ "/Resources/reverseGeocodeRequest.json";

	public static final String API_KEY = "uKpKvdPt9KDeb3b6DDEVo8S5QaiS1PmW";
	public static final String SECRET = "ZYWf6F6AqxGZvWFh";

	public static final int PROBABILITY = 100;
	public static final int PROBABILITY_THRESHOLD = 35;// change to 35
	public static final String SEPARATOR = ":";

	public static final String URL = "jdbc:mysql://newhaven.c2ynl98zxc2y.us-east-1.rds.amazonaws.com:3306/";
	public static final String USERNAME = "newHaven";
	public static final String PASSWORD = "newHaven";
	public static final String DBNAME = "myDB";
	static String SQL_DRIVER = "com.mysql.jdbc.Driver";

	public static final String JSON_LATITUDE = "00000001";
	public static final String JSON_LONGITUDE = "00000002";

}
