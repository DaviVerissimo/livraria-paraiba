package br.edu.ifpb.dac.livrariaParaiba.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.livrariaParaiba.model.Cliente;
import br.edu.ifpb.dac.livrariaParaiba.repository.ClienteRepository;

/*
 * @author André Felipe
 */
@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repositorioCliente;

	/*
	 * Recebe um usuário cliente e salva no database
	 * 
	 * @param Cliente
	 * 
	 * @return Cliente
	 */
	public Cliente salvarCliente(Cliente cliente) {
		return repositorioCliente.save(cliente);
	}

	/*
	 * Deleta um usuário cliente pelo id
	 * 
	 * @param id 
	 */
	public void deletarCliente(long id) {
		repositorioCliente.deleteById(id);
	}

	/*
	 * Metodo que atualiza o usuário cliente Ele recebe um id e o objeto com as
	 * auterações O banco busca o cliente com o id passado e ocorre uma cópia de
	 * suas propriedades por meio do BeanUtils
	 */
	public void updateCliente(long id, Cliente cliente) {
		Cliente clienteSalvo = repositorioCliente.findById(id);
		BeanUtils.copyProperties(cliente, clienteSalvo);
		repositorioCliente.save(clienteSalvo);
	}

	/*
	 * Retorna todos os usuários clientes cadastrados
	 */
	public List<Cliente> pesquisarTodosClientes() {
		return repositorioCliente.findAll();
	}

	/*
	 * Retorna um usuário cliente pelo o id
	 */
	public Cliente pesquisarPorId(long id) {
		return repositorioCliente.findById(id);
	}

	/*
	 * Retorna um usuário cliente pelo email
	 */
	public Cliente pesquisarPorEmail(String email) {
		return repositorioCliente.findByUsername(email);
	}

}
