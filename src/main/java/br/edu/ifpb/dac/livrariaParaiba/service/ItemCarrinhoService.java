package br.edu.ifpb.dac.livrariaParaiba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.livrariaParaiba.modelo.ItemCarrinho;
import br.edu.ifpb.dac.livrariaParaiba.repositorio.ItemCarrinhoRepository;

@Service
public class ItemCarrinhoService {
	
	@Autowired
	private ItemCarrinhoRepository carrinhoRepository;
	
	public ItemCarrinho salvarItem(ItemCarrinho itemCarrinho) {
		return carrinhoRepository.save(itemCarrinho);
	}
	
	public void removerItem(ItemCarrinho itemCarrinho) {
		carrinhoRepository.delete(itemCarrinho);
	}
	
	public List<ItemCarrinho> listarItensIdCliente(long id){
		return carrinhoRepository.findByCliente(id);
	}
}
