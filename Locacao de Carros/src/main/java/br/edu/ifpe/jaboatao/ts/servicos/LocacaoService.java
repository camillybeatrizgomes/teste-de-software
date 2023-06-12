package br.edu.ifpe.jaboatao.ts.servicos;

//br.edu.ifpe.jaboata.ts.servicos
import java.util.Date;
import java.util.List;

import br.edu.ifpe.jaboatao.ts.entidades.Carro;
import br.edu.ifpe.jaboatao.ts.entidades.Cliente;
import br.edu.ifpe.jaboatao.ts.entidades.Locacao;
import br.edu.ifpe.jaboatao.ts.exceptions.LocacaoException;
import br.edu.ifpe.jaboatao.ts.utils.ManipulandoDatas;

public class LocacaoService {

	public Locacao alugarCarro(Cliente cliente, List<Carro> carros) throws LocacaoException {
		if (carros == null || carros.isEmpty())  {
			throw new LocacaoException("Carro nulo.");
		}
		
		for (Carro carro : carros) {
			if(carro.getEstoque().equals(0)) {
				throw new LocacaoException("Estoque vazio.");
			}
		}
		
		Locacao locacao = new Locacao();
		locacao.setCarros(carros);
		locacao.setCliente(cliente);
		locacao.setDataLocacao(new Date());
		double valorTotal = 0;
		// Valor total de todos os carros da lista 
//		for (Carro carro : carros) { 
//			valorTotal += carro.getValor();
//		}
		
		for (int i = 0; i < carros.size(); i++) {
			double varValorAtual = 0;
			Carro carro = carros.get(i);
			varValorAtual = carro.getValor();
			
			if(i == 1) { // Se for o segundo carro, aplique o desconto de 10%
				varValorAtual = carro.getValor() * 0.90;
			}
			if(i == 2) {
				varValorAtual = carro.getValor() * 0.85;
			}
			if(i == 3) {
				varValorAtual = carro.getValor() * 0.80;
			}
			if(i >= 4) {
				varValorAtual = carro.getValor() * 0.75;
			}
			valorTotal = valorTotal + varValorAtual;
		}
		locacao.setValorLocacao(valorTotal);

		// Definir a entrega para 3 dias depois.
		Date dataEntrega = ManipulandoDatas.novaDataComDiferencaDeDias(3);
		locacao.setDataRetorno(dataEntrega);

		// Salvando a locacao...
		// O método salvar() será implementado com o avançar do curso.

		return locacao;
	}
}