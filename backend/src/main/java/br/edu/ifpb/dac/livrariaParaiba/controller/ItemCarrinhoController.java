package br.edu.ifpb.dac.livrariaParaiba.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.ifpb.dac.livrariaParaiba.model.Cliente;
import br.edu.ifpb.dac.livrariaParaiba.model.ItemCarrinho;
import br.edu.ifpb.dac.livrariaParaiba.service.ClienteService;
import br.edu.ifpb.dac.livrariaParaiba.service.ItemCarrinhoService;

@Controller
public class ItemCarrinhoController {
	
	@Autowired
	private ItemCarrinhoService service;
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("user/carrinho/{id}")
	public String carrinho(@PathVariable("id") long id, Model model) {
		Cliente cliente = clienteService.pesquisarPorId(id);
		model.addAttribute("carrinho", cliente.getCarrinhoDeCompras());
		return "carrinho/index";
	}
	
	@GetMapping("/user/carrinho/excluir/{id_item}")
	public String deletar(@PathVariable("id_item") long idItem, Model model) {
		Optional<ItemCarrinho> itemOpt = Optional.ofNullable(service.pesquisarId(idItem));
		if(itemOpt.isEmpty()) {
			throw new IllegalArgumentException("Item inválido");
		}
		service.removerItem(itemOpt.get().getId());
		return "redirect:/user/carrinho/"+1;
	}
	
}
