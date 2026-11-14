package br.edu.fatecfranca.api.models;

import java.util.List;

public class Perfil {
    
    private List<String> cursos;
    private List<String> habilidades;
    private List<String> experiencias;
    private String gitHub;

    public Perfil() {

    }

    public Perfil(List<String> cursos, List<String> habilidades, List<String> experiencias, String gitHub) {
        this.cursos = cursos;
        this.habilidades = habilidades;
        this.experiencias = experiencias;
        this.gitHub = gitHub;
    }

    public List<String> getCursos() {
        return cursos;
    }

    public void setCursos(List<String> cursos) {
        this.cursos = cursos;
    }

    public List<String> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(List<String> habilidades) {
        this.habilidades = habilidades;
    }

    public List<String> getExperiencias() {
        return experiencias;
    }

    public void setExperiencias(List<String> experiencias) {
        this.experiencias = experiencias;
    }

    public String getGitHub() {
        return gitHub;
    }

    public void setGitHub(String gitHub) {
        this.gitHub = gitHub;
    }

    //*

}
