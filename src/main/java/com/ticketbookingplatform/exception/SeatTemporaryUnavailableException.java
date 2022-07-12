package com.ticketbookingplatform.exception;

public class SeatTemporaryUnavailableException extends RuntimeException {
	
	private static final long serialVersionUID = 646182706219385282L;

	private final String message;

	public SeatTemporaryUnavailableException(String message) {
		super(message);
		this.message = message;
	}
}
