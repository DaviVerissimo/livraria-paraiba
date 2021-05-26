package br.edu.ifpb.dac.livrariaParaiba.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.livrariaParaiba.modelo.Cliente;
import br.edu.ifpb.dac.livrariaParaiba.repositorio.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private ClienteRepository repositorioCliente;
	
	
	@Override
	public Cliente salvarCliente(Cliente cliente) {
		return repositorioCliente.save(cliente);
	}

	@Override
	public void deletarCliente(long id) {
		repositorioCliente.deleteById(id);
	}

	@Override
	public void updateCliente(long id) {
		//repositorioCliente
	}

	@Override
	public List<Cliente> pesquisarTodosClientes() {
		return repositorioCliente.findAll();
	}

	@Override
	public Optional<Cliente> pesquisarPorId(long id) {
		return repositorioCliente.findById(id);
	}

	@Override
	public Cliente pesquisarPorEmail(String email) {
		return repositorioCliente.findByUsername(email);
	}

	

}
