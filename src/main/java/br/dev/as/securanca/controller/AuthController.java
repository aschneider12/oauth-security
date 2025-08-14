package br.dev.as.securanca.controller;

import br.dev.as.securanca.dto.AuthRequest;
import br.dev.as.securanca.dto.AuthResponse;
import br.dev.as.securanca.security.JwtTokenUtil;
import br.dev.as.securanca.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {



    private final AuthService userService;
    private final JwtTokenUtil jwtService;

    public AuthController(AuthService userService, JwtTokenUtil jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request) {
        // 1. Autenticar usuário via serviço
        var userDetails = userService.authenticate(request.getLogin(), request.getSenha());

        // 2. Gerar JWT
        String token = jwtService.generateToken(userDetails);

        // 3. Retornar token
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
