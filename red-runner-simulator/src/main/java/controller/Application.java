package controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {

    public static void main(String[] args) throws Exception {
    	System.out.println("++++++++++STARTING SIMULATOR++++++++++++");
        SpringApplication.run(Application.class);
    }
}
