package br.edu.ifpe.jaboatao.ts.builder;

import br.edu.ifpe.jaboatao.ts.entidades.Bicicleta;

public class BicicletaBuilder {

	private Bicicleta bicicleta;
	
	private BicicletaBuilder() {}
	
	public static BicicletaBuilder umaBicicleta() {
		BicicletaBuilder builder = new BicicletaBuilder();
		builder.bicicleta = new Bicicleta("Modelo 01", 1, 50.0);
		return builder;
	}
	
	public Bicicleta agora() {
		return bicicleta;
	}
	
	public BicicletaBuilder valorLocacao(double valor) {
		bicicleta.setValor(valor);
		return this;
	}
	
	public BicicletaBuilder semEstoque() {
		bicicleta.setEstoque(0);
		return this;
	}
	
	public static BicicletaBuilder bicicletaNula() {
		BicicletaBuilder builder = new BicicletaBuilder();
		builder.bicicleta = null;
		return builder;
	}
	
}
