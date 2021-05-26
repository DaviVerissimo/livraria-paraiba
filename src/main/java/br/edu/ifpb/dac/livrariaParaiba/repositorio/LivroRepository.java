package br.edu.ifpb.dac.livrariaParaiba.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.dac.livrariaParaiba.modelo.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{

}
