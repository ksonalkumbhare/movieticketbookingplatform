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

import com.ticketbookingplatform.dto.MovieDto;
import com.ticketbookingplatform.service.MovieService;

/**
 * @author Sonal Kumbhare
 * @since 10-July-2022
 */
@RestController
@RequestMapping("movie")
public class MovieController {

	private static final Logger logger = LoggerFactory.getLogger(MovieController.class); 
	@Autowired
	private MovieService movieService;

	@PostMapping("add")
	public ResponseEntity<MovieDto> addMovie(@RequestBody MovieDto movieDto) {

		logger.info("Received Request to add new movie: " + movieDto);

		return ResponseEntity.ok(movieService.addMovie(movieDto));
	}

	@GetMapping("{id}")
	public ResponseEntity<MovieDto> getUser(@PathVariable(name = "id") @Min(value = 1, message = "Movie Id Cannot be -ve") long id) {

		logger.info("Received Request to get movie: " + id);

		return ResponseEntity.ok(movieService.getMovie(id));
	}
}