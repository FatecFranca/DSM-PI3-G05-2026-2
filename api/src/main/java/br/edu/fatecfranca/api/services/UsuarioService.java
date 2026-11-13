package br.edu.fatecfranca.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.edu.fatecfranca.api.models.Usuario;
import br.edu.fatecfranca.api.repositories.UsuarioRepository;
import br.edu.fatecfranca.api.repositories.VagaRepository;

@Service 
public class UsuarioService {
    
    private final UsuarioRepository repository;
    private final VagaRepository vagaRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository repository, VagaRepository vagaRepository, PasswordEncoder passwordEncoder) {

       this.repository = repository;
       this.vagaRepository = vagaRepository;
       this.passwordEncoder = passwordEncoder;
   }

   public List<Usuario> listarTodos() {
       return repository.findAll();
   }

   public Optional<Usuario> buscarPorId(String id) {
       return repository.findById(id);
   }

   // Método de Cadastro com validação de e-mail duplicado
   public Usuario cadastrar(Usuario usuario) {
       if (repository.existsByEmail(usuario.getEmail())) {
           throw new ResponseStatusException(
                   HttpStatus.BAD_REQUEST,
                   "Já existe um usuário cadastrado com este e-mail.");
       }
       
       // Criptografa a senha antes de salvar no banco
       String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
       usuario.setSenha(senhaCriptografada);
       
       return repository.save(usuario);
   }

   // Método de Login
   public Usuario login(String email, String senha) {
       Usuario usuario = repository.findByEmail(email)
               .orElseThrow(() -> new ResponseStatusException(
                       HttpStatus.UNAUTHORIZED, "E-mail ou senha inválidos."));

       // Compara a senha digitada com o hash salvo no banco de dados
       if (!passwordEncoder.matches(senha, usuario.getSenha())) {
           throw new ResponseStatusException(
                   HttpStatus.UNAUTHORIZED, "E-mail ou senha inválidos.");
       }

       return usuario;
   }

   public Usuario salvar(Usuario usuario) {
       return repository.save(usuario);
   }

   public void excluir(String id) {

        if (!repository.existsById(id)) {
           throw new ResponseStatusException(
                   HttpStatus.NOT_FOUND,
                   "Usuário não encontrado.");
       }


       if (vagaRepository.existsByUsuarioId(id)) {
           throw new ResponseStatusException(
                   HttpStatus.CONFLICT,
                   "Não é possível excluir um usuario com vagas vinculadas.");
       }


        repository.deleteById(id);
   }


}
