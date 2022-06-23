package br.com.agls.gerenciadorpix.api.dto;

import java.math.BigDecimal;

public class TransacaoPixDto {

	private BigDecimal valor;
	
	private ChavePixDto chave;

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public ChavePixDto getChave() {
		return chave;
	}

	public void setChave(ChavePixDto chave) {
		this.chave = chave;
	}
}
