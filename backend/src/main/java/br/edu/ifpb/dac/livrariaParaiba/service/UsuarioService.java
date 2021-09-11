package br.edu.ifpb.dac.livrariaParaiba.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.livrariaParaiba.model.Usuario;
import br.edu.ifpb.dac.livrariaParaiba.repository.UsuarioRepository;


@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repositorioCliente;
	private PasswordEncoder encoder =  new BCryptPasswordEncoder();

	/**
	 * Recebe um usuário e salva no database
	 * 
	 * @param Usuario
	 * 
	 * @return Usuario
	 */
	public Usuario salvarCliente(Usuario usuario) {
		usuario.setSenha(encoder.encode(usuario.getSenha()));
		return repositorioCliente.save(usuario);
	}

	/**
	 * Deleta um usuário pelo id
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
	public void updateCliente(Long id, Usuario usuario) {
		Usuario usuarioSalvo = pesquisarPorId(id);
		if (usuarioSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		BeanUtils.copyProperties(usuario, usuarioSalvo);
		repositorioCliente.save(usuario);
	}

	/**
	 * Retorna todos os usuários clientes cadastrados
	 */
	public List<Usuario> pesquisarTodosClientes() {
		return repositorioCliente.findAll();
	}

	/**
	 * Retorna um usuário cliente pelo o id
	 */
	public Usuario pesquisarPorId(long id) {
		return repositorioCliente.findById(id);
	}

	/**
	 * Retorna um usuário cliente pelo email
	 */
	public Usuario pesquisarPorEmail(String email) {
		return repositorioCliente.findByNome(email);
	}

}
