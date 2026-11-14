package br.edu.fatecfranca.api.models;

public class Depoimentos {

    private String texto;
    private String dataDepoimento;

    public Depoimentos() {

    }

    public Depoimentos(String texto, String dataDepoimento) {
        this.texto = texto;
        this.dataDepoimento = dataDepoimento;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getDataDepoimento() {
        return dataDepoimento;
    }

    public void setDataDepoimento(String dataDepoimento) {
        this.dataDepoimento = dataDepoimento;
    }

    //*

}
