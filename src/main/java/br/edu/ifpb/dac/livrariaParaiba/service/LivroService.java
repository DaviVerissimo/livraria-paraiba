package br.edu.ifpb.dac.livrariaParaiba.service;

import java.math.BigDecimal;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

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
	 * Metodo de cadastro para livro com restrições de autor, ou seja, o livro só
	 * será cadastrado, caso o autor também esteja cadastrado no sistema.
	 * 
	 * 
	 * @author bruno
	 * 
	 * @param carrinho  Carrinho - carrinhos que o livro esta
	 * @param autores   List - Autores que escreveram o livro
	 * @param edicao    Integer - edicao de publicacao do Livro
	 * @param genero    String - classificacao do genero do Livro
	 * @param nome      String - nome do livro a ser cadastrado
	 * @param valor     BigDecimal - valor monetario do livro em Real Brasileiro
	 * @param descricao String - breve descricao do livro
	 * @param isbn      String - identificacao internacional do numero do livro
	 * @param nPaginas  Integer - numero de paginas que o livro contem
	 * 
	 * @return Boolean - indica se a ação foi bem sucedida
	 */

	public boolean cadastrarLivro(Integer quantidade, List<Autor> autores, Integer edicao,
			String genero, String nome, BigDecimal valor, String descricao, String isbn, Integer nPaginas) {
		List<Autor> autoresCadastrados = repositorioAutor.findAll();
		int aux = 0;
		for (int i = 0; i < autoresCadastrados.size(); i++) {
			if (autores.contains(autoresCadastrados.get(i))) {
				aux++;
				if (aux == autores.size()) {
					Livro novoLivro = new Livro( quantidade, autoresCadastrados, edicao, genero, nome, valor,
							descricao, isbn, nPaginas);
					repositorioLivro.save(novoLivro);
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Realiza a operacao de atualizacao dos atributos de uma entidade livro,
	 * verificando se ele existe no repositório antes de tudo, logo apos uma nova
	 * entidade do tipo livro sera cadastrada seguindo a logica do metodo save.
	 * 
	 * @author bruno
	 * 
	 * @param carrinho  Carrinho - carrinhos que o livro esta
	 * @param autores   List - Autores que escreveram o livro
	 * @param edicao    Integer - edicao de publicacao do Livro
	 * @param genero    String - classificacao do genero do Livro
	 * @param nome      String - nome do livro a ser cadastrado
	 * @param valor     BigDecimal - valor monetario do livro em Real Brasileiro
	 * @param descricao String - breve descricao do livro
	 * @param isbn      String - identificacao internacional do numero do livro
	 * @param nPaginas  Integer - numero de paginas que o livro contem
	 * 
	 */

	public void editarLivro(long id, Integer quantidade, List<Autor> autores, Integer edicao,
			String genero, String nome, BigDecimal valor, String descricao, String isbn, Integer nPaginas) {

		if (repositorioLivro.findById(id) != null) {
			Livro livroAtrAlterados = new Livro(id, quantidade, autores, edicao, genero, nome, valor,
					descricao, isbn, nPaginas);
			repositorioLivro.save(livroAtrAlterados);
		}

	}

	/**
	 * Retorna uma entidade do tipo livro de acordo com o id fornecido
	 * 
	 * @author bruno
	 * 
	 * @param id long - identificador da entidade livro
	 */
	public Optional<Livro> recuperarLivro(long id) {
		return repositorioLivro.findById(id);
	}

	/**
	 * Retorna a lista com todos os livros cadastrados
	 * 
	 * @author bruno
	 * 
	 */
	public List<Livro> recuperarTodosOsLivros() {
		return repositorioLivro.findAll();
	}

	/**
	 * Remove o livro fornecido
	 * 
	 * @author bruno
	 * 
	 * @param removido Livro - livro que sera removido
	 */
	public void removerLivro(Livro removido) {
		repositorioLivro.delete(removido);
	}

	/**
	 * Remove um livro do repositorio de acordo com o identificador fornecido
	 * 
	 * @author bruno
	 * 
	 * @param id long - identificador fornecido para a remocao
	 */
	public void removePorId(long id) {
		repositorioLivro.deleteById(id);
	}

	/**
	 * Busca os 5 livros mais baratos no repositorio e ordena-os de forna ascendente
	 * 
	 * @autor bruno
	 * 
	 */

	public List<Livro> cincoLivrosMaisBaratos() {
		return repositorioLivro.findTop5ByOrderByValorAsc();
	}
}
