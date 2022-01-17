package by.serhel.springwebapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

@SpringBootApplication
public class SpringWebAppApplication {
	private static final Logger logger = LogManager.getLogger();

	public static void main(String[] args) {
		SpringApplication.run(SpringWebAppApplication.class, args);
	}
}
