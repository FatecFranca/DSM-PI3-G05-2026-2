package br.edu.fatecfranca.api.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.edu.fatecfranca.api.models.Candidatura;

public interface CandidaturaRepository extends MongoRepository<Candidatura, String> {
    
    List<Candidatura> findByVagaId(String vagaId);

}
