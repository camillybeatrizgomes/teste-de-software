package br.edu.ifpe.jaboatao.ts.builder;

import br.edu.ifpe.jaboatao.ts.entidades.Cliente;

public class ClienteBuilder {
	
	private Cliente cliente;
	
	private ClienteBuilder () {}
	
	public static ClienteBuilder umCliente() {
		ClienteBuilder builder = new ClienteBuilder();
		builder.cliente = new Cliente("Cliente 01");
		return builder;
	}
	
	public Cliente agora() {
		return cliente;
	}
	
	public ClienteBuilder clienteNulo() {
		cliente.setNome(null);
		return this;
	}
	
	public ClienteBuilder outroNome(String nome) {
		cliente.setNome(nome);
		return this;
	}
}
