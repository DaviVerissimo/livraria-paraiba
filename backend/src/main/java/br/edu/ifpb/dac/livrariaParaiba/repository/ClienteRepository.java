package br.edu.ifpb.dac.livrariaParaiba.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.dac.livrariaParaiba.model.Cliente;
/**
 * @author André Felipe
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
		
		
		public Cliente findByUsername(String email);
		public Cliente findById(long id);
}
