/**
 * 
 */
package com.ticketbookingplatform.service;

import com.ticketbookingplatform.dto.TheaterDto;

/**
 * @author Sonal Kumbhare
 *
 * @since 10-July-2022
 */
public interface TheaterService {

	TheaterDto addTheater(TheaterDto theaterDto);

	TheaterDto getTheater(long id);
}