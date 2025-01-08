package com.admin_pedidos.jean.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin_pedidos.jean.service.TallajeService;
import com.admin_pedidos.jean.entity.Tallaje;

@RestController
@RequestMapping("/tallaje")
public class TallajeController {

    @Autowired
    private TallajeService tallajeService;

    @GetMapping
    public List<Tallaje> getAll() {
        return tallajeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tallaje> getById(@PathVariable String id) {
        return tallajeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Tallaje create(@RequestBody Tallaje tallaje) {
        return tallajeService.save(tallaje);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tallaje> update(@PathVariable String id, @RequestBody Tallaje tallaje) {
        try {
            return ResponseEntity.ok(tallajeService.update(id, tallaje));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        tallajeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
