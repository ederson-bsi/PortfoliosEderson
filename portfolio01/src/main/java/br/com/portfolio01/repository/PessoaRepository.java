package br.com.portfolio01.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portfolio01.entity.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
