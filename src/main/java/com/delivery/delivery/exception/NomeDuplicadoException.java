package com.delivery.delivery.exception;

public class NomeDuplicadoException extends RuntimeException {

    public NomeDuplicadoException(String nome) {
        super("Ja existe um prato cadastrado com o nome: " + nome);
    }
}