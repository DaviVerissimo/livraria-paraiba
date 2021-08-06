package br.edu.ifpb.dac.livrariaParaiba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.livrariaParaiba.model.ItemCarrinho;
import br.edu.ifpb.dac.livrariaParaiba.repository.ItemCarrinhoRepository;
/**
 * @author andré felipe
 */
@Service
public class ItemCarrinhoService {
	
	@Autowired
	private ItemCarrinhoRepository carrinhoRepository;
	
	public ItemCarrinho salvarItem(ItemCarrinho itemCarrinho) {
		return carrinhoRepository.save(itemCarrinho);
	}
	
	public void removerItem(long id) {
		carrinhoRepository.deleteById(id);;
	}
	
	public List<ItemCarrinho> listarItensIdCliente(long id){
		return carrinhoRepository.findByCliente(id);
	}
	public ItemCarrinho pesquisarId(long id) {
		return carrinhoRepository.getById(id);
	}
}
