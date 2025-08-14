package br.dev.as.securanca.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.Jwt;
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

    @GetMapping("/private/token")
    public String privateRouteToken(@AuthenticationPrincipal OidcUser principal){
        return "<h1>Rota privada do Http Controller</h1>"
                +"<h3>"+principal.getEmail()+"</h3>"
                +"<h3>"+principal.getIdToken().getTokenValue()+"</h3>"
                +"<h3>"+principal.getAccessTokenHash()+"</h3>";
    }

    @GetMapping("/private/jwt")
    public String privateRouteJwt(@AuthenticationPrincipal Jwt jwt){

        return "<h1>Rota privada do Http Controller</h1>"
                +"<h3>"+jwt.getSubject()+"</h3>"
                +"<h3>"+jwt.getTokenValue()+"</h3>"
                +"<h3>"+jwt.getClaims().get("email")+"</h3>";
    }

}
