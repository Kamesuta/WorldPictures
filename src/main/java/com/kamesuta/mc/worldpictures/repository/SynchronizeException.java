package com.kamesuta.mc.worldpictures.repository;

/**
 * A Synchornize Exception
 * @author Kamesuta
 */
public class SynchronizeException extends Exception {

	public SynchronizeException() {
	}

	public SynchronizeException(String message) {
		super(message);
	}

	public SynchronizeException(Throwable cause) {
		super(cause);
	}

	public SynchronizeException(String message, Throwable cause) {
		super(message, cause);
	}

	public SynchronizeException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
