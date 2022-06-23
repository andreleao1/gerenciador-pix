package br.com.agls.gerenciadorpix.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import br.com.agls.gerenciadorpix.domain.exception.ChaveNaoEncontradaException;
import br.com.agls.gerenciadorpix.domain.model.ChavePix;
import br.com.agls.gerenciadorpix.domain.model.TipoChavePix;
import br.com.agls.gerenciadorpix.domain.model.TransacaoPix;
import br.com.agls.gerenciadorpix.domain.repository.ChavePixRepository;
import br.com.agls.gerenciadorpix.domain.repository.TransacaoPixRepository;

public class TransacaoPixServiceTest {
	
	private ChavePix chavePix;
	
	private TransacaoPix transacao;
	
	@Mock
	private ChavePixRepository chavePixRepository;
	
	@Mock
	private TransacaoPixRepository transacaoRepository;
	
	@InjectMocks	
	private TransacaoPixServiceImpl transacaoService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		chavePix = new ChavePix("teste@teste.com.br", TipoChavePix.EMAIL);
		transacao = new TransacaoPix(BigDecimal.valueOf(200.50), chavePix);
	}

	@Test
	@DisplayName("Nova transação pix")
	public void deveSalvarUmaTransacaoQuandoPassarDadosValidos() {		
		when(chavePixRepository.findByChave(transacao.getChavePix().getChave())).thenAnswer(new Answer<Optional<ChavePix>>() {
			public Optional<ChavePix> answer(InvocationOnMock invocation) throws Throwable {
				chavePix.setId(1L);
				return Optional.of(chavePix);
			}
		});
		
		when(transacaoRepository.save(transacao)).thenAnswer(new Answer<TransacaoPix>() {
			public TransacaoPix answer(InvocationOnMock invocation) throws Throwable {
				transacao.setId(1L);
				return transacao;
			}
		});
		
		TransacaoPix transacaoSalva = transacaoService.salvar(transacao);
		
		verify(transacaoRepository, times(1)).save(transacao);
		assertThat(transacaoSalva).isNotNull();
		assertThat(transacaoSalva.getId()).isNotNull();
	}
	
	@Test
	@DisplayName("Transação com chave não cadastrada")
	public void deveLancarChaveNaoEncontradaExceptionAoRealizarUmaTransacaoComUmaChaveNaoCadastrada() {
		when(chavePixRepository.existsByChave(transacao.getChavePix().getChave())).thenReturn(false);
		
		ChaveNaoEncontradaException exception = Assertions.assertThrows(ChaveNaoEncontradaException.class, () -> {
			transacaoService.salvar(transacao);
		});
		
		assertThat(exception.getMessage()).isEqualTo("Chave não cadastrada na instituição Will Bank");
	}
}
