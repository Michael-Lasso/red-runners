package com.redrunner.controller;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {

	static Logger logger = Logger.getLogger(Application.class);
	
    public static void main(String[] args) throws Exception {
    	logger.info("++++++++++STARTING SIMULATOR++++++++++++");
    	Singleton.INSTANCE.setPath(args[0]);
        SpringApplication.run(Application.class);
    }
}
