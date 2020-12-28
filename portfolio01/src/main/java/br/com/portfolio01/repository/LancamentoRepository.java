package br.com.portfolio01.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portfolio01.entity.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

}
