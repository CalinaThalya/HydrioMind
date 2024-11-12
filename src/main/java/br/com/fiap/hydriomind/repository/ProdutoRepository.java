package br.com.fiap.hydriomind.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.hydriomind.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
