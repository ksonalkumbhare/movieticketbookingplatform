/**
 * 
 */
package com.ticketbookingplatform.service;

import com.ticketbookingplatform.dto.UserDto;

/**
 * @author Sonal Kumbhare
 *
 * @since 10-July-2022
 */
public interface UserService {

	UserDto addUser(UserDto userDto);

	UserDto getUser(long id);
}