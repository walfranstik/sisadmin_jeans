package com.admin_pedidos.jean.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.admin_pedidos.jean.entity.Tallaje;
import com.admin_pedidos.jean.repository.TallajeRepository;

import java.util.List;
import java.util.Optional;


@Service
public class TallajeService {

    @Autowired
    private TallajeRepository tallajeRepository;

    public List<Tallaje> findAll() {
        return tallajeRepository.findAll();
    }

    public Optional<Tallaje> findById(String id) {
        return tallajeRepository.findById(id);
    }

    public Tallaje save(Tallaje tallaje) {
        return tallajeRepository.save(tallaje);
    }

    public Tallaje update(String id, Tallaje tallaje) {
        if (tallajeRepository.existsById(id)) {
            tallaje.setCodtall(id);
            return tallajeRepository.save(tallaje);
        }
        throw new RuntimeException("Tallaje not found");
    }

    public void deleteById(String id) {
        tallajeRepository.deleteById(id);
    }
}

