package by.serhel.springwebapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringWebAppApplication {
	private static final Logger logger = LogManager.getLogger(SpringWebAppApplication.class.getName());

	public static void main(String[] args) {
		logger.warn("TEST");
		SpringApplication.run(SpringWebAppApplication.class, args);
	}
}
