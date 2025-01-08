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

import com.admin_pedidos.jean.service.MunicipioService;
import com.admin_pedidos.jean.entity.Municipio;

@RestController
@RequestMapping("/municipios")
public class MunicipioController {

    @Autowired
    private MunicipioService municipioService;

    @GetMapping
    public List<Municipio> getAll() {
        return municipioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Municipio> getById(@PathVariable int id) {
        return municipioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Municipio create(@RequestBody Municipio municipio) {
        return municipioService.save(municipio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Municipio> update(@PathVariable int id, @RequestBody Municipio municipio) {
        try {
            return ResponseEntity.ok(municipioService.update(id, municipio));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        municipioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
