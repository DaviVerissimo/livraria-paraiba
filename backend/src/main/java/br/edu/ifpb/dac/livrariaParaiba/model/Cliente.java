package br.edu.ifpb.dac.livrariaParaiba.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sun.istack.NotNull;

/**
 * @author André Felipe
 */
@Entity
@Table(name = "cliente")
public class Cliente extends Usuario implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	private String nome;

	@NotNull
	private String cpf;

	@NotNull
	@Temporal(TemporalType.DATE)
	private Date nascimento;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	private Set<Endereco> endereco = new HashSet<>();

	@OneToMany(mappedBy = "cliente")
	private Set<ItemCarrinho> carrinhoDeCompras = new HashSet<>();
	
	private String role;

	public void setEndereco(Set<Endereco> endereco) {
		this.endereco = endereco;
	}

	public Cliente() {

	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public Set<Endereco> getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco.add(endereco);
	}

	public Set<ItemCarrinho> getCarrinhoDeCompras() {
		return carrinhoDeCompras;
	}

	public void setCarrinhoDeCompras(Set<ItemCarrinho> carrinhoDeCompras) {
		this.carrinhoDeCompras = carrinhoDeCompras;
	}

	@Override
	public boolean validarLogin(String user, String senha) {
		if (user != null && user.length() > 0 && senha.length() > 8) {
			String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
			Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(user);
			if (matcher.matches()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isLogado() {
		return isStatus();
	}

	@Override
	public boolean verificarLogin(String user, String senha) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Adiciona um livro ao carrinho
	 */
	public void adicionarLivroAoCarrinho(ItemCarrinho livro) {
		carrinhoDeCompras.add(livro);
	}

	/**
	 * remove um livro do carrinho pelo id
	 */
	public boolean removerLivroDoCarrinho(long id) {
		for (ItemCarrinho l : carrinhoDeCompras) {
			if (l.getId() == id) {
				carrinhoDeCompras.remove(l);
				return true;
			}
		}
		return false;
	}

	/**
	 * Esvazeia o carrinho do usuario cliente
	 */
	public void esvaziarCarrinho() {
		carrinhoDeCompras.clear();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
