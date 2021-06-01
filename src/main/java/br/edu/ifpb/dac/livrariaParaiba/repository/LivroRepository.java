package br.edu.ifpb.dac.livrariaParaiba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.dac.livrariaParaiba.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

	public List<Livro> findTop5ByOrderByValorAsc();

}
