package br.edu.ifpb.dac.livrariaParaiba.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.livrariaParaiba.modelo.Livro;
import br.edu.ifpb.dac.livrariaParaiba.repositorio.LivroRepository;

@Service
public class LivroService {

	@Autowired
	LivroRepository repositorioLivro;

	public void cadastrarOuAtualizarLivro(Livro livro) {

		repositorioLivro.save(livro);
	}

	public void removerLivro(Livro removido) {
		repositorioLivro.delete(removido);
	}

}
