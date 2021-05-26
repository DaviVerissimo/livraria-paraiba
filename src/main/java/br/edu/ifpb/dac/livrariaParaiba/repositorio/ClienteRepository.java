package br.edu.ifpb.dac.livrariaParaiba.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.dac.livrariaParaiba.modelo.Cliente;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
		
		
		public Cliente findByUsername(String email);
		public Cliente findById(long id);
}
