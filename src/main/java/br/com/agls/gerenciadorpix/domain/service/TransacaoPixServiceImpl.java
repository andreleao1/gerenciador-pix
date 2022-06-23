package br.com.agls.gerenciadorpix.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agls.gerenciadorpix.domain.exception.ChaveNaoEncontradaException;
import br.com.agls.gerenciadorpix.domain.model.ChavePix;
import br.com.agls.gerenciadorpix.domain.model.TransacaoPix;
import br.com.agls.gerenciadorpix.domain.repository.ChavePixRepository;
import br.com.agls.gerenciadorpix.domain.repository.TransacaoPixRepository;
import br.com.agls.gerenciadorpix.domain.service.interfaces.TransacaoPixService;

@Service
public class TransacaoPixServiceImpl implements TransacaoPixService {

	private static final String MSG_CAHVE_PIX_INEXISTENTE = "Chave não cadastrada na instituição Will Bank";
	
	@Autowired
	private ChavePixRepository chavePixRepository;
	
	@Autowired
	private TransacaoPixRepository transacaoRepository;

	@Override
	public TransacaoPix salvar(TransacaoPix transacaoPix) {
		Optional<ChavePix> chavePix = this.chavePixRepository.findByChave(transacaoPix.getChavePix().getChave());
		
		if (chavePix.isPresent()) {
			transacaoPix.setChavePix(chavePix.get());
			return this.transacaoRepository.save(transacaoPix);
		}

		throw new ChaveNaoEncontradaException(MSG_CAHVE_PIX_INEXISTENTE);
	}

}
