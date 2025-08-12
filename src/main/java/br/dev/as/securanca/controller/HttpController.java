package br.dev.as.securanca.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpController {

    @GetMapping("/public")
    public String publicRoute(){
        return "<h1>Entrou na rota pública do Http Controller</h1>";
    }

    @GetMapping("/private")
    public String privateRoute(){
        return "<h1>Rota privada do Http Controller</h1>";
    }

}
