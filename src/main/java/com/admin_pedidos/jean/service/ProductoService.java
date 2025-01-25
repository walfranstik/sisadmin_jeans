package com.admin_pedidos.jean.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin_pedidos.jean.entity.Producto;
import com.admin_pedidos.jean.repository.ProductoRepository;

import java.util.List;
import java.util.Optional;


@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }
    public Page<Producto> searchProductos(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productoRepository.search(keyword, pageable);
    }

    public Optional<Producto> findById(int id) {
        return productoRepository.findById(id);
    }

    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto update(int id, Producto producto) {
        if (productoRepository.existsById(id)) {
            producto.setId_pdto(id);
            return productoRepository.save(producto);
        }
        throw new RuntimeException("Producto not found");
    }

    public void deleteById(int id) {
        productoRepository.deleteById(id);
    }
}