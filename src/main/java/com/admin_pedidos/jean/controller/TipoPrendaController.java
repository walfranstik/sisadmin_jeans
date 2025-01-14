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

import com.admin_pedidos.jean.entity.TipoPrenda;
import com.admin_pedidos.jean.service.TipoPrendaService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/tipo-prenda")
public class TipoPrendaController {

    @Autowired
    private TipoPrendaService tipoPrendaService;

    // Mostrar todos los tipos de prendas
    @GetMapping
    public String listTipoPrendas(Model model) {
        model.addAttribute("tipoPrendas", tipoPrendaService.findAll());
        return "tipo_prenda/list_tipo_prendas"; // Renderiza la plantilla list_tipo_prendas.html en templates/tipo_prenda
    }

    // Mostrar un formulario para crear un nuevo tipo de prenda
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("tipoPrenda", new TipoPrenda());
        return "tipo_prenda/create_tipo_prenda"; // Renderiza la plantilla create_tipo_prenda.html en templates/tipo_prenda
    }

    // Guardar un nuevo tipo de prenda
    @PostMapping
    public String saveTipoPrenda(@Valid @ModelAttribute TipoPrenda tipoPrenda, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("tipoPrenda", tipoPrenda);
            return "tipo_prenda/create_tipo_prenda"; // Vuelve al formulario si hay errores
        }
        // Verifica si el ID ya existe en la base de datos
    if (tipoPrendaService.existsByCodpre(tipoPrenda.getCodpre())) {
        result.rejectValue("codpre", "error.tipoPrenda", "El código ya existe. Por favor, usa otro.");
        model.addAttribute("tipoPrenda", tipoPrenda);
        return "tipo_prenda/create_tipo_prenda"; // Vuelve al formulario
    }
        tipoPrendaService.save(tipoPrenda);
        return "redirect:/tipo-prenda"; // Redirige a la lista de tipos de prendas
    }

    // Mostrar un formulario para editar un tipo de prenda
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        TipoPrenda tipoPrenda = tipoPrendaService.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de prenda no encontrado"));
        model.addAttribute("tipoPrenda", tipoPrenda);
        return "tipo_prenda/edit_tipo_prenda"; // Renderiza la plantilla edit_tipo_prenda.html en templates/tipo_prenda
    }

    // Actualizar un tipo de prenda
    @PostMapping("/update/{id}")
    public String updateTipoPrenda(@PathVariable String id, @Valid @ModelAttribute TipoPrenda tipoPrenda, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("tipoPrenda", tipoPrenda);
            return "tipo_prenda/edit_tipo_prenda"; // Vuelve al formulario si hay errores
        }
        tipoPrendaService.update(id, tipoPrenda);
        return "redirect:/tipo-prenda"; // Redirige a la lista de tipos de prendas
    }

    // Eliminar un tipo de prenda
    @GetMapping("/delete/{id}")
    public String deleteTipoPrenda(@PathVariable String id) {
        tipoPrendaService.deleteById(id);
        return "redirect:/tipo-prenda"; // Redirige a la lista de tipos de prendas
    }
}
