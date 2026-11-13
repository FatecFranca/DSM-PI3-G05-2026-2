package br.edu.fatecfranca.api.models;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Document(collection = "candidatura")
public class Candidatura {
    
    @Id
    private String id;
    private String estudanteId;
    private String vagaId;

    @NotNull(message = "A data é obrigatória.")
    private LocalDate dataCandidatura;

    @NotBlank(message = "O status é obrigatório.")
   @Pattern(
       regexp = "EM ANÁLISE|ENTREVISTA|APROVADO|REJEITADO",
       message = "O status deve ser EM ANÁLISE, ENTREVISTA, APROVADO ou REJEITADO.")
    private String status;

    private List<String> historicoStatus;

    public Candidatura() {

    }

    public Candidatura(String id, String estudanteId, String vagaId, LocalDate dataCandidatura, String status,
            List<String> historicoStatus) {
        this.id = id;
        this.estudanteId = estudanteId;
        this.vagaId = vagaId;
        this.dataCandidatura = dataCandidatura;
        this.status = status;
        this.historicoStatus = historicoStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEstudanteId() {
        return estudanteId;
    }

    public void setEstudanteId(String estudanteId) {
        this.estudanteId = estudanteId;
    }

    public String getVagaId() {
        return vagaId;
    }

    public void setVagaId(String vagaId) {
        this.vagaId = vagaId;
    }

    public LocalDate getDataCandidatura() {
        return dataCandidatura;
    }

    public void setDataCandidatura(LocalDate dataCandidatura) {
        this.dataCandidatura = dataCandidatura;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getHistoricoStatus() {
        return historicoStatus;
    }

    public void setHistoricoStatus(List<String> historicoStatus) {
        this.historicoStatus = historicoStatus;
    }

    


}
