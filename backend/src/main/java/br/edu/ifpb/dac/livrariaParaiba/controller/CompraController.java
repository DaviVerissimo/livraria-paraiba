package br.edu.ifpb.dac.livrariaParaiba.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.ifpb.dac.livrariaParaiba.event.RecursoUser;
import br.edu.ifpb.dac.livrariaParaiba.model.Cliente;
import br.edu.ifpb.dac.livrariaParaiba.model.Compra;
import br.edu.ifpb.dac.livrariaParaiba.model.Endereco;
import br.edu.ifpb.dac.livrariaParaiba.service.ClienteService;
import br.edu.ifpb.dac.livrariaParaiba.service.CompraService;

@Controller
public class CompraController {

	@Autowired
	private CompraService compraService;
	@Autowired
	private ClienteService clienteService;

	private RecursoUser recurso = new RecursoUser();

	@PostMapping("/user/pedido/finalizar")
	public String finalizarCompra(@ModelAttribute("compra") Compra compra) {
		compraService.salvarCompra(compra);
		return "redirect:/home";
	}

	@GetMapping("/user/pedido")
	public String formPedido(Model model) {
		String nome = recurso.getUsarname();
		Cliente c = clienteService.pesquisarPorEmail(nome);
		Set<Endereco> list = c.getEndereco();
		model.addAttribute("enderecos", list);
		return "pedido/index";
	}

}