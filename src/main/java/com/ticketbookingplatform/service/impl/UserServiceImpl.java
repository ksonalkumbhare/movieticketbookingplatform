/**
 * 
 */
package com.ticketbookingplatform.service.impl;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketbookingplatform.adapter.UserAdapter;
import com.ticketbookingplatform.dto.UserDto;
import com.ticketbookingplatform.exception.DuplicateRecordException;
import com.ticketbookingplatform.model.UserEntity;
import com.ticketbookingplatform.repository.UserRepository;
import com.ticketbookingplatform.service.UserService;

import lombok.extern.log4j.Log4j2;

/**
 * @author Sonal Kumbhare
 *
 * @since 10-July-2022
 */
@Log4j2
@Service
public class UserServiceImpl implements UserService {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDto addUser(UserDto userDto) {

		if (userRepository.existsByMobile(userDto.getMobile())) {
			throw new DuplicateRecordException("User Already Exists with Mobile: " + userDto.getMobile());
		}

		log.info("Adding New User: " + userDto);

		UserEntity userEntity = UserAdapter.toEntity(userDto);

		userEntity = userRepository.save(userEntity);

		log.info("Added New User [id: " + userEntity.getId() + ", Mobile: " + userEntity.getMobile() + "]");

		return UserAdapter.toDto(userEntity);
	}

	@Override
	public UserDto getUser(long id) {

		log.info("Searching User by id: " + id);

		Optional<UserEntity> userEntity = userRepository.findById(id);

		if (!userEntity.isPresent()) {
			log.error("User not found for id: " + id);
			throw new EntityNotFoundException("User Not Found with ID: " + id);
		}

		return UserAdapter.toDto(userEntity.get());
	}

}