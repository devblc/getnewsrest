package com.bluet.bring.getnewsrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@EnableMongoRepositories(basePackages = {
		"com.bluet.bring.getnewsrest.repo", 
		"com.bluet.bring.getnewsrest.auth.repo", 
		"com.bluet.bring.getnewsrest.auth.service",
		"com.bluet.bring.getnewsrest.providers.repo", 
		"com.bluet.bring.getnewsrest.blogbuilder.repo" 
		})
public class GetnewsRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetnewsRestApplication.class, args);
	}

}
