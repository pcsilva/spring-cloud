package com.pcsilva.rest.exception;

public class ServicoIndisponivelException extends RuntimeException {

	private static final long serialVersionUID = -5310366437709597066L;

	public ServicoIndisponivelException(String msg) {
		super(msg);
	}

	public ServicoIndisponivelException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
