package br.com.agls.gerenciadorpix.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class TransacaoPix {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@CreationTimestamp
	private LocalDateTime dataHora;
	
	@NotNull
	private BigDecimal valor;
	
	@ManyToOne
	private ChavePix chavePix;
	
	public TransacaoPix() {
		
	}

	public TransacaoPix(BigDecimal valor, ChavePix chavePix) {
		this.valor = valor;
		this.chavePix = chavePix;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public ChavePix getChavePix() {
		return chavePix;
	}

	public void setChavePix(ChavePix chavePix) {
		this.chavePix = chavePix;
	}
}
