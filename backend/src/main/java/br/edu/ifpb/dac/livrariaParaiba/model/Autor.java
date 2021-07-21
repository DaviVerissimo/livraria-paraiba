package br.edu.ifpb.dac.livrariaParaiba.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

/**
 * Modelo de Autor.
 * 
 * @author davi
 *
 */
@Entity
@Table(name = "autor")
public class Autor {

	@NotBlank
	private String nome;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long ID;

	@ManyToMany(mappedBy = "autores")
	private List<Livro> listaAssociados;

	public Autor() {

	}

	public Autor(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getID() {
		return ID;
	}

	public void setID(long ID) {
		this.ID = ID;
	}

	@Override
	public String toString() {
		return "Autor [nome=" + nome + ", ID=" + ID + "]";
	}

	public List<Livro> getListaAssociados() {
		return listaAssociados;
	}

	public boolean equals(Autor comparavel) {
		if (comparavel.getNome().equals(nome) && comparavel.getID() == ID) {
			return true;
		}
		return false;

	}

	public void setListaAssociados(List<Livro> listaAssociados) {
		this.listaAssociados = listaAssociados;
	}

}
