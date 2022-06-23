package br.com.agls.gerenciadorpix.domain.utils;

public class Validator {
	
	public static boolean isCpfValido(String cpf) {
		return cpf.length() == 11;
	}

	public static boolean isEmailValido(String email) {
		return email.contains("@");
	}
}
