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


import br.edu.fatecfranca.api.models.Empresa;
import br.edu.fatecfranca.api.services.EmpresaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {
    
    private final EmpresaService service;

    public EmpresaController(EmpresaService service) {
       this.service = service;
   }

   @GetMapping
   public List<Empresa> listarTodos() {
       return service.listarTodos();
   }

   @GetMapping("/{id}")
   public ResponseEntity<Empresa> buscarPorId(@PathVariable String id) {
       return service.buscarPorId(id)
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
   }

   @PostMapping
   public ResponseEntity<Empresa> criar(@Valid @RequestBody Empresa empresa) {
       Empresa empresaSalva = service.salvar(empresa);

       return ResponseEntity
               .status(HttpStatus.CREATED)
               .body(empresaSalva);
   }

   @PutMapping("/{id}")
   public ResponseEntity<Empresa> atualizar(
           @PathVariable String id,
           @Valid @RequestBody Empresa empresa) {

       if (service.buscarPorId(id).isEmpty()) {
           return ResponseEntity.notFound().build();
       }

       empresa.setId(id);

       Empresa empresaAtualizada = service.salvar(empresa);

       return ResponseEntity.ok(empresaAtualizada);
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> excluir(@PathVariable String id) {

       service.excluir(id);

       return ResponseEntity.noContent().build();
   }

}
