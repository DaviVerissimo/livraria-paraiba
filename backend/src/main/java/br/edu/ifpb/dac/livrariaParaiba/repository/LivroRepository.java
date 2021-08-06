package br.edu.ifpb.dac.livrariaParaiba.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.dac.livrariaParaiba.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

	@Query("SELECT l FROM Livro l WHERE l.quantidade > 0 ORDER BY valor")
	public Page<Livro> findTop5(PageRequest pageRequest);
	
	@Query("SELECT l FROM Livro l WHERE l.nome like %?1%")
	public List<Livro> findLivroByName(String nome);
	
}
