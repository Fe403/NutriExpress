package com.delivery.delivery.service;

import org.springframework.stereotype.Service;

@Service
public class ImcService {

    public double calcularImc(double peso, double altura) {
        double imc = peso / (altura * altura);
        return Math.round(imc * 100.0) / 100.0;
    }

    public String classificar(double imc) {
        if (imc < 18.5) return "Abaixo do peso";
        else if (imc < 25) return "Peso normal";
        else if (imc < 30) return "Sobrepeso";
        else return "Obesidade";
    }

    public String categoriaIndicada(String classificacao) {
        return switch (classificacao) {
            case "Abaixo do peso" -> "fitness";
            case "Peso normal"    -> "fitness";
            case "Sobrepeso"      -> "low carb";
            default               -> "vegano";
        };
    }
}
