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

import com.admin_pedidos.jean.service.TipoPrendaService;
import com.admin_pedidos.jean.entity.TipoPrenda;

@RestController
@RequestMapping("/tipo-prenda")
public class TipoPrendaController {

    @Autowired
    private TipoPrendaService tipoPrendaService;

    @GetMapping
    public List<TipoPrenda> getAll() {
        return tipoPrendaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoPrenda> getById(@PathVariable String id) {
        return tipoPrendaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public TipoPrenda create(@RequestBody TipoPrenda tipoPrenda) {
        return tipoPrendaService.save(tipoPrenda);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoPrenda> update(@PathVariable String id, @RequestBody TipoPrenda tipoPrenda) {
        try {
            return ResponseEntity.ok(tipoPrendaService.update(id, tipoPrenda));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        tipoPrendaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
