/**
 * 
 */
package com.ticketbookingplatform.service;

import java.time.LocalDate;
import java.time.LocalTime;

import com.ticketbookingplatform.dto.PageResponse;
import com.ticketbookingplatform.dto.ShowDto;

/**
 * @author Sonal Kumbhare
 *
 * @since 10-July-2022
 */
public interface ShowService {

	ShowDto addShow(ShowDto showDto);

	PageResponse<ShowDto> searchShows(String movieName, String city, LocalDate showDate, LocalTime showTime, int pageNo, int limit);

}