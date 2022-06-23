package br.com.agls.gerenciadorpix.domain.model;

public enum TipoChavePix {

	CPF("CPF"), 
	EMAIL("EMAIL");

	private String tipoChave;

	private TipoChavePix(String tipoChave) {
		this.tipoChave = tipoChave;
	}

	public String getTipoChave() {
		return this.tipoChave;
	}
}
