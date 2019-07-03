package com.rjs.myshows.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.rjs.myshows"})
@EntityScan("com.rjs.myshows.core.domain.entity")
@EnableJpaRepositories("com.rjs.myshows.core.repository")
public class ServerApplication {
	public ServerApplication() {
	}

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}
}
