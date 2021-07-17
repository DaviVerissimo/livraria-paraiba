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
	 * @param campoOrdenacao   ordenar com base neste campo.
	 * @param direcaoOrdenacao Forma de ordenar (Ascedente, decrescente).
	 * @param numPagina        Numero da pagina solicitada.
	 * @param qtdPagina        Quantidade máxima de itens por pargina.
	 * @return
	 */
	public Page<Autor> retornarListaDeAutoresPaginada(String campoOrdenacao, Sort.Direction direcaoOrdenacao,
			Integer pagina, int qtdPagina) {
		Scanner input = new Scanner(System.in);
		System.out.println("Lista de todos os produtos: \n");
		System.out.println("Deseja ordenar por qual campo? \n");
		String campoOrdenar = input.nextLine();
		campoOrdenacao = campoOrdenar;
		System.out.println("Crescente - 1 ou Decrescente - 2 \n");
		Integer direcaoEscolha = Integer.parseInt(input.nextLine());
		Sort.Direction direcaoOrdenar = Sort.Direction.ASC;
		switch (direcaoEscolha) {
			case 1:
				direcaoOrdenar = Sort.Direction.ASC;
				break;
		
			case 2:
				direcaoOrdenar = Sort.Direction.DESC;
				break;
			default:
				break;
		}
		
		direcaoOrdenacao = direcaoOrdenar;
		System.out.println("Qual página? \n");
		Integer numPagina = Integer.parseInt(input.nextLine());
		qtdPagina = 3;
		Sort ordenacao = Sort.by(direcaoOrdenacao, campoOrdenacao);
		Page<Autor> paginaDeAutores = autorRepositorio.findAll(PageRequest.of(--numPagina, qtdPagina, ordenacao));
		for (Autor autor : paginaDeAutores) {
			System.out.println(autor +"\n");
		}
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

}
