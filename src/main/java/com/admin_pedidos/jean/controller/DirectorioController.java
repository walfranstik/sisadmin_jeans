package com.admin_pedidos.jean.controller;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.admin_pedidos.jean.service.DirectorioService;
import com.admin_pedidos.jean.service.MunicipioService;

import jakarta.validation.Valid;
import com.admin_pedidos.jean.entity.Directorio;
import com.admin_pedidos.jean.entity.Municipio;

@Controller
@RequestMapping("/directorios")
public class DirectorioController {

    @Autowired
    private DirectorioService directorioService;

    @Autowired
    private MunicipioService municipioService;
    

    // Mostrar todos los directorios
    @GetMapping
    public String listDirectorios(Model model) {
        model.addAttribute("directorios", directorioService.findAll());
        return "directorio/list_directorios"; // Renderiza la plantilla list_directorios.html en templates/directorios
    }

    // Mostrar un formulario para crear un nuevo directorio
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("directorio", new Directorio());

        model.addAttribute("municipios", municipioService.findAll().stream()
        .collect(Collectors.groupingBy(
            Municipio::getDepartamento, // Agrupar por departamento (clave)
            Collectors.mapping(
                Municipio::getNombreMunicipio, // Mapeo: Solo queremos los nombres de las ciudades (valor)
                Collectors.toList() // Agrupar los nombres en una lista
            )
        )));

        model.addAttribute("vendedores", directorioService.findByVddor("True"));

        return "directorio/create_directorio"; // Renderiza la plantilla create_directorio.html en templates/directorios
    }

    // Guardar un nuevo directorio
    @PostMapping
    public String saveDirectorio(@Valid @ModelAttribute Directorio directorio, BindingResult result, Model model) {
       
        if (result.hasErrors()) {
            model.addAttribute("directorio", directorio);
            model.addAttribute("municipios", municipioService.findAll().stream()
        .collect(Collectors.groupingBy(
            Municipio::getDepartamento, // Agrupar por departamento (clave)
            Collectors.mapping(
                Municipio::getNombreMunicipio, // Mapeo: Solo queremos los nombres de las ciudades (valor)
                Collectors.toList() // Agrupar los nombres en una lista
            )
        )));

        model.addAttribute("vendedores", directorioService.findByVddor("True"));
        
            return "directorio/create_directorio"; // Vuelve al formulario si hay errores
        }
        if (directorioService.existsByNitdir(directorio.getNitdir())) {
            result.rejectValue("nitdir", "error.directorio", "El NIT ya existe. Por favor, usa otro.");
            model.addAttribute("directorio", directorio);
            model.addAttribute("municipios", municipioService.findAll().stream()
        .collect(Collectors.groupingBy(
            Municipio::getDepartamento, // Agrupar por departamento (clave)
            Collectors.mapping(
                Municipio::getNombreMunicipio, // Mapeo: Solo queremos los nombres de las ciudades (valor)
                Collectors.toList() // Agrupar los nombres en una lista
            )
        )));

        model.addAttribute("vendedores", directorioService.findByVddor("True"));
            return "directorio/create_directorio"; // Renderiza la plantilla create_colecciones.html en templates/colecciones
        }

        if (directorio.getVddor()==null) {
            directorio.setVddor("False");
        }
        if (directorio.getClte()==null) {
            directorio.setClte("False");
        }
        if (directorio.getEmpl()==null) {
            directorio.setEmpl("False");
        }
        directorioService.save(directorio);
        return "redirect:/directorios"; // Redirige a la lista de directorios
    }

    // Mostrar un formulario para editar un directorio
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        Directorio directorio = directorioService.findById(id)
                .orElseThrow(() -> new RuntimeException("Directorio no encontrado"));
        model.addAttribute("directorio", directorio);
        model.addAttribute("municipios", municipioService.findAll().stream()
        .collect(Collectors.groupingBy(
            Municipio::getDepartamento, // Agrupar por departamento (clave)
            Collectors.mapping(
                Municipio::getNombreMunicipio, // Mapeo: Solo queremos los nombres de las ciudades (valor)
                Collectors.toList() // Agrupar los nombres en una lista
            )
        )));

        model.addAttribute("vendedores", directorioService.findByVddor("True"));
        return "directorio/edit_directorio"; // Renderiza la plantilla edit_directorio.html en templates/directorios
    }

    // Actualizar un directorio
    @PostMapping("/update/{id}")
    public String updateDirectorio(@PathVariable String id, @Valid @ModelAttribute Directorio directorio, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("directorio", directorio);
            model.addAttribute("municipios", municipioService.findAll().stream()
        .collect(Collectors.groupingBy(
            Municipio::getDepartamento, // Agrupar por departamento (clave)
            Collectors.mapping(
                Municipio::getNombreMunicipio, // Mapeo: Solo queremos los nombres de las ciudades (valor)
                Collectors.toList() // Agrupar los nombres en una lista
            )
        )));

        model.addAttribute("vendedores", directorioService.findByVddor("True"));
            return "directorio/edit_directorio"; // Vuelve al formulario si hay errores
        }
        if (directorio.getVddor()==null) {
            directorio.setVddor("False");
        }
        if (directorio.getClte()==null) {
            directorio.setClte("False");
        }
        if (directorio.getEmpl()==null) {
            directorio.setEmpl("False");
        }
        directorioService.update(id, directorio);
        return "redirect:/directorios"; // Redirige a la lista de directorios
    }

    // Eliminar un directorio
    @GetMapping("/delete/{id}")
    public String deleteDirectorio(@PathVariable String id) {
        directorioService.deleteById(id);
        return "redirect:/directorios"; // Redirige a la lista de directorios
    }
}
