package br.com.agls.gerenciadorpix.domain.service.interfaces;

import java.util.List;

import br.com.agls.gerenciadorpix.domain.model.ChavePix;

public interface ChavePixService {

	ChavePix cadastrar(ChavePix chavePix);

	ChavePix atualizar(ChavePix chavePix);

	List<ChavePix> listar();

	void excluir(Long idChave);
}
