package br.edu.ifpb.dac.livrariaParaiba.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.dac.livrariaParaiba.event.RecursoCriadoEvent;
import br.edu.ifpb.dac.livrariaParaiba.model.Usuario;
import br.edu.ifpb.dac.livrariaParaiba.service.UsuarioService;

/**
 * 
 * @author andré
 *
 */
@RestController
@RequestMapping("/cliente")
public class UsuarioClienteController {

	@Autowired
	private UsuarioService clienteService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	public ResponseEntity<List<Usuario>> listarAll() {
		List<Usuario> list = clienteService.pesquisarTodosClientes();
		return ResponseEntity.ok().body(list);
	}

	@PostMapping
	public ResponseEntity<Usuario> save(@Validated @RequestBody Usuario cliente, HttpServletResponse response) {
		Usuario clienteSalvo = clienteService.salvarCliente(cliente);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, clienteSalvo.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscarPeloId(@PathVariable long id) {
		Usuario cliente = clienteService.pesquisarPorId(id);
		return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		clienteService.deletarCliente(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Usuario> atualizar(@Validated @RequestBody Usuario cliente, @PathVariable Long id) {
		clienteService.updateCliente(id, cliente);
		return ResponseEntity.ok(cliente);
	}

}
