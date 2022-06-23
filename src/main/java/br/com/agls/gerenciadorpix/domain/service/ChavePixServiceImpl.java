package br.com.agls.gerenciadorpix.domain.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.agls.gerenciadorpix.domain.exception.ChaveNaoEncontradaException;
import br.com.agls.gerenciadorpix.domain.exception.ChavePixExistenteException;
import br.com.agls.gerenciadorpix.domain.exception.CpfInvalidoException;
import br.com.agls.gerenciadorpix.domain.exception.EmailInvalidoException;
import br.com.agls.gerenciadorpix.domain.model.ChavePix;
import br.com.agls.gerenciadorpix.domain.model.TipoChavePix;
import br.com.agls.gerenciadorpix.domain.repository.ChavePixRepository;
import br.com.agls.gerenciadorpix.domain.service.interfaces.ChavePixService;
import br.com.agls.gerenciadorpix.domain.utils.Validator;

@Service
public class ChavePixServiceImpl implements ChavePixService {
	
	private static final String MSG_CAHVE_PIX_INEXISTENTE = "Chave não cadastrada na instituição Will Bank";
	private static final String MSG_CHAVE_PIX_EXISTENTE = "Chave %s já cadastrada na instituição Will Bank";
	private static final String MSG_EMAIL_INVALIDO = "O e-mail %s não é válido";
	private static final String MSG_CPF_INVALIDO = "O cpf %s não é válido";
	
	@Autowired
	private ChavePixRepository chavePixReposotory;
	
	private void validarChave(ChavePix chavePix) {
		String chave = chavePix.getChave();

		if (this.chavePixReposotory.existsByChave(chave)) {
			throw new ChavePixExistenteException(String.format(MSG_CHAVE_PIX_EXISTENTE, chave));
		}

		if (chavePix.getTipoChave().equals(TipoChavePix.CPF)) {
			if (!Validator.isCpfValido(chave)) {
				throw new CpfInvalidoException(String.format(MSG_CPF_INVALIDO, chave));
			}
		} else {
			if (!Validator.isEmailValido(chave)) {
				throw new EmailInvalidoException(String.format(MSG_EMAIL_INVALIDO, chave));
			}
		}
	}

	@Override
	public ChavePix cadastrar(ChavePix chavePix) {
		validarChave(chavePix);
		return this.chavePixReposotory.save(chavePix);
	}

	@Override
	public ChavePix atualizar(ChavePix chavePix) {
		validarChave(chavePix);
		ChavePix chavePixEncontrada = buscar(chavePix.getId());		
		BeanUtils.copyProperties(chavePix, chavePixEncontrada, "id");
		return this.chavePixReposotory.save(chavePixEncontrada);
	}

	private ChavePix buscar(Long idChave) {
		return this.chavePixReposotory
				.findById(idChave).orElseThrow(() -> new ChaveNaoEncontradaException(MSG_CAHVE_PIX_INEXISTENTE));
	}

	@Override
	public List<ChavePix> listar() {
		return this.chavePixReposotory.findAll();
	}

	@Override
	public void excluir(Long idChave) {
		buscar(idChave);
		this.chavePixReposotory.deleteById(idChave);
	}

}
