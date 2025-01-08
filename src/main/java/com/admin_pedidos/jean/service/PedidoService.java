package com.admin_pedidos.jean.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin_pedidos.jean.entity.Pedido;
import com.admin_pedidos.jean.repository.PedidoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> findById(long id) {
        return pedidoRepository.findById(id);
    }

    public Pedido save(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public Pedido update(long id, Pedido pedido) {
        if (pedidoRepository.existsById(id)) {
            pedido.setId_pedido(id);
            return pedidoRepository.save(pedido);
        }
        throw new RuntimeException("Pedido not found");
    }

    public void deleteById(long id) {
        pedidoRepository.deleteById(id);
    }
}
