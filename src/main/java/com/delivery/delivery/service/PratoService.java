package com.delivery.delivery.service;

import com.delivery.delivery.dto.PratoRequestDTO;
import com.delivery.delivery.dto.PratoResponseDTO;
import com.delivery.delivery.exception.NomeDuplicadoException;
import com.delivery.delivery.exception.PratoNaoEncontradoException;
import com.delivery.delivery.model.Prato;
import com.delivery.delivery.repository.PratoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PratoService {

    private final PratoRepository pratoRepository;

    // Injecao via construtor - nada de lista em memoria ou acesso a banco no Controller
    public PratoService(PratoRepository pratoRepository) {
        this.pratoRepository = pratoRepository;
    }

    public PratoResponseDTO criar(PratoRequestDTO dto) {
        // Regra de negocio propria: nao permitir dois pratos com o mesmo nome
        // (comparacao ignorando maiusculas/minusculas)
        if (pratoRepository.existsByNomeIgnoreCase(dto.nome())) {
            throw new NomeDuplicadoException(dto.nome());
        }

        Prato prato = toEntity(dto);
        Prato salvo = pratoRepository.save(prato);
        return toDTO(salvo);
    }

    public List<PratoResponseDTO> listarTodos() {
        return pratoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public PratoResponseDTO buscarPorId(Long id) {
        Prato prato = pratoRepository.findById(id)
                .orElseThrow(() -> new PratoNaoEncontradoException(id));
        return toDTO(prato);
    }

    public List<PratoResponseDTO> listarPorCategoria(String categoria) {
        return pratoRepository.findByCategoria(categoria)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public PratoResponseDTO atualizar(Long id, PratoRequestDTO dto) {
        Prato prato = pratoRepository.findById(id)
                .orElseThrow(() -> new PratoNaoEncontradoException(id));

        prato.setNome(dto.nome());
        prato.setDescricao(dto.descricao());
        prato.setValor(dto.valor());
        prato.setCategoria(dto.categoria());
        prato.setCalorias(dto.calorias());
        prato.setQuantidade(dto.quantidade());
        prato.setUnidadeMedida(dto.unidadeMedida());

        Prato atualizado = pratoRepository.save(prato);
        return toDTO(atualizado);
    }

    public void remover(Long id) {
        if (!pratoRepository.existsById(id)) {
            throw new PratoNaoEncontradoException(id);
        }
        pratoRepository.deleteById(id);
    }

    // ---- Desafios extras ----

    // Desafio extra 1: atualizar somente o campo valor
    public PratoResponseDTO atualizarValor(Long id, BigDecimal novoValor) {
        Prato prato = pratoRepository.findById(id)
                .orElseThrow(() -> new PratoNaoEncontradoException(id));
        prato.setValor(novoValor);
        return toDTO(pratoRepository.save(prato));
    }

    // Desafio extra 2: filtrar pratos com ate X calorias
    public List<PratoResponseDTO> listarPorCaloriasMax(Integer max) {
        return pratoRepository.findByCaloriasLessThanEqual(max)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // ---- Conversoes ----

    private Prato toEntity(PratoRequestDTO dto) {
        Prato prato = new Prato();
        prato.setNome(dto.nome());
        prato.setDescricao(dto.descricao());
        prato.setValor(dto.valor());
        prato.setCategoria(dto.categoria());
        prato.setCalorias(dto.calorias());
        prato.setQuantidade(dto.quantidade());
        prato.setUnidadeMedida(dto.unidadeMedida());
        return prato;
    }

    private PratoResponseDTO toDTO(Prato prato) {
        return new PratoResponseDTO(
                prato.getId(),
                prato.getNome(),
                prato.getDescricao(),
                prato.getValor(),
                prato.getCategoria(),
                prato.getCalorias(),
                prato.getQuantidade(),
                prato.getUnidadeMedida()
        );
    }
}