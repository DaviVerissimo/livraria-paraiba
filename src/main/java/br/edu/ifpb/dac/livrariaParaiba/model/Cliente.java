package br.edu.ifpb.dac.livrariaParaiba.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.MERGE)
	private List<Endereco> endereco = new ArrayList<Endereco>();

	@OneToMany(mappedBy = "cliente")
	private List<ItemCarrinho> carrinhoDeCompras = new ArrayList<ItemCarrinho>();

	public Cliente() {

	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

	public List<Endereco> getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco.add(endereco);
	}

	public List<ItemCarrinho> getCarrinhoDeCompras() {
		return carrinhoDeCompras;
	}

	public void setCarrinhoDeCompras(List<ItemCarrinho> carrinhoDeCompras) {
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

}
