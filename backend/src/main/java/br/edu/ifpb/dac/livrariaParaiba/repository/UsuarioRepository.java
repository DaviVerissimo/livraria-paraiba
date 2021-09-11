package br.edu.ifpb.dac.livrariaParaiba.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.dac.livrariaParaiba.model.Usuario;
/**
 * @author André Felipe
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
		
		
		public Usuario findByNome(String email);
		public Usuario findById(long id);
}
