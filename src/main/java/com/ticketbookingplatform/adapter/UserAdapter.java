/**
 * 
 */
package com.ticketbookingplatform.adapter;

import com.ticketbookingplatform.dto.UserDto;
import com.ticketbookingplatform.model.UserEntity;

import lombok.experimental.UtilityClass;

/**
 * @author Sonal Kumbhare
 * @since 10-July-2022
 */
@UtilityClass
public class UserAdapter {

	public static UserEntity toEntity(UserDto userDto) {

		return UserEntity.builder()
				.name(userDto.getName())
				.mobile(userDto.getMobile())
				.build();

	}

	public static UserDto toDto(UserEntity userEntity) {

		return UserDto.builder()
				.id(userEntity.getId())
				.name(userEntity.getName())
				.mobile(userEntity.getMobile())
				.build();
	}

}