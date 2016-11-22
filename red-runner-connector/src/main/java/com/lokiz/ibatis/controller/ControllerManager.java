package com.lokiz.ibatis.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lokiz.ibatis.service.IbatisProcessor;

public class ControllerManager {

	private static final Log log = LogFactory.getLog(ControllerManager.class);

	public static void main(String[] args) {

		log.info("<--------------------Starting ControllerManager-------------------->\n");
		IbatisProcessor surveillance;
		try {
			surveillance = new RedRunnerConnector();
			surveillance.process(null);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exiting program");
			System.exit(-1);
		}
	}

}
