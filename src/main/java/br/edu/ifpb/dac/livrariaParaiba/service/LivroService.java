package br.edu.ifpb.dac.livrariaParaiba.service;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.livrariaParaiba.modelo.Autor;
import br.edu.ifpb.dac.livrariaParaiba.modelo.Livro;
import br.edu.ifpb.dac.livrariaParaiba.repositorio.AutorRepositorio;
import br.edu.ifpb.dac.livrariaParaiba.repositorio.LivroRepository;

/**
 * Classe que representa o servico de persistencia da entidade Livro
 * 
 * @author bruno
 * 
 */
@Service
public class LivroService {

	@Autowired
	LivroRepository repositorioLivro;
	AutorRepositorio repositorioAutor;

	/**
	 * Metodo de cadastro para livro com restrições de autor, ou seja, o livro só será
	 * cadastrado, caso o autor também esteja cadastrado no sistema.
	 * 
	 * @author bruno
	 * 
	 * @param autores   List - Autores que escreveram o livro
	 * @param edicao    Integer - edicao de publicacao do Livro
	 * @param genero    String - classificacao do genero do Livro
	 * @param nome      String - nome do livro a ser cadastrado
	 * @param valor     BigDecimal - valor monetario do livro em Real Brasileiro
	 * @param descricao String - breve descricao do livro
	 * @param isbn      String - identificacao internacional do numero do livro
	 * @param nPaginas  Integer - numero de paginas que o livro contem
	 * 
	 * 
	 * @return Boolean - indica se a ação foi bem sucedida
	 */

	public boolean cadastrarLivro(List<Autor> autores, Integer edicao, String genero, String nome, BigDecimal valor,
			String descricao, String isbn, Integer nPaginas) {
		List<Autor> autoresCadastrados = repositorioAutor.findAll();
		int aux = 0;
		for (int i = 0; i < autoresCadastrados.size(); i++) {
			if (autores.contains(autoresCadastrados.get(i))) {
				aux++;
				if (aux == autores.size()) {
					Livro novoLivro = new Livro(autoresCadastrados, edicao, genero, nome, valor, descricao, isbn,
							nPaginas);
					repositorioLivro.save(novoLivro);
					return true;
				}
			}
		}
		return false;
	}

	public void removerLivro(Livro removido) {
		repositorioLivro.delete(removido);
	}

}
