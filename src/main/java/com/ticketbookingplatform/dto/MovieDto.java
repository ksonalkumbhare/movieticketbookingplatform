/**
 * 
 */
package com.ticketbookingplatform.dto;

import com.ticketbookingplatform.enums.CertificateType;
import com.ticketbookingplatform.enums.MovieLanguage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Sonal Kumbhare
 * @since 10-July-2022
 */

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MovieDto {

	private long id;

	private String name;

	private MovieLanguage language;

	private CertificateType certificateType;

}