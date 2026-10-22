package com.delivery.delivery.dto;

import java.util.List;

public record ImcResponseDTO(double imc, String classificacao, String categoriaIndicada, List<PratoResponseDTO> refeicoes) {}