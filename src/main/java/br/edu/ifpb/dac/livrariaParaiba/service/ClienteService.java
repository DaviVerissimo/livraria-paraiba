package br.edu.ifpb.dac.livrariaParaiba.service;

import java.util.List;

import java.util.Optional;
import br.edu.ifpb.dac.livrariaParaiba.modelo.Cliente;

public interface ClienteService {
	
	
	public Cliente salvarCliente(Cliente cliente);
	public void deletarCliente(long id);
	public void updateCliente(long id);
	public List<Cliente> pesquisarTodosClientes();
	public Optional<Cliente> pesquisarPorId(long id);
	public Cliente pesquisarPorEmail(String email);

}
