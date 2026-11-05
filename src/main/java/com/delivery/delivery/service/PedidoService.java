package com.delivery.delivery.service;

import com.delivery.delivery.exception.EstoqueInsuficienteException;
import com.delivery.delivery.exception.PratoNaoEncontradoException;
import com.delivery.delivery.model.Pedido;
import com.delivery.delivery.model.Prato;
import com.delivery.delivery.repository.PedidoRepository;
import com.delivery.delivery.repository.PratoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class PedidoService {

    private final PratoRepository pratoRepository;
    private final PedidoRepository pedidoRepository;

    public PedidoService(PratoRepository pratoRepository, PedidoRepository pedidoRepository) {
        this.pratoRepository = pratoRepository;
        this.pedidoRepository = pedidoRepository;
    }

    // Tudo ou nada: se algo falhar no meio, o Spring desfaz o que ja foi gravado
    @Transactional
    public Pedido criarPedido(Long pratoId, Integer quantidade) {

        // 01 - Debitar estoque
        Prato prato = pratoRepository.findById(pratoId)
                .orElseThrow(() -> new PratoNaoEncontradoException(pratoId));

        if (prato.getEstoque() < quantidade) {
            throw new EstoqueInsuficienteException(
                    "Estoque insuficiente. Disponivel: " + prato.getEstoque());
        }

        prato.setEstoque(prato.getEstoque() - quantidade);
        pratoRepository.save(prato);

        // 02 - Calcular valor total
        BigDecimal valorTotal = prato.getValor().multiply(BigDecimal.valueOf(quantidade));

        // 03 - Salvar o pedido
        Pedido pedido = new Pedido();
        pedido.setPrato(prato);
        pedido.setQuantidade(quantidade);
        pedido.setValorTotal(valorTotal);

        return pedidoRepository.save(pedido);
    }
}