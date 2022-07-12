/**
 * 
 */
package com.ticketbookingplatform.adapter;

import com.ticketbookingplatform.dto.TheaterDto;
import com.ticketbookingplatform.model.TheaterEntity;

import lombok.experimental.UtilityClass;

/**
 * @author Sonal Kumbhare
 * @since 10-July-2022
 */
@UtilityClass
public class TheaterAdapter {

	public static TheaterEntity toEntity(TheaterDto theaterDto) {

		return TheaterEntity.builder()
				.name(theaterDto.getName())
				.type(theaterDto.getType())
				.city(theaterDto.getCity())
				.address(theaterDto.getAddress())
				.build();
	}

	public static TheaterDto toDto(TheaterEntity theaterEntity) {

		return TheaterDto.builder()
				.id(theaterEntity.getId())
				.name(theaterEntity.getName())
				.type(theaterEntity.getType())
				.city(theaterEntity.getCity())
				.address(theaterEntity.getAddress())
				.build();
	}

}