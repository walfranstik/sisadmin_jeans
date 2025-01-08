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

import com.admin_pedidos.jean.service.DirectorioService;
import com.admin_pedidos.jean.entity.Directorio;

@RestController
@RequestMapping("/directorios")
public class DirectorioController {

    @Autowired
    private DirectorioService directorioService;

    @GetMapping
    public List<Directorio> getAll() {
        return directorioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Directorio> getById(@PathVariable String id) {
        return directorioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Directorio create(@RequestBody Directorio directorio) {
        return directorioService.save(directorio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Directorio> update(@PathVariable String id, @RequestBody Directorio directorio) {
        try {
            return ResponseEntity.ok(directorioService.update(id, directorio));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        directorioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}