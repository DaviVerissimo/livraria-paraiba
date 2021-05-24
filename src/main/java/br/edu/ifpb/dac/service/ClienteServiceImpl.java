package br.edu.ifpb.dac.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.modelo.Cliente;
import br.edu.ifpb.dac.repositorio.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	private ClienteRepository repositorioCliente;
	
	@Autowired
	public ClienteServiceImpl(ClienteRepository cr) {
		this.repositorioCliente = cr;
	}
	
	@Override
	public Cliente salvarCliente(Cliente cliente) {
		return repositorioCliente.save(cliente);
	}

}
