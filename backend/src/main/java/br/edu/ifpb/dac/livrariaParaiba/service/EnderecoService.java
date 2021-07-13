package br.edu.ifpb.dac.livrariaParaiba.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.livrariaParaiba.model.Endereco;
import br.edu.ifpb.dac.livrariaParaiba.repository.EnderecoRepository;
/**
 * @author andré felipe
 */
@Service
public class EnderecoService{
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	public Endereco salvarEndereco(Endereco endereco) {
		enderecoRepository.save(endereco);
		return endereco;
	}
	public void removerEnderecoPorId(long id) {
		enderecoRepository.deleteById(id);
	}
	public void removerEndereco(Endereco endereco) {
		enderecoRepository.delete(endereco);
	}
	public void updateEndereco(long id, Endereco endereco) {
		Endereco enderecoSalvo = enderecoRepository.findById(id);
		BeanUtils.copyProperties(endereco, enderecoSalvo);
		enderecoRepository.save(enderecoSalvo);
	}
	public List<Endereco> listarEnderecos(){
		return enderecoRepository.findAll();
	}
	public List<Endereco> findByIdCliente(long id_cliente){
		return enderecoRepository.findByCliente(id_cliente);
	}
}
