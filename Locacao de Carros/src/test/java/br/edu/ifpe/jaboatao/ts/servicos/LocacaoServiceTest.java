package br.edu.ifpe.jaboatao.ts.servicos;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.edu.ifpe.jaboatao.ts.builder.CarroBuilder;
import br.edu.ifpe.jaboatao.ts.builder.ClienteBuilder;
import br.edu.ifpe.jaboatao.ts.entidades.Carro;
import br.edu.ifpe.jaboatao.ts.entidades.Cliente;
import br.edu.ifpe.jaboatao.ts.entidades.Locacao;
import br.edu.ifpe.jaboatao.ts.exceptions.LocacaoException;
import br.edu.ifpe.jaboatao.ts.utils.ManipulandoDatas;

public class LocacaoServiceTest {
	LocacaoService service;
	
	@BeforeEach
	public void inicio() {
		service = new LocacaoService();
	}
	
	@Test
	public void primeiroTeste() throws LocacaoException {
		//System.out.println("Funcionando.");
		//Cenário
		Cliente cliente = ClienteBuilder.umCliente().agora();
		List<Carro> carros = Arrays.asList(CarroBuilder.umCarro().valorLocacao(100).agora());
		
		//Ação
		Locacao locacao = service.alugarCarro(cliente, carros);
		
		//Verificação
		// Irá ser esperado um valor verdadeiro 
		Assertions.assertTrue(locacao.getValorLocacao() == 100.00);
		Assertions.assertTrue(ManipulandoDatas.boDatasIguais(locacao.getDataLocacao(), new Date()));
		Assertions.assertTrue(ManipulandoDatas.boDatasIguais(locacao.getDataRetorno(), ManipulandoDatas.novaDataComDiferencaDeDias(3)));
		
	}
	
	@Test
	@DisplayName("Exception - Estoque vazio - Modo Try/catch")
	public void exception01() {
		// Cenário
		Cliente cliente = ClienteBuilder.umCliente().outroNome("Cliente 02").agora();
		List<Carro> carros = Arrays.asList(CarroBuilder.umCarro().semEstoque().valorLocacao(100.00).agora());
		
		// Ação 
		try {
			Locacao locacao = service.alugarCarro(cliente, carros);
			Assertions.fail("Deveria ter ocorrido uma exceção, mas não ocorreu.");
		} catch (LocacaoException e) {
			// Verificação
			Assertions.assertEquals("Estoque vazio.", e.getMessage());
		}
	}
	
	@Test
	@DisplayName("Exception - Estoque vazio - Modo assertThrows")
	public void exception02() {
		// Cenário
		Cliente cliente = ClienteBuilder.umCliente().agora();
		List<Carro> carros = Arrays.asList(CarroBuilder.umCarro().semEstoque().valorLocacao(100.00).agora());
		
		// Ação 
		LocacaoException e = Assertions.assertThrows(LocacaoException.class, () -> {
			service.alugarCarro(cliente, carros);
		}, "Deveria ter ocorrido uma exceção, mas não ocorreu.");
		// Verificação
		Assertions.assertEquals("Estoque vazio.", e.getMessage());
	}
	
	@Test
	@DisplayName("Exception - Carro nulo - Modo Try/catch")
	public void exception03() {
		// Cenário
		Cliente cliente = ClienteBuilder.umCliente().outroNome("Cliente 03").agora();
		Carro carro = CarroBuilder.carroNulo().agora();
		
		// Ação 
		try {
			Locacao locacao = service.alugarCarro(cliente, null);
			Assertions.fail("Deveria ter ocorrido uma exceção, mas não ocorreu.");
		} catch (LocacaoException e) {
			Assertions.assertEquals("Carro nulo.", e.getMessage());
		}
	}
	
