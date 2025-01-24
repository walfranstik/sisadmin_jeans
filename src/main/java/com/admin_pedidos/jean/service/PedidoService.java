package com.admin_pedidos.jean.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin_pedidos.jean.entity.Pedido;
import com.admin_pedidos.jean.repository.PedidoRepository;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    
    public Page<Pedido> findAllPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return pedidoRepository.findAll(pageable);
    }

    public Page<Pedido> searchPedidos(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return pedidoRepository.search(keyword, pageable);
    }

    public Optional<Pedido> findById(long id) {
        return pedidoRepository.findById(id);
    }

    public Pedido save(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }


    public void deleteById(long id) {
        pedidoRepository.deleteById(id);
    }
}
