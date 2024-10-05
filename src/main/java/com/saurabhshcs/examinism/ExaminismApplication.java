package com.saurabhshcs.examinism;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class ExaminismApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExaminismApplication.class, args);
	}

}
