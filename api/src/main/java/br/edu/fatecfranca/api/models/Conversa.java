package br.edu.fatecfranca.api.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;

@Document(collection = "conversa")
public class Conversa {
    
    @Id
    private String id;

    private List<String> participantesId;

    @NotBlank(message = "O última mensagem é obrigatória.")
    private String ultimaMensagem;

    @NotBlank(message = "A última atualização é obrigatória.")
    private String ultimaAtualizacao;

    public Conversa() {

    }

    public Conversa(String id, List<String> participantesId, String ultimaMensagem, String ultimaAtualizacao) {
        this.id = id;
        this.participantesId = participantesId;
        this.ultimaMensagem = ultimaMensagem;
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getParticipantesId() {
        return participantesId;
    }

    public void setParticipantesId(List<String> participantesId) {
        this.participantesId = participantesId;
    }

    public String getUltimaMensagem() {
        return ultimaMensagem;
    }

    public void setUltimaMensagem(String ultimaMensagem) {
        this.ultimaMensagem = ultimaMensagem;
    }

    public String getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public void setUltimaAtualizacao(String ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    

}
