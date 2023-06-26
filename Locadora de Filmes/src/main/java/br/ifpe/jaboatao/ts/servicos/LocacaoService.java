package br.ifpe.jaboatao.ts.servicos;

import java.util.Date;
import java.util.List;

import br.ifpe.jaboatao.ts.entidades.Filme;
import br.ifpe.jaboatao.ts.entidades.Locacao;
import br.ifpe.jaboatao.ts.entidades.Usuario;
import br.ifpe.jaboatao.ts.exceptions.FilmesException;

public class LocacaoService {

	public Locacao alugarFilme(List<Filme> filmes, Usuario usuario) throws FilmesException {

		if (usuario.getNome() == "") {
			throw new FilmesException("Usuario com nome vazio.");
		}
		if (filmes == null || filmes.isEmpty()) {
			throw new FilmesException("Filme nulo.");
		}

		for (Filme filme : filmes) {
			if (filme.getEstoque() == 0) {
				throw new FilmesException("Filme sem estoque.");
			}
		}

		Locacao locacao = new Locacao();
		locacao.setFilmes(filmes);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());

		double valorTotal = 0;
		/*for (Filme filme : filmes) {
			valorTotal += filme.getValor();
		}*/
		for (int i = 0; i < filmes.size(); i++) {
			double valorAtual = 0;
			Filme filme = filmes.get(i);
			valorAtual = filme.getValor();
			
			if(i == 1) {
				valorAtual = valorAtual * 0.90;
			}
			if(i == 2) {
				valorAtual = valorAtual * 0.85;
			}
			if(i == 3) {
				valorAtual = valorAtual * 0.75;
			}
			if(i >= 4) {
				valorAtual = valorAtual * 0.50;
			}
			valorTotal = valorTotal + valorAtual;
		}

		locacao.setValorLocacao(valorTotal);

		return locacao;

	}
}
