package br.com.fiap.hydriomind.repository;

import br.com.fiap.hydriomind.entity.Recomendacao;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RecomendacaoRepository extends MongoRepository<Recomendacao, Long> {
}
