package com.ey;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HostelProjectApplication {
	
	private static final Logger logger = LogManager.getLogger(HostelProjectApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(HostelProjectApplication.class, args);
		logger.info("Application started.");
	}

}
