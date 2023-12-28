package com.github.ahisyfa.saad.saad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SaadApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaadApplication.class, args);
	}

}
