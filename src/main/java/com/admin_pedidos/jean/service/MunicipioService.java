package com.admin_pedidos.jean.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin_pedidos.jean.entity.Municipio;
import com.admin_pedidos.jean.repository.MunicipioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MunicipioService {

    @Autowired
    private MunicipioRepository municipioRepository;

    public List<Municipio> findAll() {
        return municipioRepository.findAll();
    }

    public Optional<Municipio> findById(int id) {
        return municipioRepository.findById(id);
    }

    public Municipio save(Municipio municipio) {
        return municipioRepository.save(municipio);
    }

    public Municipio update(int id, Municipio municipio) {
        if (municipioRepository.existsById(id)) {
            municipio.setId_municipio(id);
            return municipioRepository.save(municipio);
        }
        throw new RuntimeException("Municipio not found");
    }

    public void deleteById(int id) {
        municipioRepository.deleteById(id);
    }
}