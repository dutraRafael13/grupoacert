package com.grupoacert.services.exceptions;

public class DuplicateDeliveryException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DuplicateDeliveryException(String mensagem) {
		super(mensagem);
	}

	public DuplicateDeliveryException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}