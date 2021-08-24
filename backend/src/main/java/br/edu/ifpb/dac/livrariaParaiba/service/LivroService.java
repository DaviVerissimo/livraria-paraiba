package br.edu.ifpb.dac.livrariaParaiba.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.livrariaParaiba.model.Autor;
import br.edu.ifpb.dac.livrariaParaiba.model.Livro;
import br.edu.ifpb.dac.livrariaParaiba.repository.AutorRepositorio;
import br.edu.ifpb.dac.livrariaParaiba.repository.LivroRepository;

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
	@Autowired
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
	 * @return Boolean - informa se a ação foi bem sucedida (true) ou não (false)
	 */

	public boolean cadastrarLivro(Livro livroNovo) {
		List<Autor> autoresCadastrados = repositorioAutor.findAll();
		List<Autor> autoresLivro = livroNovo.getAutores();
		int aux = 0;
		for (Autor autor : autoresLivro) {
			for (Iterator iterator = autoresCadastrados.iterator(); iterator.hasNext();) {
				Autor autor2 = (Autor) iterator.next();
				if (autor2.equals(autor)) {
					aux++;
				}
			}
		}
		if (aux == autoresLivro.size()) {
			repositorioLivro.save(livroNovo);
			return true;
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
	 * @return Boolean - informa se a ação foi bem sucedida (true) ou não (false)
	 */

	public boolean editarLivro(Long idAntigo, Livro livroNovo) {
		Livro livroSalvo = repositorioLivro.findById(idAntigo).get();
		BeanUtils.copyProperties(livroNovo, livroSalvo);
		if (livroSalvo != null) {
			if (repositorioLivro.save(livroNovo) != null) {
				return true;
			}
		}
		return false;
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

	public Page<Livro> cincoLivrosMaisBaratos() {
		Integer pagina = 1;
		Page<Livro> paginaDeLivros = (Page<Livro>) repositorioLivro.findTop5(PageRequest.of(--pagina, 5));
		return paginaDeLivros;
	}

	public Page<Livro> retornarListaDeLivrosPaginada(String campoOrdenacao, Sort.Direction direcaoOrdenacao,
			Integer pagina, int qtdPagina) {
		Sort ordenacao = Sort.by(direcaoOrdenacao, campoOrdenacao);
		Page<Livro> paginaDeLivros = repositorioLivro.findAll(PageRequest.of(--pagina, qtdPagina, ordenacao));
		for (Livro livro : paginaDeLivros) {
			System.out.println(livro + "\n");
		}
		return paginaDeLivros;
	}

	public List<Livro> retornarLivrosPeloNome(String nome) {
		return repositorioLivro.findLivroByName(nome);
	}

	public Page<Livro> recuperarTodosOsLivros(int inicio, int tamanho) {
		return repositorioLivro.findAll(PageRequest.of(inicio, tamanho));
	}
}
