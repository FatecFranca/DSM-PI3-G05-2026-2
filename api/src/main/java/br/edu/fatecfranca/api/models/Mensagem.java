package br.edu.fatecfranca.api.models;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Document(collection = "mensagem")
public class Mensagem {
    
    @Id 
    private String id;
    private String conversaId;
    private String remetenteId;

    @NotBlank(message = "O conteúdo é obrigatório.")
    private String conteudo;

    @NotNull(message = "A data é obrigatória.")
    private LocalDate dataEnvio;

    @NotBlank(message = "O tipo é obrigatório.")
   @Pattern(
       regexp = "LIDO|NÃO LIDO",
       message = "O status deve ser LIDO ou NÃO LIDO.")
    private String status;

    public Mensagem() {

    }

    public Mensagem(String id, String conversaId, String remetenteId, String conteudo, LocalDate dataEnvio,
            String status) {
        this.id = id;
        this.conversaId = conversaId;
        this.remetenteId = remetenteId;
        this.conteudo = conteudo;
        this.dataEnvio = dataEnvio;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConversaId() {
        return conversaId;
    }

    public void setConversaId(String conversaId) {
        this.conversaId = conversaId;
    }

    public String getRemetenteId() {
        return remetenteId;
    }

    public void setRemetenteId(String remetenteId) {
        this.remetenteId = remetenteId;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public LocalDate getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDate dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    

}
