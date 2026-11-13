package br.edu.fatecfranca.api.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.edu.fatecfranca.api.models.Mensagem;

public interface MensagemRepository extends MongoRepository<Mensagem, String> {

    List<Mensagem> findByConversaId(String conversaId);

}
