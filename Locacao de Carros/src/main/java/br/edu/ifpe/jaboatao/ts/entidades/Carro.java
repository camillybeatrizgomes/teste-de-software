package br.edu.ifpe.jaboatao.ts.entidades;

public class Carro {
	private String modelo;
	private Integer ano;
	private Integer estoque;
	private Double valor; 
	
	public Carro() {}

	public Carro(String modelo, Integer ano, Integer estoque, Double valor) {
		this.modelo = modelo;
		this.ano = ano;
		this.estoque = estoque;
		this.valor = valor;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	
}