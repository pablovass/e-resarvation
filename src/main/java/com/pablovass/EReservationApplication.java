package com.pablovass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class EReservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(EReservationApplication.class,args);
	}

}
