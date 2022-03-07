package com.zensar.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class SpringBootBankingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBankingSystemApplication.class, args);
	}

}
