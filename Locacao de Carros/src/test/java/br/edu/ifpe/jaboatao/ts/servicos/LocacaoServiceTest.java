package br.edu.ifpe.jaboatao.ts.servicos;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
		Cliente cliente = new Cliente("Cliente 01");
		Carro carro = new Carro("modelo", 2023, 1, 100.00);
		
		//Ação
		Locacao locacao = service.alugarCarro(cliente, carro);
		
		//Verificação
		// Irá ser esperado um valor verdadeiro 
		Assertions.assertTrue(locacao.getCarro().getEstoque() == 1);
		Assertions.assertTrue(ManipulandoDatas.boDatasIguais(locacao.getDataLocacao(), new Date()));
		Assertions.assertTrue(ManipulandoDatas.boDatasIguais(locacao.getDataRetorno(), ManipulandoDatas.novaDataComDiferencaDeDias(3)));
		
	}
	
	@Test
	@DisplayName("Exception - Estoque vazio - Modo Try/catch")
	public void exception01() {
		// Cenário
		Cliente cliente = new Cliente("Cliente 01");
		Carro carro = new Carro("modelo", 2023, 0, 100.00);
		
		// Ação 
		try {
			Locacao locacao = service.alugarCarro(cliente, carro);
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
		Cliente cliente = new Cliente("Cliente 01");
		Carro carro = new Carro("modelo", 2023, 0, 100.00);
		
		// Ação 
		LocacaoException e = Assertions.assertThrows(LocacaoException.class, () -> {
			service.alugarCarro(cliente, carro);
		}, "Deveria ter ocorrido uma exceção, mas não ocorreu.");
		// Verificação
		Assertions.assertEquals("Estoque vazio.", e.getMessage());
	}
	
	@Test
	@DisplayName("Exception - Carro nulo - Modo Try/catch")
	public void exception03() {
		// Cenário
		Cliente cliente = new Cliente("Cliente 01");
		Carro carro = null;
		
		// Ação 
		try {
			Locacao locacao = service.alugarCarro(cliente, carro);
			Assertions.fail("Deveria ter ocorrido uma exceção, mas não ocorreu.");
		} catch (LocacaoException e) {
			Assertions.assertEquals("Carro nulo.", e.getMessage());
		}
	}
	
	@Test
	@DisplayName("Exception - Carro nulo - Modo assertThrows")
	public void exception04() {
		// Cenário
		Cliente cliente = new Cliente("Cliente 01");
		Carro carro = null;
				
		// Ação 
		LocacaoException e = Assertions.assertThrows(LocacaoException.class, () -> {
			service.alugarCarro(cliente, null);
		}, "Deveria ter ocorrido uma exceção, mas não ocorreu.");
		// Verificação
		Assertions.assertEquals("Carro nulo.", e.getMessage());
	}
}
