package br.edu.ifpb.dac.livrariaParaiba.modelo;

import java.util.List;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
/*
 * @author André Felipe
 */
@Embeddable
public class Carrinho {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; 
	@ManyToMany (mappedBy = "carrinho")
	private List<Livro> lista;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Livro> getLista() {
		return lista;
	}

	public void setLista(List<Livro> lista) {
		this.lista = lista;
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
