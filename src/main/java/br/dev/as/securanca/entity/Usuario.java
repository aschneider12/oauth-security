package br.dev.as.securanca.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "users") // nome da tabela no banco
public class Usuario {

    @Id
    private String username;
    private String password;


}
