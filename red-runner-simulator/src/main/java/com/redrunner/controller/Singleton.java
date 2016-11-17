package com.redrunner.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.redrunner.utils.Constants;

public enum Singleton {
	INSTANCE;

	protected Properties props;

	Singleton() {
	}

	public void setPath(String path) {
		try {
			this.props = initializeProps(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Gets data-access.properties
	 * 
	 * @return Properties
	 * @throws IOException
	 */
	private Properties initializeProps(String path) throws IOException {
		Properties props = new Properties();
		final InputStream inputStream = new FileInputStream(path + Constants.RESOURCES);

		props.load(inputStream);

		return props;
	}

	public String getPath() {
		return props.getProperty(Constants.RESOURCES_PATH);
	}
}
