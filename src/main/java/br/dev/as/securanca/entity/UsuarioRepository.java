package br.dev.as.securanca.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public class UsuarioRepository implements JpaRepository<Usuario, UUID> {
}
