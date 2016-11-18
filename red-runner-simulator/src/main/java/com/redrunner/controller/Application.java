package com.redrunner.controller;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {

	static Logger log = Logger.getLogger(Application.class);

	public static void main(String[] args) throws Exception {
		log.info("++++++++++STARTING SIMULATOR++++++++++++");
		log.info(args[0]);
		Singleton.INSTANCE.setPath(args[0]);
		SpringApplication.run(Application.class);
	}
}
