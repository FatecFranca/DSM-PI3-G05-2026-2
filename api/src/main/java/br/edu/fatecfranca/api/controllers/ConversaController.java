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

import br.edu.fatecfranca.api.models.Conversa;
import br.edu.fatecfranca.api.services.ConversaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/conversas")
public class ConversaController {
    
    private final ConversaService service;

    public ConversaController(ConversaService service) {
       this.service = service;
   }

   @GetMapping
   public List<Conversa> listarTodos() {
       return service.listarTodos();
   }

   @GetMapping("/{id}")
   public ResponseEntity<Conversa> buscarPorId(@PathVariable String id) {
       return service.buscarPorId(id)
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
   }

   @PostMapping
   public ResponseEntity<Conversa> criar(@Valid @RequestBody Conversa conversa) {
       Conversa conversaSalva = service.salvar(conversa);

       return ResponseEntity
               .status(HttpStatus.CREATED)
               .body(conversaSalva);
   }

   @PutMapping("/{id}")
   public ResponseEntity<Conversa> atualizar(
           @PathVariable String id,
           @Valid @RequestBody Conversa conversa) {

       if (service.buscarPorId(id).isEmpty()) {
           return ResponseEntity.notFound().build();
       }

       conversa.setId(id);

       Conversa conversaAtualizada = service.salvar(conversa);

       return ResponseEntity.ok(conversaAtualizada);
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
