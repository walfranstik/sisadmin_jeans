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

import com.admin_pedidos.jean.service.LineaService;
import com.admin_pedidos.jean.entity.Linea;

@RestController
@RequestMapping("/lineas")
public class LineaController {

    @Autowired
    private LineaService lineaService;

    @GetMapping
    public List<Linea> getAll() {
        return lineaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Linea> getById(@PathVariable String id) {
        return lineaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Linea create(@RequestBody Linea linea) {
        return lineaService.save(linea);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Linea> update(@PathVariable String id, @RequestBody Linea linea) {
        try {
            return ResponseEntity.ok(lineaService.update(id, linea));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        lineaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}