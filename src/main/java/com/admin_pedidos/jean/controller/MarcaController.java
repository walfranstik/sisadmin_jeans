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

import com.admin_pedidos.jean.service.MarcaService;
import com.admin_pedidos.jean.entity.Marca;

@RestController
@RequestMapping("/marcas")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @GetMapping
    public List<Marca> getAll() {
        return marcaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Marca> getById(@PathVariable String id) {
        return marcaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Marca create(@RequestBody Marca marca) {
        return marcaService.save(marca);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Marca> update(@PathVariable String id, @RequestBody Marca marca) {
        try {
            return ResponseEntity.ok(marcaService.update(id, marca));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        marcaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
