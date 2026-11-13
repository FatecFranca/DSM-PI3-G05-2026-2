package br.edu.fatecfranca.api.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.edu.fatecfranca.api.models.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);

}
