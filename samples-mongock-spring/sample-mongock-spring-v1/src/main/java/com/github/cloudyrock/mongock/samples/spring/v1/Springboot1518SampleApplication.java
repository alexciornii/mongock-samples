package com.github.cloudyrock.mongock.samples.spring.v1;

import com.github.cloudyrock.mongock.Mongock;
import com.github.cloudyrock.mongock.SpringMongockBuilder;
import com.github.cloudyrock.mongock.samples.spring.v1.changests.ClientChangeLog;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
public class Springboot1518SampleApplication {

	private static final String MONGO_URI = "mongodb://127.0.0.1:27017";
	private static final String SPRING_BOOT_MONGO_DB = "mongock_db_springboot_1_x_x";
	private static final String SPRING_MONGO_DB = "mongock_db_spring_5_x_x";

	public static void main(String[] args) {
		SpringApplication.run(Springboot1518SampleApplication.class, args);
	}

	@Bean
	ClientController clientController() {
		return new ClientController();
	}

	@Bean("mongock-spring")
	public Mongock mongock() {
		MongoClient mongoclient = new MongoClient(new MongoClientURI(MONGO_URI));
		return new SpringMongockBuilder(mongoclient, SPRING_MONGO_DB, ClientChangeLog.class.getPackage().getName())
				.setLockQuickConfig()
				.build();
	}

	@Bean("mongock-spring-boot")
	public Mongock mongockSpringBoot() {
		MongoClient mongoclient = new MongoClient(new MongoClientURI(MONGO_URI));
		return new SpringMongockBuilder(mongoclient, SPRING_BOOT_MONGO_DB, ClientChangeLog.class.getPackage().getName())
				.setLockQuickConfig()
				.build();
	}
}

