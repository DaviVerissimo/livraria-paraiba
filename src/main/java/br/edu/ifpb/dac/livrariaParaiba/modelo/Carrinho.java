package br.edu.ifpb.dac.livrariaParaiba.modelo;

import java.util.ArrayList;

import javax.persistence.Embeddable;
import javax.persistence.ManyToMany;

@Embeddable
public class Carrinho {

	@ManyToMany
	private ArrayList<Livro> lista;
	
	public void adicionarLivroAoCarrinho(Livro livro) {
		lista.add(livro);
	}
	
	public boolean removerLivroDoCarrinho(long id) {
		for(Livro l: lista) {
			if(l.getId()==id) {
				lista.remove(l);
				return true;
			}
		}
		return false;
	}
}
