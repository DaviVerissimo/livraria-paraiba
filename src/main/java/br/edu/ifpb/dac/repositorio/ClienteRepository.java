package br.edu.ifpb.dac.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.dac.modelo.Cliente;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	

}
