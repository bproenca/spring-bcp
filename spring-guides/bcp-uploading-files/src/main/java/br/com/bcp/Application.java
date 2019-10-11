package br.com.bcp;

import br.com.bcp.storage.StorageProperties;
import br.com.bcp.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/*
To start a Spring Boot MVC application, we first need a starter; here, spring-boot-starter-thymeleaf and
spring-boot-starter-web are already added as dependencies. To upload files with Servlet containers,
you need to register a MultipartConfigElement class (which would be <multipart-config> in web.xml).
Thanks to Spring Boot, everything is auto-configured for you!

All you need to get started with this application is the following Application class.
*/
@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class Application {

    public static void main(String[] args) {
	SpringApplication.run(Application.class, args);
    }

    /*
    You also want a target folder to upload files to, so there is a Java 8 lambda used
    to create a Boot CommandLineRunner at startup which creates that folder.
     */
    @Bean CommandLineRunner init(StorageService pStorageService) {
	return args -> {
	    pStorageService.deleteAll();
	    pStorageService.init();
	};
    }
}
