package br.edu.fatecfranca.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.edu.fatecfranca.api.models.Conversa;
import br.edu.fatecfranca.api.repositories.ConversaRepository;

@Service 
public class ConversaService {
    
    private final ConversaRepository repository;

    public ConversaService(ConversaRepository repository) {
       this.repository = repository;
   }

   public List<Conversa> listarTodos() {
       return repository.findAll();
   }

   public Optional<Conversa> buscarPorId(String id) {
       return repository.findById(id);
   }

   public Conversa salvar(Conversa conversa) {
       return repository.save(conversa);
   }

   public void excluir(String id) {
       repository.deleteById(id);
   }

}
