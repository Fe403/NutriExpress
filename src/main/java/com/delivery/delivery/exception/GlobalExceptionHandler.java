package com.delivery.delivery.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Captura os erros de validacao do @Valid (ex: campo em branco) e devolve 400
    // com um mapa "campo -> mensagem de erro"
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> erros = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(erro ->
                erros.put(erro.getField(), erro.getDefaultMessage())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
    }

    // Captura quando um prato com o id informado nao existe e devolve 404
    @ExceptionHandler(PratoNaoEncontradoException.class)
    public ResponseEntity<Map<String, String>> handlePratoNaoEncontrado(PratoNaoEncontradoException ex) {
        Map<String, String> erro = new HashMap<>();
        erro.put("mensagem", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    // Captura quando ja existe um prato com o mesmo nome e devolve 400
    @ExceptionHandler(NomeDuplicadoException.class)
    public ResponseEntity<Map<String, String>> handleNomeDuplicado(NomeDuplicadoException ex) {
        Map<String, String> erro = new HashMap<>();
        erro.put("mensagem", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}