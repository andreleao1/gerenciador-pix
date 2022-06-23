package br.com.agls.gerenciadorpix.domain.exception;

public class EmailInvalidoException extends RuntimeException {

	private static final long serialVersionUID = -4076836863222872529L;

	public EmailInvalidoException(String msg) {
		super(msg);
	}
}
