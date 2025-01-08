package com.admin_pedidos.jean.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin_pedidos.jean.entity.Marca;
import com.admin_pedidos.jean.repository.MarcaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    public List<Marca> findAll() {
        return marcaRepository.findAll();
    }

    public Optional<Marca> findById(String id) {
        return marcaRepository.findById(id);
    }

    public Marca save(Marca marca) {
        return marcaRepository.save(marca);
    }

    public Marca update(String id, Marca marca) {
        if (marcaRepository.existsById(id)) {
            marca.setCodmar(id);
            return marcaRepository.save(marca);
        }
        throw new RuntimeException("Marca not found");
    }

    public void deleteById(String id) {
        marcaRepository.deleteById(id);
    }
}
