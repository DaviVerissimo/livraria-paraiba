package br.edu.ifpb.dac.livrariaParaiba.modelo;

import java.io.Serializable;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
/*
 * @author André Felipe
 */
@Entity
public class Carrinho implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne (mappedBy = "carrinhoDeCompras")
	private Cliente cliente;
	@ManyToMany
	private List<Livro> lista;
	public Carrinho() {
		
	}
	public List<Livro> getLista() {
		return lista;
	}

	public void setLista(List<Livro> lista) {
		this.lista = lista;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/*
	 * Adiciona um livro ao carrinho
	 */
	public void adicionarLivroAoCarrinho(Livro livro) {
		lista.add(livro);
	}
	
	/*
	 * remove um livro do carrinho pelo id
	 */
	public boolean removerLivroDoCarrinho(long id) {
		for(Livro l: lista) {
			if(l.getId()==id) {
				lista.remove(l);
				return true;
			}
		}
		return false;
	}
	/*
	 * Esvazeia o carrinho do usuario cliente
	 */
	public void esvaziarCarrinho() {
		lista.clear();
	}
}