	@Test
	@DisplayName("Exception - Carro nulo - Modo assertThrows")
	public void exception04() {
		// Cenário
		Cliente cliente = ClienteBuilder.umCliente().agora();
		Carro carro = CarroBuilder.carroNulo().agora();
				
		// Ação 
		LocacaoException e = Assertions.assertThrows(LocacaoException.class, () -> {
			service.alugarCarro(cliente, null);
		}, "Deveria ter ocorrido uma exceção, mas não ocorreu.");
		// Verificação
		Assertions.assertEquals("Carro nulo.", e.getMessage());
	}
	
	@Test
	@DisplayName("Exception - Estoque total")
	public void exception05() {
		// Cenário
		Cliente cliente = ClienteBuilder.umCliente().agora();
		List<Carro> carros = Arrays.asList(CarroBuilder.umCarro().valorLocacao(60).agora(),
											CarroBuilder.umCarro().valorLocacao(40).agora());
		Locacao locacao = new Locacao();
		// Ação 
		try {
			locacao = service.alugarCarro(cliente, carros);
		} catch (LocacaoException e) {
			// Verificação
			Assertions.assertEquals(100.00, locacao.getValorLocacao());
		}
	}
	
	@Test
	@DisplayName("Desconto de 10% no 2º Carro")
	public void desconto01() throws LocacaoException {
		// Cenário
		Cliente cliente = new Cliente("Cliente 01");
		List<Carro> carros = Arrays.asList(
									CarroBuilder.umCarro().valorLocacao(100.00).agora(),
									CarroBuilder.umCarro().valorLocacao(90.00).agora());
		
		// Ação
		Locacao locacao = service.alugarCarro(cliente, carros);
		
		// Verificação
		Assertions.assertEquals(181.00, locacao.getValorLocacao());
	}
	
	@Test
	@DisplayName("Desconto de 15% no 3º Carro")
	public void desconto02() throws LocacaoException {
		// Cenário
		Cliente cliente = ClienteBuilder.umCliente().agora();
		List<Carro> carros = Arrays.asList(
										CarroBuilder.umCarro().valorLocacao(100.00).agora(),
										CarroBuilder.umCarro().valorLocacao(90.00).agora(),
										CarroBuilder.umCarro().valorLocacao(90.00).agora());
		
		// Ação
		Locacao locacao = service.alugarCarro(cliente, carros);
		
		// Verificação
		Assertions.assertEquals(257.50, locacao.getValorLocacao());
	}
	
	@Test
	@DisplayName("Desconto de 20% no 4º Carro")
	public void desconto03() throws LocacaoException {
		// Cenário
		Cliente cliente = ClienteBuilder.umCliente().agora();
		List<Carro> carros = Arrays.asList(
									CarroBuilder.umCarro().valorLocacao(100.00).agora(),
									CarroBuilder.umCarro().valorLocacao(90.00).agora(),
									CarroBuilder.umCarro().valorLocacao(90.00).agora(),
									CarroBuilder.umCarro().valorLocacao(90.00).agora());
		
		// Ação
		Locacao locacao = service.alugarCarro(cliente, carros);
		
		// Verificação
		Assertions.assertEquals(329.50, locacao.getValorLocacao());
	}
	
	@Test
	@DisplayName("Desconto de 25% no 5º Carro ou mais")
	public void desconto04() throws LocacaoException {
		// Cenário
		Cliente cliente = ClienteBuilder.umCliente().agora();
		List<Carro> carros = Arrays.asList(
									CarroBuilder.umCarro().valorLocacao(100.00).agora(),
									CarroBuilder.umCarro().valorLocacao(90.00).agora(),
									CarroBuilder.umCarro().valorLocacao(90.00).agora(),
									CarroBuilder.umCarro().valorLocacao(90.00).agora(),
									CarroBuilder.umCarro().valorLocacao(90.00).agora(),
									CarroBuilder.umCarro().valorLocacao(90.00).agora());
		
		// Ação
		Locacao locacao = service.alugarCarro(cliente, carros);
		
		// Verificação
		Assertions.assertEquals(464.50, locacao.getValorLocacao());
	}
}
