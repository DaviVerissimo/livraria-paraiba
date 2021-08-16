package br.edu.ifpb.dac.livrariaParaiba.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/home/")
	public String iniciarHome() {
		
		return "home/index";
	}

}
