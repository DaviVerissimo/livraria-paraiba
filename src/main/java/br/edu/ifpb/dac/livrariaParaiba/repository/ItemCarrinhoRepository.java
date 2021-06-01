package br.edu.ifpb.dac.livrariaParaiba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.dac.livrariaParaiba.model.ItemCarrinho;

@Repository
public interface ItemCarrinhoRepository extends JpaRepository<ItemCarrinho, Long> {

	public List<ItemCarrinho> findByCliente(long id_cliente);
}
