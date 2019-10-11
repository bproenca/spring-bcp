package br.com.bcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/*
Fetch a REST resource

With project setup complete, you can create a simple application that consumes a RESTful service.

A RESTful service has been stood up at http://gturnquist-quoters.cfapps.io/api/random.
It randomly fetches quotes about Spring Boot and returns them as a JSON document.

If you request that URL through your web browser or curl, you’ll receive a JSON document that looks something like this:
 */

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    /*
    So far (commented method) we haven’t used Spring Boot in our application, but there are some advantages in doing so,
    and it isn’t hard to do. One of the advantages is that we might want to let Spring Boot manage the message converters
    in the RestTemplate, so that customizations are easy to add declaratively. To do that we use @SpringBootApplication
    on the main class and convert the main method to start it up, like in any Spring Boot application. Finally we move
    the RestTemplate to a CommandLineRunner callback so it is executed by Spring Boot on startup:
     */
    public static void main(String[] args) {
	SpringApplication.run(Application.class);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder pBuilder) {
	return pBuilder.build();
    }

    /*
    The RestTemplateBuilder is injected by Spring, and if you use it to create a RestTemplate then you will
    benefit from all the autoconfiguration that happens in Spring Boot with message converters and request factories.
    We also extract the RestTemplate into a @Bean to make it easier to test (it can be mocked more easily that way).
     */
    @Bean
    public CommandLineRunner run(RestTemplate pRestTemplate) {
	return args -> {
	    Quote quote = pRestTemplate.getForObject(
			    "http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
	    log.info(quote.toString());
	};
    }

    //  public static void main(String args[]) {
    //		RestTemplate restTemplate = new RestTemplate();
    //		Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
    //		log.info(quote.toString());
    //    }

}
