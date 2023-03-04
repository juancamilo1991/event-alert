package com.projects.eventsapp;

import com.projects.eventsapp.security.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class EventsAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventsAppApplication.class, args);
	}

}
