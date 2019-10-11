package br.com.bcp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
Triggering a restart

As DevTools monitors classpath resources, the only way to trigger a restart is to update the classpath.
The way in which you cause the classpath to be updated depends on the IDE that you are using. In Eclipse,
saving a modified file will cause the classpath to be updated and trigger a restart.
In IntelliJ IDEA, building the project (Build → Make Project) will have the same effect.
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
	SpringApplication.run(Application.class, args);
    }
}
