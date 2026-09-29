package com.delivery.delivery.exception;

public class PratoNaoEncontradoException extends RuntimeException {

    public PratoNaoEncontradoException(Long id) {
        super("Prato nao encontrado com o id: " + id);
    }
}