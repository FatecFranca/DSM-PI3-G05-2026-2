package br.edu.fatecfranca.api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.fatecfranca.api.models.Vaga;
import br.edu.fatecfranca.api.services.VagaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/vagas")
public class VagaController {
    
    private final VagaService service;

    public VagaController(VagaService service) {
       this.service = service;
   }

   @GetMapping
   public List<Vaga> listarTodos() {
       return service.listarTodos();
   }

   @GetMapping("/usuario/{usuarioId}")
   public List<Vaga> listarPorUsuario(
           @PathVariable String usuarioId) {


       return service.listarPorUsuario(usuarioId);
   }

   @GetMapping("/empresa/{empresaId}")
   public List<Vaga> listarPorEmpresa(
           @PathVariable String empresaId) {


       return service.listarPorUsuario(empresaId);
   }


   @GetMapping("/{id}")
   public ResponseEntity<Vaga> buscarPorId(@PathVariable String id) {
       return service.buscarPorId(id)
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
   }

   @PostMapping
   public ResponseEntity<Vaga> criar(@Valid @RequestBody Vaga vaga) {
       Vaga vagaSalva = service.salvar(vaga);

       return ResponseEntity
               .status(HttpStatus.CREATED)
               .body(vagaSalva);
   }

   @PutMapping("/{id}")
   public ResponseEntity<Vaga> atualizar(
           @PathVariable String id,
           @Valid @RequestBody Vaga vaga) {

       if (service.buscarPorId(id).isEmpty()) {
           return ResponseEntity.notFound().build();
       }

       vaga.setId(id);

       Vaga vagaAtualizada = service.salvar(vaga);

       return ResponseEntity.ok(vagaAtualizada);
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> excluir(@PathVariable String id) {

       if (service.buscarPorId(id).isEmpty()) {
           return ResponseEntity.notFound().build();
       }

       service.excluir(id);

       return ResponseEntity.noContent().build();
   }

}
