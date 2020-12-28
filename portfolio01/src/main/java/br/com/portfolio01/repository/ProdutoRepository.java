package br.com.portfolio01.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portfolio01.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
