package com.delivery.delivery.controller;

import com.delivery.delivery.dto.ImcResponseDTO;
import com.delivery.delivery.dto.PratoResponseDTO;
import com.delivery.delivery.service.ImcService;
import com.delivery.delivery.service.PratoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/imc")
public class ImcController {

    private final ImcService imcService;
    private final PratoService pratoService;

    public ImcController(ImcService imcService, PratoService pratoService) {
        this.imcService = imcService;
        this.pratoService = pratoService;
    }

    @GetMapping
    public ResponseEntity<?> calcular(@RequestParam double peso,
                                      @RequestParam double altura) {
        if (peso <= 0 || altura <= 0) {
            return ResponseEntity.badRequest()
                    .body("Peso e altura devem ser maiores que zero.");
        }

        double imc = imcService.calcularImc(peso, altura);
        String classificacao = imcService.classificar(imc);
        String categoria = imcService.categoriaIndicada(classificacao);

        List<PratoResponseDTO> refeicoes = pratoService.listarPorCategoria(categoria);

        return ResponseEntity.ok(new ImcResponseDTO(imc, classificacao, categoria, refeicoes));
    }
}