/**
 * 
 */
package com.ticketbookingplatform.exception;

import lombok.Getter;

/**
 * @author Sonal Kumbhare
 *
 * @since 10-July-2022
 */
@Getter
public class DuplicateRecordException extends RuntimeException {

	private static final long serialVersionUID = 646182706219385282L;

	private final String message;

	public DuplicateRecordException(String message) {
		super(message);
		this.message = message;
	}

}