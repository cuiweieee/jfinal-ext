package com.infoland.jfinal.converter;

@SuppressWarnings("serial")
public class ConverterException extends RuntimeException {

	public ConverterException() {
		super();
	}

	public ConverterException(String msg) {
		super(msg);
	}

	public ConverterException(Throwable t) {
		super(t);
	}

	public ConverterException(String msg, Throwable t) {
		super(msg, t);
	}
}
