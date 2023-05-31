package br.edu.ifpe.jaboatao.ts.servicos;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.edu.ifpe.jaboatao.ts.entidades.Carro;
import br.edu.ifpe.jaboatao.ts.entidades.Cliente;
import br.edu.ifpe.jaboatao.ts.entidades.Locacao;
import br.edu.ifpe.jaboatao.ts.utils.ManipulandoDatas;

public class LocacaoServiceTest {
	
	@Test
	public void primeiroTeste() {
		//System.out.println("Funcionando.");
		//Cenário
		LocacaoService service = new LocacaoService();
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
	
	
	
	
}
