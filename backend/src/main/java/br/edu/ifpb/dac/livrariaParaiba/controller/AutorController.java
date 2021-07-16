package br.edu.ifpb.dac.livrariaParaiba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.edu.ifpb.dac.livrariaParaiba.service.AutorService;

@Controller
public class AutorController {
	
	
	private AutorService autorService;
	
	public AutorController(AutorService autorService) {
		this.autorService = autorService;
	}
	
	@GetMapping("/autor")
	public String autor() {
		return "autor";
	} 
	@GetMapping("/autores")
	public String autores(Model model) {
		model.addAttribute("listaAutores", autorService.retornarListaDeAutores());
		return "autor/index";
	}
}
