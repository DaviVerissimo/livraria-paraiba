package br.edu.ifpb.dac.livrariaParaiba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.dac.livrariaParaiba.model.Endereco;
/**
 * @author andré felipe
 */
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
	
	public Endereco findById(long id);
	public List<Endereco> findByCliente(long id_cliente);

}
