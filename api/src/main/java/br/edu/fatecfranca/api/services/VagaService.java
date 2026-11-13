package br.edu.fatecfranca.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.edu.fatecfranca.api.models.Vaga;
import br.edu.fatecfranca.api.repositories.EmpresaRepository;
import br.edu.fatecfranca.api.repositories.UsuarioRepository;
import br.edu.fatecfranca.api.repositories.VagaRepository;

@Service 
public class VagaService {
    
    private final VagaRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final EmpresaRepository empresaRepository;

    public VagaService(VagaRepository repository, UsuarioRepository usuarioRepository, EmpresaRepository empresaRepository) {

       this.repository = repository;
       this.usuarioRepository = usuarioRepository;
       this.empresaRepository = empresaRepository;
   }

   private void verificarUsuario(String usuarioId) {
       if (usuarioId == null || usuarioId.isBlank()) {
           throw new ResponseStatusException(
                   HttpStatus.BAD_REQUEST,
                   "O identificador do usuário é obrigatório.");
       }


       if (!usuarioRepository.existsById(usuarioId)) {
           throw new ResponseStatusException(
                   HttpStatus.NOT_FOUND,
                   "Usuário não encontrado.");
       }

   }

   private void verificarEmpresa(String empresaId) {
       if (empresaId == null || empresaId.isBlank()) {
           throw new ResponseStatusException(
                   HttpStatus.BAD_REQUEST,
                   "O identificador da empresa é obrigatório.");
       }


       if (!empresaRepository.existsById(empresaId)) {
           throw new ResponseStatusException(
                   HttpStatus.NOT_FOUND,
                   "Empresa não encontrada.");
       }

   }

   public List<Vaga> listarTodos() {
       return repository.findAll();
   }

   public Optional<Vaga> buscarPorId(String id) {
       return repository.findById(id);
   }

   public List<Vaga> listarPorUsuario(String usuarioId) {
       verificarUsuario(usuarioId);
       return repository.findByUsuarioId(usuarioId);
   }

   public List<Vaga> listarPorEmpresa(String empresaId) {
        verificarEmpresa(empresaId);
        return repository.findByEmpresaId(empresaId);
   }

   public Vaga salvar(Vaga vaga) {
        verificarUsuario(vaga.getUsuarioId());
        verificarEmpresa(vaga.getEmpresaId());
        return repository.save(vaga);
   }

   public void excluir(String id) {
       repository.deleteById(id);
   }

}
