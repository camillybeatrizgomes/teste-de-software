package br.ifpe.jaboatao.ts.servicos;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.ifpe.jaboatao.ts.entidades.Filme;
import br.ifpe.jaboatao.ts.entidades.Locacao;
import br.ifpe.jaboatao.ts.entidades.Usuario;
import br.ifpe.jaboatao.ts.exceptions.FilmesException;

public class LocacaoServiceTest {

	@Test
	@DisplayName("Primeiro teste")
	public void teste01() throws FilmesException {

		// Cenário
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuário 01");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 01", 10, 11.0));

		// Ação
		Locacao locacao = service.alugarFilme(filmes, usuario);

		// Verificação
		Assertions.assertEquals(locacao.getValorLocacao(), 11.0);
	}

	@Test
	@Disabled
	@DisplayName("Exceção - Filme sem estoque - Modo try/catch")
	public void excecao01() {
		// Cenário
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuário 01");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 01", 0, 11.0));
		// Ação
		try {
			Locacao locacao = service.alugarFilme(filmes, usuario);
			Assertions.fail("Deveria ter ocorrido uma Exceção.");
		} catch (FilmesException e) {
			// Verificação
			Assertions.assertEquals("Filme sem estoque.", e.getMessage());
		}
	}

	@Test
	@Disabled
	@DisplayName("Exceção - Filme sem estoque - Modo assertThrows()")
	public void excecao02() {
		// Cenário
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuário 01");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 01", 0, 11.0));

		// Ação
		FilmesException filmesException = Assertions.assertThrows(FilmesException.class, () -> {
			service.alugarFilme(filmes, usuario);
		}, "Era esperado uma Exceção.");
		// Verificação
		Assertions.assertEquals("Filme sem estoque.", filmesException.getMessage());
	}

	@Test
	@DisplayName("exceção - Filme nulo - Modo try/catch")
	public void excecao03() {
		// Cenário
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 01");

		// Ação
		try {
			Locacao locacao = service.alugarFilme(null, usuario);
		} catch (FilmesException e) {
			// Verificação
			Assertions.assertEquals("Filme nulo.", e.getMessage());
		}
	}

	@Test
	@DisplayName("exceção - Filme nulo - Modo assertThrows()")
	public void excecao04() {
		// Cenário
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 01");

		// Ação
		FilmesException filmesException = Assertions.assertThrows(FilmesException.class, () -> {
			service.alugarFilme(null, usuario);
		}, "Era esperado uma exceção.");
		// Verificação
		Assertions.assertEquals("Filme nulo.", filmesException.getMessage());
	}

	@Test
	@DisplayName("exceção - Usuario com nome vazio - Modo try/catch")
	public void excecao05() {
		// Cenário
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 01", 0, 11.0));

		// Ação
		try {
			Locacao locacao = service.alugarFilme(filmes, usuario);
		} catch (FilmesException e) {
			// Verificação
			Assertions.assertEquals("Usuario com nome vazio.", e.getMessage());
		}
	}

	@Test
	@DisplayName("exceção - Usuario com nome vazio - Modo assertThrows()")
	public void excecao06() {
		// Cenário
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 01", 0, 11.0));

		// Ação
		FilmesException filmesException = Assertions.assertThrows(FilmesException.class, () -> {
			service.alugarFilme(filmes, usuario);
		}, "Era esperado uma exceção.");
		// Verificação
		Assertions.assertEquals("Usuario com nome vazio.", filmesException.getMessage());
	}
	
	@Test
	@DisplayName("10% de desconto no 2° filme")
	public void desconto01() throws FilmesException {
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 01");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 01", 1, 11.0),
											new Filme("Filme 02", 1, 11.0));
		
		// Ação
		Locacao locacao = service.alugarFilme(filmes, usuario);
		
		// Verificação
		Assertions.assertEquals(20.9, locacao.getValorLocacao());
	}
	
	@Test
	@DisplayName("15% de desconto no 3° filme")
	public void desconto02() throws FilmesException {
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 01");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 01", 1, 11.0),
											new Filme("Filme 02", 1, 11.0),
											new Filme("Filme 03", 1, 11.0));
		
		// Ação
		Locacao locacao = service.alugarFilme(filmes, usuario);
		
		// Verificação
		Assertions.assertEquals(30.25, locacao.getValorLocacao());
	}
	
	@Test
	@DisplayName("25% de desconto no 4º filme")
	public void desconto03() throws FilmesException {
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 01");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 01", 1, 11.0),
											new Filme("Filme 02", 1, 11.0),
											new Filme("Filme 03", 1, 11.0),
											new Filme("Filme 04", 1, 11.0));
		
		// Ação
		Locacao locacao = service.alugarFilme(filmes, usuario);
		
		// Verificação
		Assertions.assertEquals(38.5, locacao.getValorLocacao());
	}
	
	@Test 
	@DisplayName("50% de desconto no 5º filme ou mais")
	public void desconto04() throws FilmesException {
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 01");
		List<Filme> filmes = Arrays.asList(new Filme("Filme 01", 1, 11.0),
											new Filme("Filme 02", 1, 11.0),
											new Filme("Filme 03", 1, 11.0),
											new Filme("Filme 04", 1, 11.0),
											new Filme("Filme 05", 1, 11.0));
		
		// Ação
		Locacao locacao = service.alugarFilme(filmes, usuario);
		
		// Ação
		Assertions.assertEquals(44.0, locacao.getValorLocacao());
	}
}
