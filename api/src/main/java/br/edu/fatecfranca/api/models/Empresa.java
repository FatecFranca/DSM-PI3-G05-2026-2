package br.edu.fatecfranca.api.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;

@Document(collection = "empresa")
public class Empresa {
    
    @Id
    private String id;

     @NotBlank(message = "O nome é obrigatório.")
    private String nome;

     @NotBlank(message = "A missão é obrigatória.")
    private String missao;

     @NotBlank(message = "Os valores são obrigatórios.")
    private List<String> valores;

     @NotBlank(message = "As expectativas são obrigatórias.")
    private List<String> expectativas;

    public Empresa() {

    }

    public Empresa(String id, String nome, String missao, List<String> valores, List<String> expectativas) {
        this.id = id;
        this.nome = nome;
        this.missao = missao;
        this.valores = valores;
        this.expectativas = expectativas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMissao() {
        return missao;
    }

    public void setMissao(String missao) {
        this.missao = missao;
    }

    public List<String> getValores() {
        return valores;
    }

    public void setValores(List<String> valores) {
        this.valores = valores;
    }

    public List<String> getExpectativas() {
        return expectativas;
    }

    public void setExpectativas(List<String> expectativas) {
        this.expectativas = expectativas;
    }

    

}
