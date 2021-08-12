package br.edu.ifpb.dac.livrariaParaiba.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.edu.ifpb.dac.livrariaParaiba.model.FormaDePagamento;
import br.edu.ifpb.dac.livrariaParaiba.service.FormaDePagamentoService;

@Controller
public class FormaDePagamentoController {

	@Autowired
	private FormaDePagamentoService service;

	@GetMapping("/adm/pagamento/novo")
	public String novaForma(@ModelAttribute FormaDePagamento formaDePagamento) {
		return "formaPagamento/form";
	}

	@PostMapping("/adm/pagamento/salvar")
	public String salvarNovaForma(@Valid @ModelAttribute FormaDePagamento formaDePagamento,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "autor/form";
		}
		service.save(formaDePagamento);
		return "redirect:/adm/pagamento";
	}

	@GetMapping("/adm/pagamento/excluir/{id}")
	public String excluirForma(@PathVariable("id") long id) {
		Optional<FormaDePagamento> obj = Optional.ofNullable(service.findById(id));
		if (obj.isEmpty()) {
			throw new IllegalArgumentException("Forma de Pagamento inválida.");
		}

		service.deleteById(id);
		return "redirect:/adm/pagamento";
	}

	@GetMapping("/adm/pagamento")
	public String listaFormas(Model model) {
		model.addAttribute("formas", service.findAll());
		return "formaPagamento/index";
	}

}
