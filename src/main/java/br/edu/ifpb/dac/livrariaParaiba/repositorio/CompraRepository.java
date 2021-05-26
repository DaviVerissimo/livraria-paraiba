package br.edu.ifpb.dac.livrariaParaiba.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.dac.livrariaParaiba.modelo.Compra;

public interface CompraRepository extends JpaRepository<Compra, Long> {

}
