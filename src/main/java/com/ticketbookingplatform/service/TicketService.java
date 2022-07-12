/**
 * 
 */
package com.ticketbookingplatform.service;

import com.ticketbookingplatform.dto.BookTicketRequestDto;
import com.ticketbookingplatform.dto.TicketDto;

/**
 * @author Sonal Kumbhare
 *
 * @since 10-July-2022
 */
public interface TicketService {

	TicketDto bookTicket(BookTicketRequestDto bookTicketRequestDto);

	TicketDto getTicket(long id);
}