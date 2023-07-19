package br.ifpe.jaboatao.ts.builder;

import br.ifpe.jaboatao.ts.entidades.Filme;

public class FilmesBuilder {
	
	private Filme filme;
	
	private FilmesBuilder() {}
	
	public static FilmesBuilder umFilme() {
		FilmesBuilder builder = new FilmesBuilder();
		builder.filme = new Filme("Filme 01", 1, 11.0);
		return builder;
	}
	
	public Filme agora() {
		return filme;
	}
	
	public FilmesBuilder semEstoque() {
		filme.setEstoque(0);
		return this;
	}
	
	public FilmesBuilder valorLocacao(double valor) {
		filme.setValor(valor);
		return this;
	}
}
