package br.com.agls.gerenciadorpix.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
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
import br.com.agls.gerenciadorpix.domain.exception.ChavePixExistenteException;
import br.com.agls.gerenciadorpix.domain.exception.CpfInvalidoException;
import br.com.agls.gerenciadorpix.domain.exception.EmailInvalidoException;
import br.com.agls.gerenciadorpix.domain.model.ChavePix;
import br.com.agls.gerenciadorpix.domain.model.TipoChavePix;
import br.com.agls.gerenciadorpix.domain.repository.ChavePixRepository;

public class ChavePixServiceTest {
	
	private ChavePix chavePix;
	
	@Mock
	private ChavePixRepository chavePixRepository;
	
	@InjectMocks
	private ChavePixServiceImpl chavePixService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);		
		chavePix = new ChavePix("teste@teste.com.br", TipoChavePix.EMAIL);
	}
	
	@Test
	@DisplayName("Cadastrar chave pix")
	public void deveSalvarUmaChavePixQuandoPassarUmaChaveValida() {
		when(chavePixRepository.save(chavePix)).thenAnswer(new Answer<ChavePix>() {
			public ChavePix answer(InvocationOnMock invocation) throws Throwable {
				chavePix.setId(1L);
				return chavePix;
			}
		});
		
		chavePixService.cadastrar(chavePix);
		
		verify(chavePixRepository, times(1)).save(chavePix);
		assertThat(chavePix.getId()).isNotNull();
	}
	
	@Test
	@DisplayName("Cadastro de chave existente")
	public void deveLancarChavePixExistenteExceptionAoCadastrarUmaChaveExistente() {
		when(chavePixRepository.existsByChave(chavePix.getChave())).thenReturn(true);
		
		ChavePixExistenteException exception = Assertions.assertThrows(ChavePixExistenteException.class, () -> {
			chavePixService.cadastrar(chavePix);
		});
		
		assertThat(exception.getMessage()).isEqualTo("Chave "+ chavePix.getChave() + " já cadastrada na instituição Will Bank");
	}
	
	
	@Test
	@DisplayName("Cadastro de chave com CPF inválido")
	public void deveLancarCpfInvalidoExceptionAoCadastrarUmaChaveComCpfInvalido() {
		chavePix = new ChavePix("1754824803", TipoChavePix.CPF);
		
		CpfInvalidoException exception = Assertions.assertThrows(CpfInvalidoException.class, () -> {
			chavePixService.cadastrar(chavePix);
		});
		
		assertThat(exception.getMessage()).isEqualTo("O cpf " + chavePix.getChave() + " não é válido");
	}
	
	@Test
	@DisplayName("Cadastro de chave com Email inválido")
	public void deveLancarEmailInvalidoExceptionAoCadastrarUmaChaveComEmailInvalido() {
		chavePix.setChave("testeteste.com.br");
		
		EmailInvalidoException exception = Assertions.assertThrows(EmailInvalidoException.class, () -> {
			chavePixService.cadastrar(chavePix);
		});
		
		assertThat(exception.getMessage()).isEqualTo("O e-mail " + chavePix.getChave() + " não é válido");
	}
	
	@Test
	@DisplayName("Atualizar chave pix")
	public void deveAtualizarUmaChaveAoPassarOsDadosValidos() {
		ChavePix novaChave = new ChavePix("17548248031", TipoChavePix.CPF);
		novaChave.setId(1L);
		chavePix.setId(1L);
		
		when(chavePixRepository.findById(1L)).thenAnswer(new Answer<Optional<ChavePix>>() {
			public Optional<ChavePix> answer(InvocationOnMock invocation) throws Throwable {
				return Optional.of(novaChave);
			}
		});
		
		when(chavePixRepository.save(novaChave)).thenAnswer(new Answer <ChavePix>() {
			public ChavePix answer(InvocationOnMock invocation) throws Throwable {
				return novaChave;
			}
		});
		
		ChavePix chaveAtualizada = chavePixService.atualizar(novaChave);
		
		assertThat(chaveAtualizada).isNotNull();
		assertThat(chaveAtualizada.getChave()).isEqualTo(novaChave.getChave());
		assertThat((chaveAtualizada.getTipoChave()).equals(TipoChavePix.CPF));
	}
	
	@Test
	@DisplayName("Atualizar chave com um valor já existente")
	public void deveLancarChavePixExistenteExceptionAoAtualizarUmaChaveComOValorDeOutraJaExistente() {
		ChavePix novaChave = new ChavePix("17548248031", TipoChavePix.CPF);
		
		when(chavePixRepository.existsByChave(novaChave.getChave())).thenReturn(true);
		
		ChavePixExistenteException exception = Assertions.assertThrows(ChavePixExistenteException.class, () -> {
			chavePixService.atualizar(novaChave);
		});
		
		assertThat(exception.getMessage()).isEqualTo("Chave " + novaChave.getChave() + " já cadastrada na instituição Will Bank");
	}
	
	@Test
	@DisplayName("Atualizar chave com CPF inválido")
	public void deveLancarCpfInvalidoExceptionAoAtualizarUmaChaveComUmNumeroDeCpfInvalido() {
		ChavePix novaChave = new ChavePix("1754824803", TipoChavePix.CPF);
		
		CpfInvalidoException exception = Assertions.assertThrows(CpfInvalidoException.class, () -> {
			chavePixService.atualizar(novaChave);
		});
		
		assertThat(exception.getMessage()).isEqualTo("O cpf " + novaChave.getChave() + " não é válido");
	}
	
	@Test
	@DisplayName("Atualizar chave com email inválido")
	public void deveLancarEmailInvalidoExceptionAoAtualizarChaveComEmailInvalido() {
		ChavePix novaChave = new ChavePix("testeteste.com.br", TipoChavePix.EMAIL);
		
		EmailInvalidoException exception = Assertions.assertThrows(EmailInvalidoException.class, () -> {
			chavePixService.atualizar(novaChave);
		});
		
		assertThat(exception.getMessage()).isEqualTo("O e-mail " + novaChave.getChave() + " não é válido");
	}
	
	@Test
	@DisplayName("Listar chaves")
	public void deveRetornarListagemDasChavesCadastradas() {
		List<ChavePix> chaves = new ArrayList<ChavePix>();
		when(chavePixRepository.findAll()).thenAnswer(new Answer<List<ChavePix>>() {
			public List<ChavePix> answer(InvocationOnMock invocation) throws Throwable {
				ChavePix chave1 = new ChavePix("teste@teste.com.br", TipoChavePix.EMAIL);
				ChavePix chave2 = new ChavePix("1754824803", TipoChavePix.CPF);
				
				chaves.add(chave1);
				chaves.add(chave2);
				
				return chaves;
			}
		});
		
		chavePixService.listar();
		
		assertThat(chaves).isNotNull();
		assertThat(chaves.size()).isEqualTo(2);
	}
	
	@Test
	@DisplayName("Excluir uma chave")
	public void deveExcluirChavePixQuandoPassarUmIdValido() {
		chavePix.setId(1L);
		when(chavePixRepository.findById(1L)).thenAnswer(new Answer<Optional<ChavePix>>() {
			public Optional<ChavePix> answer(InvocationOnMock invocation) throws Throwable {
				return Optional.of(chavePix);
			}
		});
		
		chavePixService.excluir(chavePix.getId());
		
		verify(chavePixRepository, times(1)).deleteById(chavePix.getId());
	}
	
	@Test
	@DisplayName("Excluir chave inexistente")
	public void deveRetornarChaveNaoEncontradaExceptionAoTentarExcluirUmaChaveInexistente() {
		when(chavePixRepository.findById(1L)).thenReturn(Optional.empty());
		ChaveNaoEncontradaException exception = Assertions.assertThrows(ChaveNaoEncontradaException.class, () -> {
			chavePixService.excluir(1L);
		});
		
		assertThat(exception.getMessage()).isEqualTo("Chave não cadastrada na instituição Will Bank");
	}
}
