package com.delivery.delivery.controller;

import com.delivery.delivery.model.Pedido;
import com.delivery.delivery.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<Pedido> criar(@RequestParam Long pratoId,
                                        @RequestParam Integer quantidade) {
        Pedido pedido = pedidoService.criarPedido(pratoId, quantidade);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }
}