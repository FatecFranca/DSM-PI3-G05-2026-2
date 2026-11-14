package br.edu.fatecfranca.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.edu.fatecfranca.api.models.Empresa;
import br.edu.fatecfranca.api.repositories.EmpresaRepository;
import br.edu.fatecfranca.api.repositories.VagaRepository;

@Service
public class EmpresaService {
    
    private final EmpresaRepository repository;
    private final VagaRepository vagaRepository;

    public EmpresaService(EmpresaRepository repository, VagaRepository vagaRepository) {

       this.repository = repository;
       this.vagaRepository = vagaRepository;
   }

   public List<Empresa> listarTodos() {
       return repository.findAll();
   }

   public Optional<Empresa> buscarPorId(String id) {
       return repository.findById(id);
   }

   public Empresa salvar(Empresa empresa) {
       return repository.save(empresa);
   }

   public void excluir(String id) {

        if (!repository.existsById(id)) {
           throw new ResponseStatusException(
                   HttpStatus.NOT_FOUND,
                   "Empresa não encontrada.");
       }


       if (vagaRepository.existsByEmpresaId(id)) {
           throw new ResponseStatusException(
                   HttpStatus.CONFLICT,
                   "Não é possível excluir uma empresa com vagas vinculadas.");
       }


       repository.deleteById(id);
   }

}
