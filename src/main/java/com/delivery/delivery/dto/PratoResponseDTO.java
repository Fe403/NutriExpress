package com.delivery.delivery.dto;

import java.math.BigDecimal;

// DTO usado para devolver os dados de um prato ao cliente
public record PratoResponseDTO(
        Long id,
        String nome,
        String descricao,
        BigDecimal valor,
        String categoria,
        Integer calorias,
        Double quantidade,
        String unidadeMedida,
        Integer estoque
) {
}