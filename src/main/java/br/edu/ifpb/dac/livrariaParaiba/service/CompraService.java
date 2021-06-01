package br.edu.ifpb.dac.livrariaParaiba.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.livrariaParaiba.model.Compra;
import br.edu.ifpb.dac.livrariaParaiba.repository.CompraRepository;

@Service
public class CompraService {

	@Autowired
	private CompraRepository compraRepository;
	
	public Compra salvarCompra(Compra compra) {
		return compraRepository.save(compra);
		
	}
	public void deletarCompra(Compra compra) {
		compraRepository.delete(compra);	
	}
}
