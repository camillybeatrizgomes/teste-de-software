package br.ifpe.jaboatao.ts.builder;

import br.ifpe.jaboatao.ts.entidades.Usuario;

public class UsuarioBuilder {

	private Usuario usuario;
	
	private UsuarioBuilder() {}
	
	public static UsuarioBuilder umUsuario() {
		UsuarioBuilder builder = new UsuarioBuilder();
		builder.usuario = new Usuario("Usuario 01");
		return builder;
	}
	
	public static UsuarioBuilder usuarioVazio() {
		UsuarioBuilder builder = new UsuarioBuilder();
		builder.usuario = new Usuario("");
		return builder;
	}
	
	public Usuario agora() {
		return usuario;
	}
	
	public UsuarioBuilder usuarioNulo() {
		usuario.setNome(null);
		return this;
	}
	
	public UsuarioBuilder outroNome(String nome) {
		usuario.setNome(nome);
		return this;
	}
}
