package br.edu.fatecfranca.api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    
    // Rota para a página inicial
    @GetMapping("/")
    public String home() {
        return "index"; // Vai procurar por templates/index.html
    }

    // Rota para a página de login
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // Vai procurar por templates/login.html
    }

    // Rota para a página de cadastro
    @GetMapping("/cadastro")
    public String cadastroPage() {
        return "cadastro"; // Vai procurar por templates/cadastro.html
    }

    // Rota para a página sobre
    @GetMapping("/sobre")
    public String sobrePage() {
        return "sobre"; // Vai procurar por templates/sobre.html
    }

    // Rota para a página contato
    @GetMapping("/contato")
    public String contatoPage() {
        return "contato"; // Vai procurar por templates/contato.html
    }

    // Rota para a página vagas
    @GetMapping("/vagas")
    public String vagasPage() {
        return "vagas"; // Vai procurar por templates/vagas.html
    }

    // Rota para a página depoimentos
    @GetMapping("/depoimentos")
    public String depoimentosPage() {
        return "depoimentos"; // Vai procurar por templates/depoimentos.html
    }

}
