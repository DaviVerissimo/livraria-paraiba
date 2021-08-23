package br.edu.ifpb.dac.livrariaParaiba.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.livrariaParaiba.model.Cliente;
import br.edu.ifpb.dac.livrariaParaiba.model.Role;
import br.edu.ifpb.dac.livrariaParaiba.repository.ClienteRepository;

/**
 * @author André Felipe
 */
@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repositorioCliente;
	private PasswordEncoder encoder =  new BCryptPasswordEncoder();

	/**
	 * Recebe um usuário cliente e salva no database
	 * 
	 * @param Cliente
	 * 
	 * @return Cliente
	 */
	public Cliente salvarCliente(Cliente cliente) {
		cliente.setRole(Role.USER.getNome());
		cliente.setSenha(encoder.encode(cliente.getSenha()));
		return repositorioCliente.save(cliente);
	}

	/**
	 * Deleta um usuário cliente pelo id
	 * 
	 * @param id
	 */
	public void deletarCliente(long id) {
		repositorioCliente.deleteById(id);
	}

	/**
	 * Metodo que atualiza o usuário cliente Ele recebe um id e o objeto com as
	 * auterações O banco busca o cliente com o id passado e ocorre uma cópia de
	 * suas propriedades por meio do BeanUtils
	 */
	public void updateCliente(Long id, Cliente cliente) {
		Cliente clienteSalvo = pesquisarPorId(id);
		if (clienteSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(cliente, clienteSalvo);
		repositorioCliente.save(cliente);
	}

	/**
	 * Retorna todos os usuários clientes cadastrados
	 */
	public List<Cliente> pesquisarTodosClientes() {
		return repositorioCliente.findAll();
	}

	/**
	 * Retorna um usuário cliente pelo o id
	 */
	public Cliente pesquisarPorId(long id) {
		return repositorioCliente.findById(id);
	}

	/**
	 * Retorna um usuário cliente pelo email
	 */
	public Cliente pesquisarPorEmail(String email) {
		return repositorioCliente.findByUsername(email);
	}

}
