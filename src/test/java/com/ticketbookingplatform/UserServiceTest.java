package com.ticketbookingplatform;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Objects;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ticketbookingplatform.dto.UserDto;
import com.ticketbookingplatform.exception.DuplicateRecordException;
import com.ticketbookingplatform.repository.UserRepository;
import com.ticketbookingplatform.service.UserService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Test
	public void testAddUser() {

		deleteUser();

		String name = "Test";
		String mobile = "124";

		UserDto userDto = UserDto.builder().name(name).mobile(mobile).build();

		userDto = userService.addUser(userDto);

		assertTrue(Objects.nonNull(userDto));
		assertTrue(userDto.getName().equals(name));
		assertTrue(userDto.getMobile().equals(mobile));

		userRepository.deleteById(userDto.getId());
	}

	//@Test(expected = DuplicateRecordException.class)
	@Test()
	public void testAddDuplicateUser() {

		deleteUser();

		String name = "Test";
		String mobile = "124";

		UserDto userDto = UserDto.builder().name(name).mobile(mobile).build();

		userDto = userService.addUser(userDto);

		assertTrue(Objects.nonNull(userDto));
		assertTrue(userDto.getName().equals(name));
		assertTrue(userDto.getMobile().equals(mobile));

		try {
		userService.addUser(userDto);
		}
		catch(Exception e) {
			assertTrue(e instanceof DuplicateRecordException);
		}
	}

	@Test
	public void testGetUser() {

		deleteUser();

		String name = "Test";
		String mobile = "124";

		UserDto userDto = UserDto.builder().name(name).mobile(mobile).build();

		userDto = userService.addUser(userDto);

		userDto = userService.getUser(userDto.getId());

		assertTrue(Objects.nonNull(userDto));
		assertTrue(userDto.getName().equals(name));
		assertTrue(userDto.getMobile().equals(mobile));

		userRepository.deleteById(userDto.getId());

	}

	@Test()
	public void testGetNonExistentUser() {
		try {
			userService.getUser(-1);
		}
		catch(Exception e)
		{
			assertTrue(e instanceof EntityNotFoundException);
		}
	}

	private void deleteUser() {
		userRepository.deleteByMobile("124");
	}
}