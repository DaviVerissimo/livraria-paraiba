package br.edu.ifpb.dac.livrariaParaiba.modelo;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import antlr.collections.List;

/**Modelo de Autor.
 * @author davi
 *
 */
@Entity
public class Autor {
	
	private String nome;
	
	@Id
	private long ID;
	
	private ArrayList<String> generos;
	
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

	public ArrayList<String> getGeneros() {
		return generos;
	}

	public void setGeneros(ArrayList<String> generos) {
		this.generos = generos;
	}
	
	
	
}
