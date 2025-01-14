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

import com.admin_pedidos.jean.service.MarcaService;
import jakarta.validation.Valid;
import com.admin_pedidos.jean.entity.Marca;

@Controller
@RequestMapping("/marcas")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    // Mostrar todas las marcas
    @GetMapping
    public String listMarcas(Model model) {
        model.addAttribute("marcas", marcaService.findAll());
        return "marcas/list_marcas"; // Renderiza la plantilla list_marcas.html en templates/marcas
    }

    // Mostrar un formulario para crear una nueva marca
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("marca", new Marca());
        return "marcas/create_marca"; // Renderiza la plantilla create_marca.html en templates/marcas
    }

    // Guardar una nueva marca
    @PostMapping
    public String saveMarca(@Valid @ModelAttribute Marca marca, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("marca", marca);
            return "marcas/create_marca"; // Vuelve al formulario si hay errores
        }
              // Verifica si el ID ya existe en la base de datos
        if (marcaService.existsByCodmar(marca.getCodmar())) {
            result.rejectValue("codmar", "error.marca", "El código ya existe. Por favor, usa otro.");
            model.addAttribute("marca", marca);
            return "marcas/create_marca"; // Vuelve al formulario si hay errores
        }
        marcaService.save(marca);
        return "redirect:/marcas"; // Redirige a la lista de marcas
    }

    // Mostrar un formulario para editar una marca
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Marca marca = marcaService.findById(id)
                .orElseThrow(() -> new RuntimeException("Marca no encontrada"));
        model.addAttribute("marca", marca);
        return "marcas/edit_marca"; // Renderiza la plantilla edit_marca.html en templates/marcas
    }

    // Actualizar una marca
    @PostMapping("/update/{id}")
    public String updateMarca(@PathVariable String id, @Valid @ModelAttribute Marca marca, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("marca", marca);
            return "marcas/edit_marca"; // Vuelve al formulario si hay errores
        }
        marcaService.update(id, marca);
        return "redirect:/marcas"; // Redirige a la lista de marcas
    }

    // Eliminar una marca
    @GetMapping("/delete/{id}")
    public String deleteMarca(@PathVariable String id) {
        marcaService.deleteById(id);
        return "redirect:/marcas"; // Redirige a la lista de marcas
    }
}
