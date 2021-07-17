package br.edu.ifpb.dac.livrariaParaiba.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.ifpb.dac.livrariaParaiba.model.Autor;
import br.edu.ifpb.dac.livrariaParaiba.service.AutorService;

@Controller
public class AutorController {

	@Autowired
	private AutorService autorService;

	@GetMapping("/autores/novo")
	public String autor(@ModelAttribute("autor") Autor autor) {
		return "autor/form";
	}
	
	@PostMapping("/autor/salvar")
	public String salvarAutor(@ModelAttribute("autor") Autor autor) {
		autorService.salvar(autor);
		return "redirect:/autores";
	}

	@GetMapping("/autores")
	public String autores(Model model) {
		model.addAttribute("listaAutores", autorService.retornarListaDeAutores());
		return "autor/index";
	}

	@GetMapping("/autor/{ID}")
	public String alterarAutor(@PathVariable("ID") long id, Model model) {
		Optional<Autor> autorOpt = Optional.ofNullable(autorService.pesquisarAutorPorID(id));
		if (autorOpt.isEmpty()) {
			throw new IllegalArgumentException("Autor inválido.");
		}
		model.addAttribute("autor", autorOpt.get());
		return "autor/form";
	}
	
	@GetMapping("/autor/excluir/{ID}")
	public String excluirAutor(@PathVariable ("ID") long id) {
		Optional<Autor> autorOpt = Optional.ofNullable(autorService.pesquisarAutorPorID(id));
		if (autorOpt.isEmpty()) {
			throw new IllegalArgumentException("Autor inválido.");
		}
		
		autorService.remove(autorOpt.get().getID());
		return "redirect:/autores";
	}
}
