package com.admin_pedidos.jean.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin_pedidos.jean.entity.TipoPrenda;
import com.admin_pedidos.jean.repository.TipoPrendaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TipoPrendaService {

    @Autowired
    private TipoPrendaRepository tipoPrendaRepository;

    public List<TipoPrenda> findAll() {
        return tipoPrendaRepository.findAll();
    }

    public Optional<TipoPrenda> findById(String id) {
        return tipoPrendaRepository.findById(id);
    }

    public TipoPrenda save(TipoPrenda tipoPrenda) {
        return tipoPrendaRepository.save(tipoPrenda);
    }

    public TipoPrenda update(String id, TipoPrenda tipoPrenda) {
        if (tipoPrendaRepository.existsById(id)) {
            tipoPrenda.setCodpre(id);
            return tipoPrendaRepository.save(tipoPrenda);
        }
        throw new RuntimeException("TipoPrenda not found");
    }

    public void deleteById(String id) {
        tipoPrendaRepository.deleteById(id);
    }
}