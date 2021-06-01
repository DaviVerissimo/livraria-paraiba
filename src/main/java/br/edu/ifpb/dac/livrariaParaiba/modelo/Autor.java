package br.edu.ifpb.dac.livrariaParaiba.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


/**Modelo de Autor.
 * @author davi
 *
 */
@Entity
@Table(name = "autor")
public class Autor implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String nome;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long ID;
	
	//@ManyToMany(mappedBy = "Autor") 
	//private List<String> generos;
	//private ArrayList<String> generos;
	
	//@ManyToMany
	//private List<Livro> listaLivros;
	
	public Autor() {
		
	}
	
	public Autor(String nome, String genero) {
		this.nome = nome;
		//generos.add(genero);
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


	
	
	
}
