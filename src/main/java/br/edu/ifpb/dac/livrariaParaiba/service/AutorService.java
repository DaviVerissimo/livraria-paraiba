package br.edu.ifpb.dac.livrariaParaiba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.livrariaParaiba.modelo.Autor;
import br.edu.ifpb.dac.livrariaParaiba.repositorio.AutorRepositorio;

/**
 * Gerencia Autor.
 * 
 * @author davi
 */
@Service
public class AutorService {

	@Autowired
	private AutorRepositorio autorRepositorio;

	/**
	 * Salva um Autor no sistema.
	 * 
	 * @param nomeDoAutor Nome do Autor.
	 * @param ID          Identificador único do Autor.
	 */
	public void salvar(Autor novoAutor) {
		autorRepositorio.save(novoAutor);
	}

	/**
	 * remove um Autor do sistema
	 * 
	 * @param ID Identificador único do Autor.
	 * @return
	 */
	public Autor remove(String ID) {
		// Autor autor =
		// autorRepositorio.
		return null;
	}

	/**
	 * Busca e retorna um Autor pelo nome.
	 * 
	 * @param nome Nome do Autor.
	 * @return
	 */
	public Autor pesquisarAutorPorNome(String nome) {
		Autor autor = autorRepositorio.findUniqueByNome(nome);

		return autor;
	}

	/**
	 * Busca e retorna um Autor pelo ID.
	 * 
	 * @param ID do Autor.
	 * @return
	 */
	public Autor pesquisarAutorPorID(long ID) {
		Autor autor = autorRepositorio.findByID(ID);

		return autor;
	}

	/**
	 * @return Retorna uma lista com todos os Autores.
	 */
	public List<Autor> retornarListaDeAutores() {
		List<Autor> listaDeAutores = autorRepositorio.findAll();
		return listaDeAutores;
	}

	/**
	 * retorna uma pagina com quantidade definida de Autores.
	 * 
	 * @param campoOrdenacao   ordenar com base neste campo.
	 * @param direcaoOrdenacao Forma de ordenar (Ascedente, decrescente).
	 * @param numPagina        Numero da pagina solicitada.
	 * @param qtdPagina        Quantidade máxima de itens por pargina.
	 * @return
	 */
	public Page<Autor> retornarListaDeAutoresPaginada(String campoOrdenacao, Sort.Direction direcaoOrdenacao,
			Integer numPagina, int qtdPagina) {
		Sort ordenacao = Sort.by(direcaoOrdenacao, campoOrdenacao);
		Page<Autor> paginaDeAutores = autorRepositorio.findAll(PageRequest.of(--numPagina, qtdPagina, ordenacao));

		return paginaDeAutores;
	}

	/**
	 * Edita o nome de um Autor.
	 * 
	 * @param novoNome Novo nome que o autor receberá.
	 * @param ID       Identificador do Autor a ser editado.
	 */
	public void editarAutor(String novoNome, long ID, String genero) {
		Autor autor = this.pesquisarAutorPorID(ID);
		autor.setNome(novoNome);
		autorRepositorio.save(autor);
	}

}
