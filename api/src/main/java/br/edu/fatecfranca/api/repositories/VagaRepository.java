package br.edu.fatecfranca.api.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.edu.fatecfranca.api.models.Vaga;

public interface VagaRepository extends MongoRepository<Vaga, String> {
    
    List<Vaga> findByUsuarioId(String usuarioId);

    boolean existsByUsuarioId(String usuarioId);

    List<Vaga> findByEmpresaId(String empresaId);

    boolean existsByEmpresaId(String empresaId);

}
