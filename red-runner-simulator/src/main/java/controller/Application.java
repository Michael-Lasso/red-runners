package controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import utils.Constants;

@SpringBootApplication
@EnableScheduling
public class Application {

	public static void main(String[] args) throws Exception {
		System.out.println("++++++++++STARTING SIMULATOR++++++++++++");
		Singleton.INSTANCE.setPath(args[0]);
		SpringApplication.run(Application.class);
	}
}
