package com.helene.venteplats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class VentePlatsApplication {

	public static void main(String[] args) {
		SpringApplication.run(VentePlatsApplication.class, args);
	}

}
