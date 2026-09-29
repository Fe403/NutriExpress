package com.delivery.delivery.dto;

import java.math.BigDecimal;

// DTO usado para devolver os dados de um prato ao cliente
// Mantivemos todos os campos aqui pois todos sao relevantes para quem consome a API
// (poderiamos omitir "descricao", por exemplo, se quisessemos uma resposta mais enxuta)
public record PratoResponseDTO(
        Long id,
        String nome,
        String descricao,
        BigDecimal valor,
        String categoria,
        Integer calorias,
        Double quantidade,
        String unidadeMedida
) {
}