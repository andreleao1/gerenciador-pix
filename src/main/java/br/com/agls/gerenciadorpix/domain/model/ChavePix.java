package br.com.agls.gerenciadorpix.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class ChavePix {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String chave;
	
	@NotNull
	private TipoChavePix tipoChave;
	
	public ChavePix() {
		
	}	
	
	public ChavePix(String chave) {
		this.chave = chave;
	}

	public ChavePix(String chave, TipoChavePix tipoChave) {
		this.chave = chave;
		this.tipoChave = tipoChave;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public TipoChavePix getTipoChave() {
		return tipoChave;
	}

	public void setTipoChave(TipoChavePix tipoChave) {
		this.tipoChave = tipoChave;
	}
}
