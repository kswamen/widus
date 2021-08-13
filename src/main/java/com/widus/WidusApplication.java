package com.widus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WidusApplication {

	public static void main(String[] args) {
		SpringApplication.run(WidusApplication.class, args);
	}

}
