package br.edu.ifpb.dac.livrariaParaiba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.ifpb.dac.livrariaParaiba.model.Autor;
import br.edu.ifpb.dac.livrariaParaiba.model.Livro;
import br.edu.ifpb.dac.livrariaParaiba.service.AutorService;
import br.edu.ifpb.dac.livrariaParaiba.service.LivroService;

@Controller
public class LivroController {
	@Autowired
	private LivroService servico;
	@Autowired
	private AutorService servicoAutor;

	@GetMapping("/livros")
	public String livroLista(Model modelo) {
		modelo.addAttribute("listaLivros",servico.recuperarTodosOsLivros());
		return "livro/livrosIndex";
	}

	@GetMapping("/livro/novo")
	public String livroCadastro(Model model) {
		model.addAttribute("listaAutores", servicoAutor.retornarListaDeAutores());
		model.addAttribute("livro", new Livro());
		return "livro/novoLivro";
	}
	
	@RequestMapping(value="/livro/salvar", params = {"addAutor"})
	public String addEndereco(Livro livro, BindingResult bindingResult, Model model) {
		
		return "redirect:livro/livrosIndex";
	}
}
