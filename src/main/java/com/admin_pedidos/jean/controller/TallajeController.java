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

import com.admin_pedidos.jean.service.TallajeService;
import jakarta.validation.Valid;
import com.admin_pedidos.jean.entity.Tallaje;

@Controller
@RequestMapping("/tallajes")
public class TallajeController {

    @Autowired
    private TallajeService tallajeService;

    // Mostrar todos los tallajes
    @GetMapping
    public String listTallajes(Model model) {
        model.addAttribute("tallajes", tallajeService.findAll());
        return "tallajes/list_tallajes"; // Renderiza la plantilla list_tallajes.html en templates/tallajes
    }

    // Mostrar un formulario para crear un nuevo tallaje
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("tallaje", new Tallaje());
        return "tallajes/create_tallaje"; // Renderiza la plantilla create_tallaje.html en templates/tallajes
    }

    // Guardar un nuevo tallaje
    @PostMapping
    public String saveTallaje(@Valid @ModelAttribute Tallaje tallaje, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("tallaje", tallaje);
            return "tallajes/create_tallaje"; // Vuelve al formulario si hay errores
        }
         // Verifica si el ID ya existe en la base de datos
    if (tallajeService.existsByCodtall(tallaje.getCodtall())) {
        result.rejectValue("codtall", "error.tallaje", "El código ya existe. Por favor, usa otro.");
        model.addAttribute("tallaje", tallaje);
        return "tallajes/create_tallaje"; // Vuelve al formulario
    }
        tallajeService.save(tallaje);
        return "redirect:/tallajes"; // Redirige a la lista de tallajes
    }

    // Mostrar un formulario para editar un tallaje
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Tallaje tallaje = tallajeService.findById(id)
                .orElseThrow(() -> new RuntimeException("Tallaje no encontrado"));
        model.addAttribute("tallaje", tallaje);
        return "tallajes/edit_tallaje"; // Renderiza la plantilla edit_tallaje.html en templates/tallajes
    }

    // Actualizar un tallaje
    @PostMapping("/update/{id}")
    public String updateTallaje(@PathVariable String id, @Valid @ModelAttribute Tallaje tallaje, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("tallaje", tallaje);
            return "tallajes/edit_tallaje"; // Vuelve al formulario si hay errores
        }
        tallajeService.update(id, tallaje);
        return "redirect:/tallajes"; // Redirige a la lista de tallajes
    }

    // Eliminar un tallaje
    @GetMapping("/delete/{id}")
    public String deleteTallaje(@PathVariable String id) {
        tallajeService.deleteById(id);
        return "redirect:/tallajes"; // Redirige a la lista de tallajes
    }
}
