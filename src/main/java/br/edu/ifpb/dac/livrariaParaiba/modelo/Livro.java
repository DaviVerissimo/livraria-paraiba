package br.edu.ifpb.dac.livrariaParaiba.modelo;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Livro {

	@ManyToMany
	private Autor autor;
}
