package com.admin_pedidos.jean.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin_pedidos.jean.entity.Coleccion;
import com.admin_pedidos.jean.repository.ColeccionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ColeccionService {

    @Autowired
    private ColeccionRepository coleccionRepository;

    public List<Coleccion> findAll() {
        return coleccionRepository.findAll();
    }

    public Optional<Coleccion> findById(String id) {
        return coleccionRepository.findById(id);
    }
    
    public boolean existsByCodcole(String codcole) {
        return coleccionRepository.existsByCodcole(codcole);
    }

    public Coleccion save(Coleccion coleccion) {
        return coleccionRepository.save(coleccion);
    }
    public Coleccion update(String id, Coleccion coleccion) {
        if (coleccionRepository.existsById(id)) {
            coleccion.setCodcole(id);
            return coleccionRepository.save(coleccion);
        }
        throw new RuntimeException("Coleccion not found");
    }

    public void deleteById(String id) {
        coleccionRepository.deleteById(id);
    }
}
