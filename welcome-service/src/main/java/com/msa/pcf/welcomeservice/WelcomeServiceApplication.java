package com.msa.pcf.welcomeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.msa.pcf.welcomeservice")
public class WelcomeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WelcomeServiceApplication.class, args);
	}
}
