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


import br.edu.fatecfranca.api.models.Candidatura;
import br.edu.fatecfranca.api.services.CandidaturaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidaturas")
public class CandidaturaController {
    
    private final CandidaturaService service;

    public CandidaturaController(CandidaturaService service) {
       this.service = service;
   }

   @GetMapping
   public List<Candidatura> listarTodos() {
       return service.listarTodos();
   }

   @GetMapping("/vaga/{vagaId}")
   public List<Candidatura> listarPorVaga(
           @PathVariable String vagaId) {


       return service.listarPorVaga(vagaId);
   }


   @GetMapping("/{id}")
   public ResponseEntity<Candidatura> buscarPorId(@PathVariable String id) {
       return service.buscarPorId(id)
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
   }

   @PostMapping
   public ResponseEntity<Candidatura> criar(@Valid @RequestBody Candidatura candidatura) {
       Candidatura candidaturaSalva = service.salvar(candidatura);

       return ResponseEntity
               .status(HttpStatus.CREATED)
               .body(candidaturaSalva);
   }

   @PutMapping("/{id}")
   public ResponseEntity<Candidatura> atualizar(
           @PathVariable String id,
           @Valid @RequestBody Candidatura candidatura) {

       if (service.buscarPorId(id).isEmpty()) {
           return ResponseEntity.notFound().build();
       }

       candidatura.setId(id);

       Candidatura candidaturaAtualizada = service.salvar(candidatura);

       return ResponseEntity.ok(candidaturaAtualizada);
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
