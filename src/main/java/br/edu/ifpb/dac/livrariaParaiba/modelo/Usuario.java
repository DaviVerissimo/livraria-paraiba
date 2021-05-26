package br.edu.ifpb.dac.livrariaParaiba.modelo;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Usuario  {
	
	private String username, senha;
	private boolean status;
	
	public abstract boolean verificarLogin(String user, String senha);
	
	public abstract boolean isLogado();
	
	public abstract boolean validarLogin(String user, String senha);

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean estado) {
		this.status = estado;
	}

	
}
