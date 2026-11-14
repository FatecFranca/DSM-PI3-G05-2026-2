package br.edu.fatecfranca.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.edu.fatecfranca.api.models.Candidatura;
import br.edu.fatecfranca.api.repositories.CandidaturaRepository;
import br.edu.fatecfranca.api.repositories.VagaRepository;

@Service 
public class CandidaturaService {
    
    private final CandidaturaRepository repository;
    private final VagaRepository vagaRepository;

    public CandidaturaService(CandidaturaRepository repository, VagaRepository vagaRepository) {
       this.repository = repository;
       this.vagaRepository = vagaRepository;
   }

   private void verificarVaga(String vagaId) {
       if (vagaId == null || vagaId.isBlank()) {
           throw new ResponseStatusException(
                   HttpStatus.BAD_REQUEST,
                   "O identificador de vaga é obrigatório.");
       }


       if (!vagaRepository.existsById(vagaId)) {
           throw new ResponseStatusException(
                   HttpStatus.NOT_FOUND,
                   "Vaga não encontrada.");
       }
   }


   public List<Candidatura> listarTodos() {
       return repository.findAll();
   }

   public Optional<Candidatura> buscarPorId(String id) {
       return repository.findById(id);
   }

   public List<Candidatura> listarPorVaga(String vagaId) {
       verificarVaga(vagaId);
       return repository.findByVagaId(vagaId);
   }


   public Candidatura salvar(Candidatura candidatura) {
        verificarVaga(candidatura.getVagaId());
        return repository.save(candidatura);
   }

   public void excluir(String id) {
       repository.deleteById(id);
   }

}
