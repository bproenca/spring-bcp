package com.example;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

//@EnableBinding(Source.class)
@EnableCircuitBreaker
@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class ReservationClientApplication {

    public static void main(String[] args) {
	SpringApplication.run(ReservationClientApplication.class, args);
    }
}

class Reservation {
    private String reservationName;

    public String getReservationName() {
	return reservationName;
    }
}

@RestController
@RequestMapping("/reservations") class ReservationApiGatewayRestController {

    @Autowired
    private RestTemplate restTemplate;

    //@Autowired
    //private Source source;

    @RequestMapping(method = RequestMethod.GET, value = "/other/{id}")
    public String doOtherStuff(@PathVariable("id") int id) {
	Object results = restTemplate.getForObject("http://reservation-service/reservations/" + id, Object.class);
	return results.toString();
    }

    @HystrixCommand(fallbackMethod = "getReservationNamesFallback")
    @RequestMapping(method = RequestMethod.GET, value = "/names")
    public Collection<String> getReservationNames() {

	ParameterizedTypeReference<Resources<Reservation>> ptr = new ParameterizedTypeReference<Resources<Reservation>>() {
	};
	/*
	http://reservation-service/
	this is the service ID, not the DNS
	*/
	ResponseEntity<Resources<Reservation>> entity = this.restTemplate.
			exchange("http://reservation-service/reservations", HttpMethod.GET, null, ptr);

	return entity
			.getBody()
			.getContent()
			.stream()
			.map(Reservation::getReservationName)
			.collect(Collectors.toList());
    }

    public Collection<String> getReservationNamesFallback() {
	List<String> retorno = new ArrayList<>();
	retorno.add("Vazio1");
	retorno.add("Vazio2");
	return retorno;
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
	return new RestTemplateBuilder().build();
    }

//    @RequestMapping(method = RequestMethod.POST)
    //    public void writeReservation(@RequestBody Reservation r) {
    //	Message<String> msg = MessageBuilder.withPayload(r.getReservationName()).build();
    //	this.source.output().send(msg);
    //    }
}
