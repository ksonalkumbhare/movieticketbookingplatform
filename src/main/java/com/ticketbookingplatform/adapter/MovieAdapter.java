package com.ticketbookingplatform.adapter;

import com.ticketbookingplatform.dto.MovieDto;
import com.ticketbookingplatform.model.MovieEntity;

import lombok.experimental.UtilityClass;
/**
 * @author Sonal Kumbhare
 * @since 10-July-2022
 */
@UtilityClass
public class MovieAdapter {

	public static MovieEntity toEntity(MovieDto movieDto) {

		return MovieEntity.builder()
				.name(movieDto.getName())
				.language(movieDto.getLanguage())
				.certificateType(movieDto.getCertificateType())
				.build();

	}

	public static MovieDto toDto(MovieEntity movieEntity) {

		return MovieDto.builder()
				.id(movieEntity.getId())
				.name(movieEntity.getName())
				.language(movieEntity.getLanguage())
				.certificateType(movieEntity.getCertificateType())
				.build();
	}

}