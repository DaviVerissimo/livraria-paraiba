package br.edu.ifpb.dac.livrariaParaiba.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import com.sun.istack.NotNull;


/**
 * @author davi
 *
 */
@Entity
public class Usuario {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull
	private String nome;
	private String senha;
	private String email;
	
//	@NotNull
//	private String cpf;
//
//	@NotNull
//	private String telefone;
	

//	@ManyToMany(fetch = FetchType.EAGER)
//	private List<Perfil> perfis;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public List<Perfil> getPerfis() {
//		return perfis;
//	}
//
//	public void setPerfis(List<Perfil> perfis) {
//		this.perfis = perfis;
//	}
	

}
