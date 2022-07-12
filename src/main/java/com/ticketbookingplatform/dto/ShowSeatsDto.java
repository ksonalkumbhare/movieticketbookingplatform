/**
 * 
 */
package com.ticketbookingplatform.dto;

import java.util.Date;

import com.ticketbookingplatform.enums.SeatType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Sonal Kumbhare
 *
 * @since 10-July-2022
 */
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class ShowSeatsDto {

	private long id;

	private String seatNumber;

	private int rate;

	private SeatType seatType;

	private boolean booked;

	private Date bookedAt;

}