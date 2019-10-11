package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Create a centre configuration module.
 * Avoid restart of you application (deployment config can be set here)
 *
 * This can be secured (ex: use keys to access config module)
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigServiceApplication {

    public static void main(String[] args) {
	SpringApplication.run(ConfigServiceApplication.class, args);
    }
}
