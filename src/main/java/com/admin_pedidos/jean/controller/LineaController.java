package com.admin_pedidos.jean.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.admin_pedidos.jean.service.LineaService;
import jakarta.validation.Valid;
import com.admin_pedidos.jean.entity.Linea;

@Controller
@RequestMapping("/lineas")
public class LineaController {

    @Autowired
    private LineaService lineaService;

    // Mostrar todas las líneas
    @GetMapping
    public String listLineas(Model model) {
        model.addAttribute("lineas", lineaService.findAll());
        return "lineas/list_lineas"; // Renderiza la plantilla list_lineas.html en templates/lineas
    }

    // Mostrar un formulario para crear una nueva línea
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("linea", new Linea());
        return "lineas/create_linea"; // Renderiza la plantilla create_linea.html en templates/lineas
    }

    // Guardar una nueva línea
    @PostMapping
    public String saveLinea(@Valid @ModelAttribute Linea linea, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("linea", linea);
            return "lineas/create_linea"; // Vuelve al formulario si hay errores
        }
        if (lineaService.existsByCodlin(linea.getCodlin())) {
            result.rejectValue("codlin", "error.linea", "El código ya existe. Por favor, usa otro.");
            model.addAttribute("linea", linea);
            return "lineas/create_linea"; // Vuelve al formulario si hay errores
        }
        lineaService.save(linea);
        return "redirect:/lineas"; // Redirige a la lista de líneas
    }

    // Mostrar un formulario para editar una línea
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Linea linea = lineaService.findById(id)
                .orElseThrow(() -> new RuntimeException("Línea no encontrada"));
        model.addAttribute("linea", linea);
        return "lineas/edit_linea"; // Renderiza la plantilla edit_linea.html en templates/lineas
    }

    // Actualizar una línea
    @PostMapping("/update/{id}")
    public String updateLinea(@PathVariable String id, @Valid @ModelAttribute Linea linea, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("linea", linea);
            return "lineas/edit_linea"; // Vuelve al formulario si hay errores
        }
        lineaService.update(id, linea);
        return "redirect:/lineas"; // Redirige a la lista de líneas
    }

    // Eliminar una línea
    @GetMapping("/delete/{id}")
    public String deleteLinea(@PathVariable String id) {
        lineaService.deleteById(id);
        return "redirect:/lineas"; // Redirige a la lista de líneas
    }
}
