package br.edu.ifpb.dac.livrariaParaiba.controller;

import java.util.List;
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

import br.edu.ifpb.dac.livrariaParaiba.event.RecursoUser;
import br.edu.ifpb.dac.livrariaParaiba.model.Endereco;
import br.edu.ifpb.dac.livrariaParaiba.model.Usuario;
import br.edu.ifpb.dac.livrariaParaiba.service.EnderecoService;
import br.edu.ifpb.dac.livrariaParaiba.service.UsuarioService;

@Controller
public class EnderecoController {

	@Autowired
	private EnderecoService enderecoService;
	private RecursoUser recurso = new RecursoUser();
	@Autowired
	private UsuarioService clienteService;

	@GetMapping("user/enderecos")
	public String listarEnderecos(Model model) {
		String nome = recurso.getUsarname();
		Usuario c = clienteService.pesquisarPorEmail(nome);
		List<Endereco> lista = enderecoService.findByIdCliente(c.getId()); 
		model.addAttribute("enderecos", lista);
		return "endereco/index";
	}

	@GetMapping("user/endereco/novo")
	public String novoEndereco(@ModelAttribute("endereco") Endereco endereco) {
		return "endereco/form";
	}

	@PostMapping("user/endereco/salvar")
	public String salvarEndereco(@Valid @ModelAttribute("endereco") Endereco endereco, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "autor/form";
		}
		String nome = recurso.getUsarname();
		Usuario c = clienteService.pesquisarPorEmail(nome);
		//endereco.setCliente(c);
		enderecoService.salvarEndereco(endereco);
		return "redirect:/user/enderecos";
	}

	@GetMapping("/user/endereco/{id}")
	public String alterarEndereco(@Valid @PathVariable("id") long id, Model model) {
		Optional<Endereco> enderecoOpt = Optional.ofNullable(enderecoService.findById(id));
		if (enderecoOpt.isEmpty()) {
			throw new IllegalArgumentException("Endereço inválido.");
		}
		model.addAttribute("endereco", enderecoOpt.get());
		return "endereco/form";
	}

	@GetMapping("/user/endereco/excluir/{id}")
	public String excluirAutor(@PathVariable("id") long id) {
		Optional<Endereco> enderecoOpt = Optional.ofNullable(enderecoService.findById(id));
		if (enderecoOpt.isEmpty()) {
			throw new IllegalArgumentException("Endereço inválido.");
		}

		enderecoService.removerEnderecoPorId(enderecoOpt.get().getIdEnd());
		return "redirect:/user/enderecos";
	}

}
