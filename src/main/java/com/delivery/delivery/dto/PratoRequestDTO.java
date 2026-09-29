package com.delivery.delivery.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

// DTO usado para receber dados na criacao e atualizacao de um prato (POST e PUT)
public record PratoRequestDTO(

        @NotBlank(message = "O nome e obrigatorio")
        String nome,

        String descricao,

        @NotNull(message = "O valor e obrigatorio")
        @Positive(message = "O valor deve ser positivo")
        BigDecimal valor,

        @NotBlank(message = "A categoria e obrigatoria")
        String categoria,

        @NotNull(message = "As calorias sao obrigatorias")
        @Positive(message = "As calorias devem ser um valor positivo")
        Integer calorias,

        @NotNull(message = "A quantidade e obrigatoria")
        @Positive(message = "A quantidade deve ser positiva")
        Double quantidade,

        @NotBlank(message = "A unidade de medida e obrigatoria")
        String unidadeMedida
) {
}
