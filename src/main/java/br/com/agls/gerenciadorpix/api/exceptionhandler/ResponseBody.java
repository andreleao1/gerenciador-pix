package br.com.agls.gerenciadorpix.api.exceptionhandler;

import java.time.LocalDateTime;

public class ResponseBody {
	private String message;
	private Integer status;
	private LocalDateTime timestamp;
	
	

	public ResponseBody(String message, Integer status) {
		this.message = message;
		this.status = status;
		this.timestamp = LocalDateTime.now();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

}
