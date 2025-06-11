package com.admin_pedidos.jean.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin_pedidos.jean.entity.Pedido;
import com.admin_pedidos.jean.repository.PedidoRepository;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;



    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public Page<Pedido> searchPedidos(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return pedidoRepository.search(keyword, pageable);
    }

    public Optional<Pedido> findById(long id) {
        return pedidoRepository.findById(id);
    }

    public List<Pedido> findByVddorAndColeccion(String vendedor, String coleccion) {
        return pedidoRepository.findByVddorAndCol(vendedor, coleccion);
    }

    public List<Pedido> findByClteAndColeccion(String clte, String coleccion) {
        return pedidoRepository.findByClteAndCol(clte, coleccion);
    }

    public List<Pedido> findByRefAndColeccion(String ref, String coleccion) {
        return pedidoRepository.findByRefAndCol(ref, coleccion);
    }

    public List<Pedido> findByRefAndColeccionAndVendedor(String ref, String coleccion, String vendedor) {
        return pedidoRepository.findByRefAndColAndVddor(ref, coleccion,vendedor);
    }

    @Transactional
    public void deleteByNumped(String numped) {
        pedidoRepository.deleteByNumped(numped);
    }
    

    public boolean existsByNumped( String numped) {
        return pedidoRepository.existsByNumped(numped);
    }

    public List<Pedido> findByNumped( String numped) {
        return pedidoRepository.findByNumped(numped);
    }

    public List<Pedido> findByColeccion(String coleccion) {
        return pedidoRepository.findByCol(coleccion);
    }

    public Pedido save(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }


   
}
