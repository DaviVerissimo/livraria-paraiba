package br.edu.ifpb.dac.livrariaParaiba.service;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.livrariaParaiba.model.Autor;
import br.edu.ifpb.dac.livrariaParaiba.repository.AutorRepositorio;


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
	public void remove(long ID) {
		 autorRepositorio.deleteById(ID);
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
	 * @param campoOrdenarPorAtributo   ordenar com base neste campo.
	 * @param direcaoOrdenacao Forma de ordenar (Ascedente, decrescente).
	 * @param numPagina        Numero da pagina solicitada.
	 * @param qtd_itensPorPagina Quantidade máxima de itens por pargina.
	 * @return
	 */
	public Page<Autor> retornarListaDeAutoresPaginada(String campoOrdenarPorAtributo, String ordemDoCampo,
			String pagina, int qtd_itensPorPagina) {		
		Sort.Direction direcaoOrdenar = Sort.Direction.ASC;
		switch (ordemDoCampo) {
			case "1":
				direcaoOrdenar = Sort.Direction.ASC;
				break;
		
			case "2":
				direcaoOrdenar = Sort.Direction.DESC;
				break;
			default:
				break;
		}
		Integer numPagina = Integer.parseInt(pagina);
		Sort ordenacao = Sort.by(direcaoOrdenar, campoOrdenarPorAtributo);
		Page<Autor> paginaDeAutores = autorRepositorio.findAll(PageRequest.of(--numPagina, qtd_itensPorPagina, ordenacao));
			
		return paginaDeAutores;
	}

	/**
	 * Edita o nome de um Autor.
	 * 
	 * @param novoNome Novo nome que o autor receberá.
	 * @param ID       Identificador do Autor a ser editado.
	 */
	public void editarAutor(Autor novoAutor, long idAntigo) {
		Autor autorSalvo = autorRepositorio.findByID(idAntigo);
		BeanUtils.copyProperties(novoAutor, autorSalvo);
		autorRepositorio.save(autorSalvo);
	}

	public Page<Autor> retornarListaDeAutoresPaginada(PageRequest requisicao) {
		Page<Autor> lista = autorRepositorio.findAll(requisicao);
		return lista;
	}

}
