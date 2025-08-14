package br.dev.as.securanca.controller;

import br.dev.as.securanca.dto.AuthRequest;
import br.dev.as.securanca.dto.AuthResponse;
import br.dev.as.securanca.security.JwtTokenUtil;
import br.dev.as.securanca.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
public class AuthController {



    private final AuthenticationService authenticationService;

    private final AuthService userService;
    private final JwtTokenUtil jwtService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public ResponseEntity<AuthResponse> authenticate(Authentication authentication) {

        String token = authenticationService.authenticate(authentication);

        return ResponseEntity.ok(new AuthResponse(token));
    }
}
