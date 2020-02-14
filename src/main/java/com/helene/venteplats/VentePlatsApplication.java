package com.helene.venteplats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class VentePlatsApplication {

	public static void main(String[] args) {
		SpringApplication.run(VentePlatsApplication.class, args);
	}

	@RequestMapping(value = "/")
	public String hello() {
		return "This is Example02!";
	}

}
