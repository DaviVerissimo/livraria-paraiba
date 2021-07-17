package br.edu.ifpb.dac.livrariaParaiba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

	@GetMapping("/autores")
	public String autores(Model model) {
		model.addAttribute("listaAutores", autorService.retornarListaDeAutores());
		return "autor/index";
	}

	@PostMapping("/autor/salvar")
	public String salvarAutor(@ModelAttribute("autor") Autor autor) {
		autorService.salvar(autor);
		return "redirect:/autores";
	}
}
