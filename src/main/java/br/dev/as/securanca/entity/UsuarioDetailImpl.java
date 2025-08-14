package br.dev.as.securanca.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UsuarioDetailImpl implements UserDetails {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, unique = true)
        private String login;

        @Column(nullable = false)
        private String senha;

        @Column(nullable = false)
        private String role; // ROLE_USER, ROLE_ADMIN...

        public User() {
        }

        public User(String login, String senha, String role) {
            this.login = login;
            this.senha = senha;
            this.role = role;
        }

        public Long getId() {
            return id;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public String getSenha() {
            return senha;
        }

        public void setSenha(String senha) {
            this.senha = senha;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        // Métodos do UserDetails

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return List.of(() -> role);
        }

        @Override
        public String getPassword() {
            return senha;
        }

        @Override
        public String getUsername() {
            return login;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }

}
