/**
 * 
 */
package com.ticketbookingplatform.service;

import com.ticketbookingplatform.dto.MovieDto;

/**
 * @author Sonal Kumbhare
 *
 * @since 10-July-2022
 */
public interface MovieService {

	MovieDto addMovie(MovieDto movieDto);

	MovieDto getMovie(long id);
}