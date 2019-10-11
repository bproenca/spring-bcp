package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Itś like a phone book for the Cloud (registry)
 * Answer: where service are, how many are there, etc
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServiceApplication {

    public static void main(String[] args) {
	SpringApplication.run(EurekaServiceApplication.class, args);
    }
}
