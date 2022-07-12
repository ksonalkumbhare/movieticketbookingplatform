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

import com.ticketbookingplatform.dto.UserDto;
import com.ticketbookingplatform.service.UserService;

/**
 * @author Sonal Kumbhare
 * @since 10-July-2022
 */
@RestController
@RequestMapping("user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;

	@PostMapping("add")
	public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {

		logger.info("Received Request to add new user: " + userDto);

		return ResponseEntity.ok(userService.addUser(userDto));
	}

	@GetMapping("{id}")
	public ResponseEntity<UserDto> getUser(@PathVariable(name = "id") @Min(value = 1, message = "User Id Cannot be -ve") long id) {

		logger.info("Received Request to get user: " + id);

		return ResponseEntity.ok(userService.getUser(id));
	}
}