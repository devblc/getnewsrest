package com.bluet.bring.getnewsrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.bluet.bring.getnewsrest.auth.repo.UserRepository;

@SpringBootApplication(scanBasePackages = "com.bluet.bring.getnewsrest")
@EnableMongoRepositories(basePackages = {"com.bluet.bring.getnewsrest.repo", 
		"com.bluet.bring.getnewsrest.auth.repo", "com.bluet.bring.getnewsrest.auth.service"})
public class GetnewsRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetnewsRestApplication.class, args);
	}

}
