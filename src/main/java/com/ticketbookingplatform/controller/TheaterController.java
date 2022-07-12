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

import com.ticketbookingplatform.dto.TheaterDto;
import com.ticketbookingplatform.service.TheaterService;

/**
 * @author Sonal Kumbhare
 *
 */
@RestController
@RequestMapping("theater")
public class TheaterController {

	private static final Logger logger = LoggerFactory.getLogger(TheaterController.class);
	
	@Autowired
	private TheaterService theaterService;

	@PostMapping("add")
	public ResponseEntity<TheaterDto> addUser(@RequestBody TheaterDto theaterDto) {

		logger.info("Received Request to add new theater: " + theaterDto);

		return ResponseEntity.ok(theaterService.addTheater(theaterDto));
	}

	@GetMapping("{id}")
	public ResponseEntity<TheaterDto> getUser(@PathVariable(name = "id") @Min(value = 1, message = "Theater Id Cannot be -ve") long id) {

		logger.info("Received Request to get theater: " + id);

		return ResponseEntity.ok(theaterService.getTheater(id));
	}
}