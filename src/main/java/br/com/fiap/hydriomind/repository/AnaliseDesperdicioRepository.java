package br.com.fiap.hydriomind.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import br.com.fiap.hydriomind.entity.AnaliseDesperdicio;

public interface AnaliseDesperdicioRepository extends MongoRepository<AnaliseDesperdicio, Long> {
    // Você pode adicionar métodos personalizados, se necessário
}
