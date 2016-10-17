package org.hni.common.delegate;

public class HniException extends Exception {

	private static final long serialVersionUID = 8769629755755529190L;

	public HniException(String message) {
		super(message);
	}
	
	public HniException(String message, Throwable e) {
		super(message, e);
	}
}
