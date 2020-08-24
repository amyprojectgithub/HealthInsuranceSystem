package com.ashokit.exception;

public class HisException extends RuntimeException {

	private static final long serialVersionUID = -8077288336568436792L;

	public HisException() {
		// No Op
	}

	public HisException(String msg) {
		super(msg);
	}

}
