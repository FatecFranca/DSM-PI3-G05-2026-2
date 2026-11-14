package br.edu.fatecfranca.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.edu.fatecfranca.api.models.Conversa;

public interface ConversaRepository extends MongoRepository<Conversa, String> {
    
}
