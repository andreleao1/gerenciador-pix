package br.com.agls.gerenciadorpix.domain.exception;

public class CpfInvalidoException extends RuntimeException {

	private static final long serialVersionUID = -8886932188282890L;

	public CpfInvalidoException(String msg) {
		super(msg);
	}
}
