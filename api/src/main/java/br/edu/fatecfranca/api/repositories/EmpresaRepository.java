package br.edu.fatecfranca.api.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.edu.fatecfranca.api.models.Empresa;

public interface  EmpresaRepository extends MongoRepository<Empresa, String> {
    
}
