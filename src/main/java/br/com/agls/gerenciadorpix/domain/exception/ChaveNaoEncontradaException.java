package br.com.agls.gerenciadorpix.domain.exception;

public class ChaveNaoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = 1489205881163926658L;

	public ChaveNaoEncontradaException(String msg) {
		super(msg);
	}

}
