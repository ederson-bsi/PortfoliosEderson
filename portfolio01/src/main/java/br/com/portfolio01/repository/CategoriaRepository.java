package br.com.portfolio01.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.portfolio01.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
