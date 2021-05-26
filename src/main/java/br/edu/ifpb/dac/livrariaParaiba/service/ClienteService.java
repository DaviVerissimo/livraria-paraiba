package br.edu.ifpb.dac.livrariaParaiba.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.livrariaParaiba.modelo.Cliente;
import br.edu.ifpb.dac.livrariaParaiba.repositorio.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repositorioCliente;
	
	
	public Cliente salvarCliente(Cliente cliente) {
		return repositorioCliente.save(cliente);
	}

	public void deletarCliente(long id) {
		repositorioCliente.deleteById(id);
	}

	public void updateCliente(long id) {
		//repositorioCliente
	}

	public List<Cliente> pesquisarTodosClientes() {
		return repositorioCliente.findAll();
	}

	public Optional<Cliente> pesquisarPorId(long id) {
		return repositorioCliente.findById(id);
	}

	public Cliente pesquisarPorEmail(String email) {
		return repositorioCliente.findByUsername(email);
	}

	

}
