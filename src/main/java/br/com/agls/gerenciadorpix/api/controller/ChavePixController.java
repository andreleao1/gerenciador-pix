package br.com.agls.gerenciadorpix.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.agls.gerenciadorpix.domain.model.ChavePix;
import br.com.agls.gerenciadorpix.domain.service.interfaces.ChavePixService;

@RestController
@RequestMapping("/chaves-pix")
public class ChavePixController {
	
	@Autowired
	private ChavePixService chavePixService;

	@PostMapping
	public ResponseEntity<ChavePix> cadastrar(@Valid @RequestBody ChavePix chavePix) {
		return ResponseEntity.ok(this.chavePixService.cadastrar(chavePix));
	}
	
	@PutMapping
	public ResponseEntity<ChavePix> atualizr(@Valid @RequestBody ChavePix chavePix) {
		return ResponseEntity.ok(this.chavePixService.atualizar(chavePix));
	}
	
	@GetMapping
	public ResponseEntity<List<ChavePix>> listar() {
		return ResponseEntity.ok(this.chavePixService.listar());
	}
	
	@DeleteMapping("/{idChave}")
	public ResponseEntity<Void> excluir(@PathVariable Long idChave) {
		this.chavePixService.excluir(idChave);
		return ResponseEntity.noContent().build();
	}
}
