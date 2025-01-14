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

import com.admin_pedidos.jean.service.MunicipioService;
import com.admin_pedidos.jean.entity.Municipio;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/municipios")
public class MunicipioController {

    @Autowired
    private MunicipioService municipioService;

    // Mostrar todos los municipios
    @GetMapping
    public String listMunicipios(Model model) {
        model.addAttribute("municipios", municipioService.findAll());
        return "municipios/list_municipios"; // Renderiza la plantilla list_municipios.html en templates/municipios
    }

    // Mostrar un formulario para crear un nuevo municipio
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("municipio", new Municipio());
        return "municipios/create_municipio"; // Renderiza la plantilla create_municipio.html en templates/municipios
    }

    // Guardar un nuevo municipio
    @PostMapping
    public String saveMunicipio(@Valid @ModelAttribute Municipio municipio, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("municipio", municipio);
            return "municipios/create_municipio"; // Vuelve al formulario si hay errores
        }
        municipioService.save(municipio);
        return "redirect:/municipios"; // Redirige a la lista de municipios
    }

    // Mostrar un formulario para editar un municipio
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Municipio municipio = municipioService.findById(id)
                .orElseThrow(() -> new RuntimeException("Municipio no encontrado"));
        model.addAttribute("municipio", municipio);
        return "municipios/edit_municipio"; // Renderiza la plantilla edit_municipio.html en templates/municipios
    }

    // Actualizar un municipio
    @PostMapping("/update/{id}")
    public String updateMunicipio(@PathVariable int id, @Valid @ModelAttribute Municipio municipio, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("municipio", municipio);
            return "municipios/edit_municipio"; // Vuelve al formulario si hay errores
        }
        municipioService.update(id, municipio);
        return "redirect:/municipios"; // Redirige a la lista de municipios
    }

    // Eliminar un municipio
    @GetMapping("/delete/{id}")
    public String deleteMunicipio(@PathVariable int id) {
        municipioService.deleteById(id);
        return "redirect:/municipios"; // Redirige a la lista de municipios
    }
}
