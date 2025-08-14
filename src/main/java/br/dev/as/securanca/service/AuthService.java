package br.dev.as.securanca.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailServiceImpl userDetailsService;


    public AuthService(AuthenticationManager authenticationManager, UserDetailServiceImpl userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    public UserDetails authenticate(String login, String senha) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login, senha)
        );
        return userDetailsService.loadUserByUsername(login);
    }
}

