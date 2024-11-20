package br.com.fiap.hydriomind.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.hydriomind.entity.Usuario;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> { 
    Optional<Usuario> findByUsername(String username);
}
