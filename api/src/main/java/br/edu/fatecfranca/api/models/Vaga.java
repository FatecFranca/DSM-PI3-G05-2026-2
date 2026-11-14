package br.edu.fatecfranca.api.models;

import java.util.List;

import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Document(collection = "vaga")
public class Vaga {
    
    @Id
    private String id;
    private String usuarioId;
    private String empresaId;

     @NotBlank(message = "O título é obrigatório.")
    private String titulo;

    @NotBlank(message = "A descrição é obrigatória.")
    private String descricao;

    @NotBlank(message = "O tipo é obrigatório.")
   @Pattern(
       regexp = "EMPREGO|ESTÁGIO",
       message = "O tipo deve ser EMPREGO ou ESTÁGIO.")
    private String tipo;
    
    @NotBlank(message = "Os requisitos são obrigatórios.")
    private List<String> requisitos;

    @NotNull(message = "O salário é obrigatório.")
    private Double salario;

    @NotNull(message = "A data é obrigatória.")
    private LocalDate dataPublicacao;

    public Vaga() {

    }

    public Vaga(String id, String usuarioId, String empresaId, String titulo, String descricao, String tipo,
            List<String> requisitos, Double salario, LocalDate dataPublicacao) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.empresaId = empresaId;
        this.titulo = titulo;
        this.descricao = descricao;
        this.tipo = tipo;
        this.requisitos = requisitos;
        this.salario = salario;
        this.dataPublicacao = dataPublicacao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(String empresaId) {
        this.empresaId = empresaId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<String> getRequisitos() {
        return requisitos;
    }

    public void setRequisitos(List<String> requisitos) {
        this.requisitos = requisitos;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

}
