package br.edu.ifpb.dac.livrariaParaiba.controller;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ifpb.dac.livrariaParaiba.model.Cliente;
import br.edu.ifpb.dac.livrariaParaiba.service.ClienteService;

public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	public Cliente salvarUsuario(Cliente cliente) {
		return clienteService.salvarCliente(cliente);
	}
}
