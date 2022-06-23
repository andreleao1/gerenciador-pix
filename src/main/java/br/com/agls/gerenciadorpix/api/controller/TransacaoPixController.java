package br.com.agls.gerenciadorpix.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.agls.gerenciadorpix.api.dto.TransacaoPixDto;
import br.com.agls.gerenciadorpix.domain.model.ChavePix;
import br.com.agls.gerenciadorpix.domain.model.TransacaoPix;
import br.com.agls.gerenciadorpix.domain.service.interfaces.TransacaoPixService;

@RestController
@RequestMapping("/transacao")
public class TransacaoPixController {

	@Autowired
	private TransacaoPixService transacaoPixService;

	@PostMapping
	public ResponseEntity<TransacaoPix> salvar(@Valid @RequestBody TransacaoPixDto transacaoPix) {
		TransacaoPix transacao = parseToTransacaoPix(transacaoPix);
		return ResponseEntity.ok(this.transacaoPixService.salvar(transacao));
	}
	
	private TransacaoPix parseToTransacaoPix(TransacaoPixDto transacaoPix) {
		return new TransacaoPix(transacaoPix.getValor(), new ChavePix(transacaoPix.getChave().getChave()));
	}
}
