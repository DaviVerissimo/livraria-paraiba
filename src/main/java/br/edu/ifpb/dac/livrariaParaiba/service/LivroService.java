package br.edu.ifpb.dac.livrariaParaiba.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.livrariaParaiba.modelo.Livro;
import br.edu.ifpb.dac.livrariaParaiba.repositorio.LivroRepository;

@Service
public interface LivroService {
	
	public void cadastrarLivro(Livro livro);
	public void editarLivro(Livro aSerEditado);
	public void removerLivro(Livro removido);
	
}
