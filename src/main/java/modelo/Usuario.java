package modelo;

public abstract class Usuario  {

	private String username, senha;
	private boolean estado;
	
	public abstract boolean verificarLogin(String user, String senha);
	
	public abstract boolean isLogado();

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

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	
}
