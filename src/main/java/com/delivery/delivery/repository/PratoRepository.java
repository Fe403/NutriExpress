package com.delivery.delivery.repository;

import com.delivery.delivery.model.Prato;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PratoRepository extends JpaRepository<Prato, Long> {

    // Gerado automaticamente pelo Spring Data JPA a partir do nome do metodo
    List<Prato> findByCategoria(String categoria);

    // Usado na regra de negocio do PratoService (nao permitir nomes duplicados)
    boolean existsByNomeIgnoreCase(String nome);

    // Usado no desafio extra de filtro por calorias
    List<Prato> findByCaloriasLessThanEqual(Integer max);
}