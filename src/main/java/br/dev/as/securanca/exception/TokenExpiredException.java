package br.dev.as.securanca.exception;

public class TokenExpiredException extends RuntimeException{

    public TokenExpiredException(String msg) {
        super(msg);
    }
}
