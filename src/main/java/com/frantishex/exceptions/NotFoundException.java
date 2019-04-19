package com.frantishex.exceptions;

public class NotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public NotFoundException(String errMessage) {
		super(errMessage);
	}
}