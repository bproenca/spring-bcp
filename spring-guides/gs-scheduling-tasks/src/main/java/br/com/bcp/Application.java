package br.com.bcp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/*
@EnableScheduling ensures that a background task executor is created. Without it, nothing gets scheduled.
 */
@SpringBootApplication
@EnableScheduling
public class Application {

    public static void main(String[] args) {
	SpringApplication.run(Application.class, args);
    }
}
