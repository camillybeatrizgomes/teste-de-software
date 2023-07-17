package br.edu.ifpe.jaboatao.ts.builder;

import br.edu.ifpe.jaboatao.ts.entidades.Carro;

public class CarroBuilder {
	
	private Carro carro;
	
	private CarroBuilder() {}
	
	public static CarroBuilder umCarro() {
		CarroBuilder builder = new CarroBuilder();
		builder.carro = new Carro("Carro 01", 2023, 1, 50.00);
		return builder;
	}
	
	public Carro agora() {
		return carro;
	}
	
	public CarroBuilder semEstoque() {
		carro.setEstoque(0);
		return this;
	}
	
	public CarroBuilder valorLocacao(double valor) {
		carro.setValor(valor);
		return this;
	}
	
	public static CarroBuilder carroNulo() {
		CarroBuilder builder = new CarroBuilder();
		builder.carro = null;
		return builder;
	}
}
