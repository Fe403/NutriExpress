package com.delivery.delivery.controller;

import com.delivery.delivery.dto.PratoRequestDTO;
import com.delivery.delivery.dto.PratoResponseDTO;
import com.delivery.delivery.service.PratoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pratos")
public class PratoController {

    private final PratoService pratoService;

    public PratoController(PratoService pratoService) {
        this.pratoService = pratoService;
    }

    @GetMapping
    public ResponseEntity<List<PratoResponseDTO>> listar(
            @RequestParam(required = false) String categoria) {

        if (categoria != null) {
            return ResponseEntity.ok(pratoService.listarPorCategoria(categoria));
        }
        return ResponseEntity.ok(pratoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PratoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pratoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<PratoResponseDTO> criar(@Valid @RequestBody PratoRequestDTO dto) {
        PratoResponseDTO criado = pratoService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PratoResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody PratoRequestDTO dto) {

        return ResponseEntity.ok(pratoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        pratoService.remover(id);
        return ResponseEntity.noContent().build();
    }

    // ---- Desafios extras ----

    // Desafio extra 1: PATCH /pratos/{id}/valor
    @PatchMapping("/{id}/valor")
    public ResponseEntity<PratoResponseDTO> atualizarValor(
            @PathVariable Long id,
            @RequestBody Map<String, BigDecimal> body) {

        BigDecimal novoValor = body.get("valor");
        return ResponseEntity.ok(pratoService.atualizarValor(id, novoValor));
    }

    // Desafio extra 2: GET /pratos/calorias?max=500
    @GetMapping("/calorias")
    public ResponseEntity<List<PratoResponseDTO>> listarPorCaloriasMax(
            @RequestParam Integer max) {

        return ResponseEntity.ok(pratoService.listarPorCaloriasMax(max));
    }
}