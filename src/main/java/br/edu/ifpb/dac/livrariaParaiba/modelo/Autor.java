package br.edu.ifpb.dac.livrariaParaiba.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**Modelo de Autor.
 * @author davi
 *
 */
@Entity
public class Autor {
	
	private String nome;
	
	@Id
	private long ID;
	
	public Autor() {
		
	}
	
	public Autor(String nome, long ID) {
		this.nome = nome;
		this.ID = ID;
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
