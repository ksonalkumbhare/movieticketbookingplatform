/**
 * 
 */
package com.ticketbookingplatform.controller;

import javax.validation.constraints.Min;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticketbookingplatform.dto.BookTicketRequestDto;
import com.ticketbookingplatform.dto.TicketDto;
import com.ticketbookingplatform.service.TicketService;

/**
 * @author Sonal Kumbhare
 * @since 10-July-2022
 */
@RestController
@RequestMapping("ticket")
public class TicketController {

	private static final Logger logger = LoggerFactory.getLogger(TicketController.class);
	
	@Autowired
	private TicketService ticketService;

	@PostMapping("book")
	public ResponseEntity<TicketDto> bookTicket(@RequestBody BookTicketRequestDto bookTicketRequestDto) {

		logger.info("Received Request to book ticket: " + bookTicketRequestDto);

		return ResponseEntity.ok(ticketService.bookTicket(bookTicketRequestDto));
	}

	@GetMapping("{id}")
	public ResponseEntity<TicketDto> getTicket(@PathVariable(name = "id") @Min(value = 1, message = "Ticket Id Cannot be -ve") long id) {

		logger.info("Received Request to get ticket: " + id);

		return ResponseEntity.ok(ticketService.getTicket(id));
	}
}