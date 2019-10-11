package com.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Collection;
import java.util.stream.Stream;

//https://www.youtube.com/watch?v=ZyK5QrKCbwM

@RepositoryRestResource interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @RestResource(path = "by-name")
    Collection<Reservation> findByReservationName(@Param("rn") String rn);
}

@EnableDiscoveryClient
//@IntegrationComponentScan
//@EnableBinding(Sink.class)
@SpringBootApplication
public class ReservationServiceApplication {

    public static void main(String[] args) {
	SpringApplication.run(ReservationServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ReservationRepository reservationRepository) {
	return pStrings -> {
	    Stream.of("Josh", "Pieter", "Tasha", "Eric", "Susie")
			    .forEach(n -> reservationRepository.save(new Reservation(n)));
	};
    }
}

@RefreshScope
@RestController class MessageRestController {

    @Value("${sayhello}")
    private String hello;

    @Value("${message}")
    private String msg;

    @RequestMapping("/message")
    String message() {
	return this.msg;
    }

    @RequestMapping("/sayhelloplease")
    String sayHello() {
	return this.hello;
    }
}

@Entity class Reservation {

    @Id @GeneratedValue
    private Long id;

    private String reservationName;

    public Reservation() { //JPA
    }

    public Reservation(final String pReservationName) {

	reservationName = pReservationName;
    }

    public Long getId() {
	return id;
    }

    public String getReservationName() {
	return reservationName;
    }

    @Override public String toString() {
	return "Reservation{" +
			"id=" + id +
			", reservationName='" + reservationName + '\'' +
			'}';
    }
}

//@MessageEndpoint class ReservationProcessor {
//
//    @Autowired
//    private ReservationRepository reservationRepository;
//
//    @ServiceActivator(inputChannel = Sink.INPUT)
//    public void acceptNewReservations(String rn) {
//	reservationRepository.save(new Reservation(rn));
//    }
//}