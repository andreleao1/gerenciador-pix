package br.com.agls.gerenciadorpix.api.exceptionhandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.agls.gerenciadorpix.domain.exception.ChaveNaoEncontradaException;
import br.com.agls.gerenciadorpix.domain.exception.ChavePixExistenteException;
import br.com.agls.gerenciadorpix.domain.exception.CpfInvalidoException;
import br.com.agls.gerenciadorpix.domain.exception.EmailInvalidoException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ChavePixExistenteException.class)
	public ResponseEntity<?> handleChavePixExistenteException(ChavePixExistenteException ex,
			WebRequest request) {
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ResponseBody responseBody = new ResponseBody(ex.getMessage(), status.value());

		return handleExceptionInternal(ex, responseBody, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(CpfInvalidoException.class)
	public ResponseEntity<?> handleCpfInvalidoException(CpfInvalidoException ex,
			WebRequest request) {
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ResponseBody responseBody = new ResponseBody(ex.getMessage(), status.value());

		return handleExceptionInternal(ex, responseBody, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(EmailInvalidoException.class)
	public ResponseEntity<?> handleEmailInvalidoException(EmailInvalidoException ex,
			WebRequest request) {
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ResponseBody responseBody = new ResponseBody(ex.getMessage(), status.value());

		return handleExceptionInternal(ex, responseBody, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(ChaveNaoEncontradaException.class)
	public ResponseEntity<?> handleChaveNaoEncontradaException(ChaveNaoEncontradaException ex,
			WebRequest request) {
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ResponseBody responseBody = new ResponseBody(ex.getMessage(), status.value());

		return handleExceptionInternal(ex, responseBody, new HttpHeaders(), status, request);
	}
}
