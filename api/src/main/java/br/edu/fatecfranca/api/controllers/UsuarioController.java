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

import br.edu.fatecfranca.api.dtos.LoginDto;
import br.edu.fatecfranca.api.models.Usuario;
import br.edu.fatecfranca.api.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
       this.service = service;
   }

   @GetMapping
   public List<Usuario> listarTodos() {
       return service.listarTodos();
   }

   @GetMapping("/{id}")
   public ResponseEntity<Usuario> buscarPorId(@PathVariable String id) {
       return service.buscarPorId(id)
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
   }

   // Endpoint específico para Cadastro
   @PostMapping("/cadastro")
   public ResponseEntity<Usuario> cadastrar(@Valid @RequestBody Usuario usuario) {
       Usuario usuarioSalvo = service.cadastrar(usuario);
       return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
   }

   // Endpoint específico para Login
   @PostMapping("/login")
   public ResponseEntity<Usuario> login(@RequestBody LoginDto credenciais) {
       Usuario usuarioLogado = service.login(credenciais.email(), credenciais.senha());
       return ResponseEntity.ok(usuarioLogado);
   }

   @PostMapping
   public ResponseEntity<Usuario> criar(@Valid @RequestBody Usuario usuario) {
       Usuario usuarioSalvo = service.salvar(usuario);

       return ResponseEntity
               .status(HttpStatus.CREATED)
               .body(usuarioSalvo);
   }

   @PutMapping("/{id}")
   public ResponseEntity<Usuario> atualizar(
           @PathVariable String id,
           @Valid @RequestBody Usuario usuario) {

       if (service.buscarPorId(id).isEmpty()) {
           return ResponseEntity.notFound().build();
       }

       usuario.setId(id);

       Usuario usuarioAtualizado = service.salvar(usuario);

       return ResponseEntity.ok(usuarioAtualizado);
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> excluir(@PathVariable String id) {

       service.excluir(id);

       return ResponseEntity.noContent().build();
   }

}
