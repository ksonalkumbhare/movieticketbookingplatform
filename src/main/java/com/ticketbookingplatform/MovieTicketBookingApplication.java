package com.ticketbookingplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author Sonal Kumbhare
 *
 */
@EnableJpaAuditing
@SpringBootApplication
public class MovieTicketBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieTicketBookingApplication.class, args);
	}

}