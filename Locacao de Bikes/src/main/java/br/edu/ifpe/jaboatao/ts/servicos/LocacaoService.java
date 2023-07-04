package br.edu.ifpe.jaboatao.ts.servicos;

import java.util.Date;
import java.util.List;

import br.edu.ifpe.jaboatao.ts.entidades.Bicicleta;
import br.edu.ifpe.jaboatao.ts.entidades.Cliente;
import br.edu.ifpe.jaboatao.ts.entidades.Locacao;
import br.edu.ifpe.jaboatao.ts.exception.BikeException;
import br.edu.ifpe.jaboatao.ts.utils.ManipularDatas;

public class LocacaoService {
	
	public Locacao alugarBicicleta(Cliente cliente, List<Bicicleta> bicicletas) throws BikeException {
		
		if(cliente.getNome().equals("")) {
			throw new BikeException ("Cliente vazio");
		}
		
		if(bicicletas == null || bicicletas.isEmpty()) {
			throw new BikeException ("Bicicleta nula");
		}
		
		for (Bicicleta bicicleta : bicicletas) {
		if(bicicleta.getEstoque().equals(0)) {
			throw new BikeException ("Estoque vazio");
		}
	}
	
		Locacao locacao = new Locacao();
		locacao.setBicicletas(bicicletas);
		locacao.setCliente(cliente);
		locacao.setDataLocacao(new Date());
		double valorTotal = 0;
		/*for (Bicicleta bicicleta : bicicletas) {
			valorTotal += bicicleta.getValor();
		}*/
		
		// Implementando descontos 
		for (int i = 0; i < bicicletas.size(); i++) {
			double valorAtual = 0;
			Bicicleta bicicleta = bicicletas.get(i);
			valorAtual = bicicleta.getValor();
			
			if(i == 1) {
				valorAtual = valorAtual * 0.90;
			}
			if(i == 2) {
				valorAtual = valorAtual * 0.80;
			}
			if(i == 3) {
				valorAtual = valorAtual * 0.70;
			}
			if(i >= 4) {
				valorAtual = valorAtual * 0.50;
			}
			valorTotal = valorTotal + valorAtual;
		}
		
		locacao.setValorLocacao(valorTotal);
		
		//Definir a entrega para 3 dias depois.
		Date dataEntrega = ManipularDatas.novaDataComDiferencaDeDias(3);
		locacao.setDataRetorno(dataEntrega);
		
		//Salvando a locacao...	
		//O método salvar() será implementado com o avançar do curso.
		
		return locacao;
	}
}