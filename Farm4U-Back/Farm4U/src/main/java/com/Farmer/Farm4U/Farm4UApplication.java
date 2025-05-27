package com.Farmer.Farm4U;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@CrossOrigin("http://localhost:5173")
public class Farm4UApplication {

	public static void main(String[] args) {
		SpringApplication.run(Farm4UApplication.class, args);
	}

}
