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


import br.edu.fatecfranca.api.models.Mensagem;
import br.edu.fatecfranca.api.services.MensagemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/mensagens")
public class MensagemController {
    
    private final MensagemService service;

    public MensagemController(MensagemService service) {
       this.service = service;
   }

   @GetMapping
   public List<Mensagem> listarTodos() {
       return service.listarTodos();
   }

    @GetMapping("/conversa/{conversaId}")
   public List<Mensagem> listarPorConversa(
           @PathVariable String conversaId) {


       return service.listarPorConversa(conversaId);
   }


   @GetMapping("/{id}")
   public ResponseEntity<Mensagem> buscarPorId(@PathVariable String id) {
       return service.buscarPorId(id)
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
   }

   @PostMapping
   public ResponseEntity<Mensagem> criar(@Valid @RequestBody Mensagem mensagem) {
       Mensagem mensagemSalva = service.salvar(mensagem);

       return ResponseEntity
               .status(HttpStatus.CREATED)
               .body(mensagemSalva);
   }

   @PutMapping("/{id}")
   public ResponseEntity<Mensagem> atualizar(
           @PathVariable String id,
           @Valid @RequestBody Mensagem mensagem) {

       if (service.buscarPorId(id).isEmpty()) {
           return ResponseEntity.notFound().build();
       }

    mensagem.setId(id);

       Mensagem mensagemAtualizada = service.salvar(mensagem);

       return ResponseEntity.ok(mensagemAtualizada);
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
