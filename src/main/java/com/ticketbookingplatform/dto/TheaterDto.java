/**
 * 
 */
package com.ticketbookingplatform.dto;

import java.util.List;

import com.ticketbookingplatform.enums.TheaterType;

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
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TheaterDto {

	private long id;

	private String name;

	private TheaterType type;

	private String city;

	private String address;

	private List<ShowDto> shows;
}