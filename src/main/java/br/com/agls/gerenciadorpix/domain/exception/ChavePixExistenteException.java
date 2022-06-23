package br.com.agls.gerenciadorpix.domain.exception;

public class ChavePixExistenteException extends RuntimeException {

	private static final long serialVersionUID = 1302712125649168554L;

	public ChavePixExistenteException(String msg) {
		super(msg);
	}

}
