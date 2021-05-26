package br.edu.ifpb.dac.livrariaParaiba.service;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.ifpb.dac.livrariaParaiba.modelo.Compra;
import br.edu.ifpb.dac.livrariaParaiba.repositorio.CompraRepository;

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
