package com.admin_pedidos.jean.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin_pedidos.jean.entity.Linea;
import com.admin_pedidos.jean.repository.LineaRepository;

import java.util.List;
import java.util.Optional;


@Service
public class LineaService {

    @Autowired
    private LineaRepository lineaRepository;

    public List<Linea> findAll() {
        return lineaRepository.findAll();
    }

    public Optional<Linea> findById(String id) {
        return lineaRepository.findById(id);
    }

    public Linea save(Linea linea) {
        return lineaRepository.save(linea);
    }

    public Linea update(String id, Linea linea) {
        if (lineaRepository.existsById(id)) {
            linea.setCodlin(id);
            return lineaRepository.save(linea);
        }
        throw new RuntimeException("Linea not found");
    }

    public void deleteById(String id) {
        lineaRepository.deleteById(id);
    }
}