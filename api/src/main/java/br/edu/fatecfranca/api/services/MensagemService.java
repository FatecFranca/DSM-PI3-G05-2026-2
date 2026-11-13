package br.edu.fatecfranca.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.edu.fatecfranca.api.models.Mensagem;
import br.edu.fatecfranca.api.repositories.ConversaRepository;
import br.edu.fatecfranca.api.repositories.MensagemRepository;

@Service 
public class MensagemService {
    
    private final MensagemRepository repository;
    private final ConversaRepository conversaRepository;

    public MensagemService(MensagemRepository repository, ConversaRepository conversaRepository) {
       this.repository = repository;
       this.conversaRepository = conversaRepository;
   }

   private void verificarConversa(String conversaId) {
       if (conversaId == null || conversaId.isBlank()) {
           throw new ResponseStatusException(
                   HttpStatus.BAD_REQUEST,
                   "O identificador da conversa é obrigatório.");
       }


       if (!conversaRepository.existsById(conversaId)) {
           throw new ResponseStatusException(
                   HttpStatus.NOT_FOUND,
                   "Conversa não encontrada.");
       }
   }


   public List<Mensagem> listarTodos() {
       return repository.findAll();
   }

   public Optional<Mensagem> buscarPorId(String id) {
       return repository.findById(id);
   }

   public List<Mensagem> listarPorConversa(String conversaId) {
       verificarConversa(conversaId);;
       return repository.findByConversaId(conversaId);
   }


   public Mensagem salvar(Mensagem mensagem) {
        verificarConversa(mensagem.getConversaId());
        return repository.save(mensagem);
   }

   public void excluir(String id) {
       repository.deleteById(id);
   }

}
