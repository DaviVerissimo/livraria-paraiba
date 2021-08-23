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

import br.edu.ifpb.dac.livrariaParaiba.event.RecursoUser;
import br.edu.ifpb.dac.livrariaParaiba.model.Cliente;
import br.edu.ifpb.dac.livrariaParaiba.model.Role;
import br.edu.ifpb.dac.livrariaParaiba.service.ClienteService;

@Controller
public class UsuarioController {

	@Autowired
	private ClienteService clienteService;
	RecursoUser recursoUser = new RecursoUser();

	@GetMapping("usuario/novo")
	public String novoUsuario(@ModelAttribute("usuario") Cliente cliente) {
		return "usuario/form";
	}

	@PostMapping("usuario/salvar")
	public String salvarUsuario(@Valid @ModelAttribute("usuario") Cliente cliente, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "usuario/form";
		}
		clienteService.salvarCliente(cliente);
		return "redirect:/";
	}

	@GetMapping("user/perfil")
	public String perfilUsuario(Model model) {
		String email = recursoUser.getUsarname();
		Cliente c = clienteService.pesquisarPorEmail(email);

		model.addAttribute("usuario", c);
		return "usuario/perfil";
	}
	@GetMapping("user/usuario/{id}")
	public String editarUsuario(@Valid @PathVariable("id") long id, Model model) {
		Optional<Cliente> clienteOpt = Optional.ofNullable(clienteService.pesquisarPorId(id));
		if(clienteOpt.isEmpty()) {
			throw new IllegalArgumentException("Usuário inválido");
		}
		model.addAttribute("usuario", clienteOpt.get());
		return "usuario/form";
	}
	
	@GetMapping("user/usuario/exluir/{id}")
	public String removerUsuario(@Valid @PathVariable("id") long id, Model model) {
		Optional<Cliente> clienteOpt = Optional.ofNullable(clienteService.pesquisarPorId(id));
		if(clienteOpt.isEmpty()) {
			throw new IllegalArgumentException("Usuário inválido");
		}
		clienteService.deletarCliente(id);
		return "redirect:/";
	}
}
