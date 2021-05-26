package br.edu.ifpb.dac.livrariaParaiba.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import modelo.Livro;

/**Modelo de Autor.
 * @author davi
 *
 */
@Entity
@Table(name = "autor")
public class Autor {
	
	private String nome;
	
	@Id
	private long ID;
	
	private List<String> generos;
	
	@ManyToMany(mappedBy = "livro")
	private List<Livro> listaAssociados;
	
	public Autor() {
		generos = new ArrayList<>();
	}
	
	public Autor(String nome, long ID, String genero) {
		this.nome = nome;
		this.ID = ID;
		generos.add(genero);
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
		return "Autor [nome=" + nome + ", ID=" + ID +  "]";
	}

	public List<String> getGeneros() {
		return generos;
	}

	public void setGeneros(List<String> generos) {
		this.generos = generos;
	}

	public List<Livro> getListaAssociados() {
		return listaAssociados;
	}

	public void setListaAssociados(List<Livro> listaAssociados) {
		this.listaAssociados = listaAssociados;
	}

	
	
	
	
}
