package br.edu.ifpe.jaboatao.ts.servicos;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import br.edu.ifpe.jaboatao.ts.builder.BicicletaBuilder;
import br.edu.ifpe.jaboatao.ts.builder.ClienteBuilder;
import br.edu.ifpe.jaboatao.ts.entidades.Bicicleta;
import br.edu.ifpe.jaboatao.ts.entidades.Cliente;
import br.edu.ifpe.jaboatao.ts.entidades.Locacao;
import br.edu.ifpe.jaboatao.ts.exception.BikeException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class LocacaoServiceTest {
	LocacaoService service;
	Locacao locacao;
	
	@BeforeEach
	public void setup() {
		service = new LocacaoService();
		locacao = new Locacao();
	}
	
	@Test
	@DisplayName("Estoque vazio - Modo Try/Catch")
	@Order(2)
	public void exception01() {
		// Cenário

		Cliente cliente = ClienteBuilder.umCliente().agora();
		List<Bicicleta> bicicletas = Arrays.asList(BicicletaBuilder.umaBicicleta().semEstoque().agora());
		
		try {
			// Ação
			locacao = service.alugarBicicleta(cliente, bicicletas);
			Assertions.fail("Deveria ter ocorrido uma exceção");
		} catch (Exception e) {
			// Verificação
			Assertions.assertEquals("Estoque vazio", e.getMessage());
		}
	}
	
	@Test
	@DisplayName("Estoque vazio - Modo assertThows")
	@Order(1)
	public void exception02() throws Exception {
		// Cenário
		Cliente cliente = ClienteBuilder.umCliente().agora();
		List<Bicicleta> bicicletas = Arrays.asList(BicicletaBuilder.umaBicicleta().semEstoque().agora());
		
		// Ação
		Exception e = Assertions.assertThrows(Exception.class, () -> {
			service.alugarBicicleta(cliente, bicicletas);
		},"Deveria ter ocorrido uma exceção");
		// Verificação
		Assertions.assertEquals("Estoque vazio", e.getMessage());
	}
	
	@Test
	@DisplayName("Bicicleta nula - Modo Try/Catch")
	public void exception03() {
		// Cenário

		Cliente cliente = ClienteBuilder.umCliente().agora();
		List<Bicicleta> bicicletas = Arrays.asList(BicicletaBuilder.bicicletaNula().agora());
		
		// Ação
		try {
			locacao = service.alugarBicicleta(cliente, null);
			Assertions.fail("Deveria ter ocorrido uma exceção");
		} catch (Exception e) {
			// Verificação
			Assertions.assertEquals("Bicicleta nula", e.getMessage());
		}
	}
	
	@Test
	@DisplayName("Bicicleta nula - Modo assertThrows")
	public void exception04() {
		// Cenário

		Cliente cliente = ClienteBuilder.umCliente().outroNome("Cliente 02").agora();
		List<Bicicleta> bicicletas = Arrays.asList(BicicletaBuilder.bicicletaNula().agora());
		
		// Ação
		Exception e = Assertions.assertThrows(Exception.class,() -> {
			service.alugarBicicleta(cliente, null);
		}, "Deveria ter ocorrido uma exceção");
	}
	

	@Test
	@DisplayName("Cliente vazio - Modo Try/Catch")
	public void exception05() {
		// Cenário

		Cliente cliente = ClienteBuilder.clienteVazio().agora();
		List<Bicicleta> bicicletas = Arrays.asList(BicicletaBuilder.umaBicicleta().agora());
		
		// Ação
		try {
			locacao = service.alugarBicicleta(cliente, bicicletas);
			Assertions.fail("Deveria ter ocorrido uma exceção");
		} catch (Exception e) {
			// Verificação
			Assertions.assertEquals("Cliente vazio", e.getMessage());
		}
	}
	
	@Test
	@DisplayName("Cliente vazio - Modo assertThrows")
	public void exception06() {
		// Cenário

		Cliente cliente = ClienteBuilder.umCliente().outroNome("").agora();
		List<Bicicleta> bicicletas = Arrays.asList(BicicletaBuilder.umaBicicleta().valorLocacao(20.0).agora());
		
		// Ação 
		Exception e = Assertions.assertThrows(Exception.class, () -> {
			service.alugarBicicleta(cliente, bicicletas);
		}, "Deveria ter ocorrido uma exceção");
		
		// Verificação 
		Assertions.assertEquals("Cliente vazio", e.getMessage());
	}
	
	@Test
	@DisplayName("Valor total da locação de 5 bicicletas")
	@Disabled
	public void valorLocacao() {
		Cliente cliente = ClienteBuilder.umCliente().agora();
		List<Bicicleta> bicicletas = Arrays.asList(BicicletaBuilder.umaBicicleta().agora(),
													BicicletaBuilder.umaBicicleta().agora(),
													BicicletaBuilder.umaBicicleta().agora(),
													BicicletaBuilder.umaBicicleta().agora(),
													BicicletaBuilder.umaBicicleta().agora());
		
		// Ação
		try {
			locacao = service.alugarBicicleta(cliente, bicicletas);
		} catch (BikeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assertions.assertEquals(250.0, locacao.getValorLocacao());
	}
	
	@Disabled // Desabilitado
	@Test
	@DisplayName("Bicicletas sem estoque")
	public void bicicletasSemEstoque() throws BikeException {
		Cliente cliente = ClienteBuilder.umCliente().agora();
		List<Bicicleta> bicicletas = Arrays.asList(BicicletaBuilder.umaBicicleta().agora(),
													BicicletaBuilder.umaBicicleta().agora(),
													BicicletaBuilder.umaBicicleta().semEstoque().agora());
		
		// Ação
		Exception e = Assertions.assertThrows(Exception.class, () -> {
			service.alugarBicicleta(cliente, bicicletas);
		},"Deveria ter ocorrido uma exceção");
		
		// Verificação
		Assertions.assertEquals("Estoque vazio", e.getMessage());
	}
	
	@Test
	@DisplayName("10% de desconto na 2º bicicleta")
	public void desconto01() throws BikeException {
		Cliente cliente = ClienteBuilder.umCliente().agora();
		List<Bicicleta> bicicletas = Arrays.asList(BicicletaBuilder.umaBicicleta().agora(),
													BicicletaBuilder.umaBicicleta().agora()); 
		
		// Ação
		locacao = service.alugarBicicleta(cliente, bicicletas);
		
		// Verificação
		Assertions.assertEquals(95.0, locacao.getValorLocacao());
	}
	
	@Test
	@DisplayName("20% de desconto na 3º bicicleta")
	public void desconto02() throws BikeException {
		Cliente cliente = new Cliente("Cliente 01");
		List<Bicicleta> bicicletas = Arrays.asList(BicicletaBuilder.umaBicicleta().agora(),
													BicicletaBuilder.umaBicicleta().agora(),
													BicicletaBuilder.umaBicicleta().agora());
		
		// Ação 
		locacao = service.alugarBicicleta(cliente, bicicletas);
		
		// Verificação
		Assertions.assertEquals(135.0, locacao.getValorLocacao());
	}
	
	@Test
	@DisplayName("30% de desconto na 4º bicicleta")
	public void desconto03() throws BikeException {
		Cliente cliente = new Cliente("Cliente 01");
		List<Bicicleta> bicicletas = Arrays.asList(BicicletaBuilder.umaBicicleta().agora(),
													BicicletaBuilder.umaBicicleta().agora(),
													BicicletaBuilder.umaBicicleta().agora(),
													BicicletaBuilder.umaBicicleta().agora());
		
		// Ação
		locacao = service.alugarBicicleta(cliente, bicicletas);
		
		// Verificação
		Assertions.assertEquals(170, locacao.getValorLocacao());
	}
	
	@Test
	@DisplayName("50% de desconto na 5º bicicleta ou mais")
	public void desconto04() throws BikeException {
		Cliente cliente = new Cliente("Cliente 01");
		List<Bicicleta> bicicletas = Arrays.asList(BicicletaBuilder.umaBicicleta().agora(),
													BicicletaBuilder.umaBicicleta().agora(),
													BicicletaBuilder.umaBicicleta().agora(),
													BicicletaBuilder.umaBicicleta().agora(),
													BicicletaBuilder.umaBicicleta().agora());
		
		// Ação 
		locacao = service.alugarBicicleta(cliente, bicicletas);
		
		// Verificação
		Assertions.assertEquals(195.0, locacao.getValorLocacao());
	}
}
