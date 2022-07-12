/**
 * 
 */
package com.ticketbookingplatform.adapter;

import com.ticketbookingplatform.dto.TicketDto;
import com.ticketbookingplatform.model.TicketEntity;

import lombok.experimental.UtilityClass;

/**
 * @author Sonal Kumbhare
 * @since 10-July-2022
 */
@UtilityClass
public class TicketAdapter {

	public static TicketEntity toEntity(TicketDto ticketDto) {

		return TicketEntity.builder()
				.allottedSeats(ticketDto.getAllottedSeats())
				.amount(ticketDto.getAmount())
				.build();

	}

	public static TicketDto toDto(TicketEntity ticketEntity) {

		return TicketDto.builder()
				.id(ticketEntity.getId())
				.allottedSeats(ticketEntity.getAllottedSeats())
				.amount(ticketEntity.getAmount())
				.show(ShowAdapter.toDto(ticketEntity.getShow()))
				.build();
	}

}