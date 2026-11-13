package br.edu.fatecfranca.api.models;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Document(collection = "usuario")
public class Usuario {
    
    @Id
    private String id;
    private String perfilId;

     @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    @NotBlank(message = "O e-mail é obrigatório.")
   @Email(message = "O e-mail informado é inválido.")
    private String email;

    @NotBlank(message = "A senha é obrigatória.")
    private String senha;

    @NotBlank(message = "O tipo é obrigatório.")
   @Pattern(
       regexp = "ALUNO|EX-ALUNO|RECRUTADOR|ESPECIALISTA",
       message = "O tipo deve ser ALUNO, EX-ALUNO, RECRUTADOR ou ESPECIALISTA.")
    private String tipo;

    @NotBlank(message = "O status é obrigatório.")
   @Pattern(
       regexp = "A|I",
       message = "O status deve ser A, I.")
    private String status;

    @NotNull(message = "A data é obrigatória.")
    @Field("data_cadastro")
    private LocalDate dataCadastro = LocalDate.now();

    private List<Perfil> perfis;
    private List<Depoimentos> depoimentos; 

    public Usuario() {

    }

    public Usuario(String id, String perfilId, @NotBlank(message = "O nome é obrigatório.") String nome,
            @NotBlank(message = "O e-mail é obrigatório.") @Email(message = "O e-mail informado é inválido.") String email,
            @NotBlank(message = "A senha é obrigatória.") String senha,
            @NotBlank(message = "O tipo é obrigatório.") @Pattern(regexp = "ALUNO|EX-ALUNO|RECRUTADOR|ESPECIALISTA", message = "O status deve ser ALUNO, EX-ALUNO, RECRUTADOR ou ESPECIALISTA.") String tipo,
            @NotBlank(message = "O status é obrigatório.") @Pattern(regexp = "A|I", message = "O status deve ser A, I.") String status,
            @NotNull(message = "A data é obrigatória.") LocalDate dataCadastro, List<Perfil> perfis,
            List<Depoimentos> depoimentos) {
        this.id = id;
        this.perfilId = perfilId;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
        this.status = status;
        this.dataCadastro = dataCadastro;
        this.perfis = perfis;
        this.depoimentos = depoimentos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPerfilId() {
        return perfilId;
    }

    public void setPerfilId(String perfilId) {
        this.perfilId = perfilId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public List<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(List<Perfil> perfis) {
        this.perfis = perfis;
    }

    public List<Depoimentos> getDepoimentos() {
        return depoimentos;
    }

    public void setDepoimentos(List<Depoimentos> depoimentos) {
        this.depoimentos = depoimentos;
    }

    //*


}
