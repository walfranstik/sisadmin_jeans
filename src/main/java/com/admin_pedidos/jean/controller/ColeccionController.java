package com.admin_pedidos.jean.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.admin_pedidos.jean.service.ColeccionService;
import com.admin_pedidos.jean.entity.Coleccion;

@Controller
@RequestMapping("/colecciones")
public class ColeccionController {

    @Autowired
    private ColeccionService coleccionService;

    // Mostrar todas las colecciones
    @GetMapping
    public String listColecciones(Model model) {
        model.addAttribute("colecciones", coleccionService.findAll());
        return "colecciones/list_colecciones"; // Renderiza la plantilla list_colecciones.html en templates/colecciones
    }

    // Mostrar un formulario para crear una nueva colección
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("coleccion", new Coleccion());
        return "colecciones/create_coleccion"; // Renderiza la plantilla create_colecciones.html en templates/colecciones
    }

    // Guardar una nueva colección
    @PostMapping
    public String saveColeccion(@ModelAttribute Coleccion coleccion) {
        coleccionService.save(coleccion);
        return "redirect:/colecciones"; // Redirige a la lista de colecciones
    }

    // Mostrar un formulario para editar una colección
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Coleccion coleccion = coleccionService.findById(id)
                .orElseThrow(() -> new RuntimeException("Colección no encontrada"));
        model.addAttribute("coleccion", coleccion);
        return "colecciones/edit_coleccion"; // Renderiza la plantilla edit.html en templates/colecciones
    }

    // Actualizar una colección
    @PostMapping("/update/{id}")
    public String updateColeccion(@PathVariable String id, @ModelAttribute Coleccion coleccion) {
        coleccionService.update(id, coleccion);
        return "redirect:/colecciones"; // Redirige a la lista de colecciones
    }

    // Eliminar una colección
    @GetMapping("/delete/{id}")
    public String deleteColeccion(@PathVariable String id) {
        coleccionService.deleteById(id);
        return "redirect:/colecciones"; // Redirige a la lista de colecciones
    }
}
