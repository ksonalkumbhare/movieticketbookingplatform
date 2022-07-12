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

import com.ticketbookingplatform.adapter.MovieAdapter;
import com.ticketbookingplatform.dto.MovieDto;
import com.ticketbookingplatform.exception.DuplicateRecordException;
import com.ticketbookingplatform.model.MovieEntity;
import com.ticketbookingplatform.repository.MovieRepository;
import com.ticketbookingplatform.service.MovieService;

import lombok.extern.log4j.Log4j2;

/**
 * @author Sonal Kumbhare
 *
 * @since 10-July-2022
 */
@Log4j2
@Service
public class MovieServiceImpl implements MovieService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MovieServiceImpl.class);
	@Autowired
	private MovieRepository movieRepository;

	@Override
	public MovieDto addMovie(MovieDto movieDto) {

		if (movieRepository.existsByNameAndLanguage(movieDto.getName(), movieDto.getLanguage())) {
			throw new DuplicateRecordException("Movie Already Exists with Name: " + movieDto.getName() + " in Language: " + movieDto.getLanguage());
		}

		LOGGER.info("Adding New Movie: " + movieDto);

		MovieEntity movieEntity = MovieAdapter.toEntity(movieDto);

		movieEntity = movieRepository.save(movieEntity);

		LOGGER.info("Added New Movie [id: " + movieEntity.getId() + ", Name: " + movieEntity.getName() + ", Language: " + movieEntity.getLanguage() + "]");

		return MovieAdapter.toDto(movieEntity);
	}

	@Override
	public MovieDto getMovie(long id) {
		LOGGER.info("Searching Movie by id: " + id);

		Optional<MovieEntity> movieEntity = movieRepository.findById(id);

		if (!movieEntity.isPresent()) {
			LOGGER.error("Movie not found for id: " + id);
			throw new EntityNotFoundException("Movie Not Found with ID: " + id);
		}

		return MovieAdapter.toDto(movieEntity.get());
	}

}