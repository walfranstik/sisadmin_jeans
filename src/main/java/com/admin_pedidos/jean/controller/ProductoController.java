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

import java.util.HashMap;
import java.util.stream.Collectors;

import com.admin_pedidos.jean.service.ProductoService;
import com.admin_pedidos.jean.service.ColeccionService;
import com.admin_pedidos.jean.service.MarcaService;
import com.admin_pedidos.jean.service.LineaService;
import com.admin_pedidos.jean.service.TipoPrendaService;
import com.admin_pedidos.jean.service.TallajeService;
import com.admin_pedidos.jean.entity.Coleccion;
import com.admin_pedidos.jean.entity.Linea;
import com.admin_pedidos.jean.entity.Marca;
import com.admin_pedidos.jean.entity.Producto;
import com.admin_pedidos.jean.entity.Tallaje;
import com.admin_pedidos.jean.entity.TipoPrenda;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ColeccionService ColeccionService;
    @Autowired
    private MarcaService MarcaService;
    @Autowired
    private LineaService LineaService;
    @Autowired
    private TipoPrendaService TipoPrendaService;
    @Autowired
    private TallajeService TallajeService;

    // Mostrar todos los productos
    @GetMapping
    public String listProductos(Model model) {
        model.addAttribute("productos", productoService.findAll());
        model.addAttribute("colecciones", ColeccionService.findAll().stream()
        .collect(Collectors.toMap(
            Coleccion::getCodcole, // Clave: codCole
            Coleccion::getDescole, // Valor: desCole
            (existing, replacement) -> existing, // Resolver duplicados
            HashMap::new // Crear un HashMap
        )));

        model.addAttribute("marcas", MarcaService.findAll().stream()
            .collect(Collectors.toMap(
                Marca::getCodmar, // Clave: codMar
                Marca::getDesmar, // Valor: desMar
                (existing, replacement) -> existing,
                HashMap::new
            )));

        model.addAttribute("lineas", LineaService.findAll().stream()
            .collect(Collectors.toMap(
                Linea::getCodlin, // Clave: codLin
                Linea::getDeslin, // Valor: desLin
                (existing, replacement) -> existing,
                HashMap::new
            )));

        model.addAttribute("tipos", TipoPrendaService.findAll().stream()
            .collect(Collectors.toMap(
                TipoPrenda::getCodpre, // Clave: codPre
                TipoPrenda::getDespre, // Valor: desPre
                (existing, replacement) -> existing,
                HashMap::new
            )));

        model.addAttribute("tallas", TallajeService.findAll().stream()
            .collect(Collectors.toMap(
                Tallaje::getCodtall, // Clave: codTall
                Tallaje::getDestall, // Valor: desTall
                (existing, replacement) -> existing,
                HashMap::new
            )));

        return "productos/list_productos"; // Renderiza la plantilla list_productos.html en templates/productos
    }

    // Mostrar un formulario para crear un nuevo producto
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("colecciones", ColeccionService.findAll());
        

        model.addAttribute("marcas", MarcaService.findAll().stream()
            .collect(Collectors.toMap(
                Marca::getCodmar, // Clave: codMar
                Marca::getDesmar, // Valor: desMar
                (existing, replacement) -> existing,
                HashMap::new
            )));
            
        model.addAttribute("lineas", LineaService.findAll().stream()
            .collect(Collectors.toMap(
                Linea::getCodlin, // Clave: codLin
                Linea::getDeslin, // Valor: desLin
                (existing, replacement) -> existing,
                HashMap::new
            )));
            
        model.addAttribute("tipos", TipoPrendaService.findAll().stream()
            .collect(Collectors.toMap(
                TipoPrenda::getCodpre, // Clave: codPre
                TipoPrenda::getDespre, // Valor: desPre
                (existing, replacement) -> existing,
                HashMap::new
            )));
            
            model.addAttribute("tallas", TallajeService.findAll().stream()
            .collect(Collectors.toMap(
                Tallaje::getCodtall, // Clave: codTall
                tallaje -> new Object[]{ // Valor: un array que incluye desTall y los valores t1 a t10
                    tallaje.getDestall(),
                    tallaje.getT1(),
                    tallaje.getT2(),
                    tallaje.getT3(),
                    tallaje.getT4(),
                    tallaje.getT5(),
                    tallaje.getT6(),
                    tallaje.getT7(),
                    tallaje.getT8(),
                    tallaje.getT9(),
                    tallaje.getT10()
                },
                (existing, replacement) -> existing, // Resolver duplicados
                HashMap::new // Crear un HashMap
            )));
        
        return "productos/create_producto"; // Renderiza la plantilla create_producto.html en templates/productos
    }

    // Guardar un nuevo producto
    @PostMapping
    public String saveProducto(@Valid @ModelAttribute Producto producto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("producto", producto);
            model.addAttribute("colecciones", ColeccionService.findAll());



        model.addAttribute("marcas", MarcaService.findAll().stream()
            .collect(Collectors.toMap(
                Marca::getCodmar, // Clave: codMar
                Marca::getDesmar, // Valor: desMar
                (existing, replacement) -> existing,
                HashMap::new
            )));
            
        model.addAttribute("lineas", LineaService.findAll().stream()
            .collect(Collectors.toMap(
                Linea::getCodlin, // Clave: codLin
                Linea::getDeslin, // Valor: desLin
                (existing, replacement) -> existing,
                HashMap::new
            )));
            
        model.addAttribute("tipos", TipoPrendaService.findAll().stream()
            .collect(Collectors.toMap(
                TipoPrenda::getCodpre, // Clave: codPre
                TipoPrenda::getDespre, // Valor: desPre
                (existing, replacement) -> existing,
                HashMap::new
            )));
            
            model.addAttribute("tallas", TallajeService.findAll().stream()
            .collect(Collectors.toMap(
                Tallaje::getCodtall, // Clave: codTall
                tallaje -> new Object[]{ // Valor: un array que incluye desTall y los valores t1 a t10
                    tallaje.getDestall(),
                    tallaje.getT1(),
                    tallaje.getT2(),
                    tallaje.getT3(),
                    tallaje.getT4(),
                    tallaje.getT5(),
                    tallaje.getT6(),
                    tallaje.getT7(),
                    tallaje.getT8(),
                    tallaje.getT9(),
                    tallaje.getT10()
                },
                (existing, replacement) -> existing, // Resolver duplicados
                HashMap::new // Crear un HashMap
            )));
            return "productos/create_producto"; // Vuelve al formulario si hay errores
        }
        productoService.save(producto);
        return "redirect:/productos"; // Redirige a la lista de productos
    }

    // Mostrar un formulario para editar un producto
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Producto producto = productoService.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        model.addAttribute("producto", producto);
        
        model.addAttribute("colecciones", ColeccionService.findAll());


        model.addAttribute("marcas", MarcaService.findAll().stream()
            .collect(Collectors.toMap(
                Marca::getCodmar, // Clave: codMar
                Marca::getDesmar, // Valor: desMar
                (existing, replacement) -> existing,
                HashMap::new
            )));
            
        model.addAttribute("lineas", LineaService.findAll().stream()
            .collect(Collectors.toMap(
                Linea::getCodlin, // Clave: codLin
                Linea::getDeslin, // Valor: desLin
                (existing, replacement) -> existing,
                HashMap::new
            )));
            
        model.addAttribute("tipos", TipoPrendaService.findAll().stream()
            .collect(Collectors.toMap(
                TipoPrenda::getCodpre, // Clave: codPre
                TipoPrenda::getDespre, // Valor: desPre
                (existing, replacement) -> existing,
                HashMap::new
            )));
            
            model.addAttribute("tallas", TallajeService.findAll().stream()
            .collect(Collectors.toMap(
                Tallaje::getCodtall, // Clave: codTall
                tallaje -> new Object[]{ // Valor: un array que incluye desTall y los valores t1 a t10
                    tallaje.getDestall(),
                    tallaje.getT1(),
                    tallaje.getT2(),
                    tallaje.getT3(),
                    tallaje.getT4(),
                    tallaje.getT5(),
                    tallaje.getT6(),
                    tallaje.getT7(),
                    tallaje.getT8(),
                    tallaje.getT9(),
                    tallaje.getT10()
                },
                (existing, replacement) -> existing, // Resolver duplicados
                HashMap::new // Crear un HashMap
            )));
        return "productos/edit_producto"; // Renderiza la plantilla edit_producto.html en templates/productos
    }

    // Actualizar un producto
    @PostMapping("/update/{id}")
    public String updateProducto(@PathVariable int id, @Valid @ModelAttribute Producto producto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            producto.setId_pdto(id);
            model.addAttribute("producto", producto);
            model.addAttribute("colecciones", ColeccionService.findAll());


        model.addAttribute("marcas", MarcaService.findAll().stream()
            .collect(Collectors.toMap(
                Marca::getCodmar, // Clave: codMar
                Marca::getDesmar, // Valor: desMar
                (existing, replacement) -> existing,
                HashMap::new
            )));
            
        model.addAttribute("lineas", LineaService.findAll().stream()
            .collect(Collectors.toMap(
                Linea::getCodlin, // Clave: codLin
                Linea::getDeslin, // Valor: desLin
                (existing, replacement) -> existing,
                HashMap::new
            )));
            
        model.addAttribute("tipos", TipoPrendaService.findAll().stream()
            .collect(Collectors.toMap(
                TipoPrenda::getCodpre, // Clave: codPre
                TipoPrenda::getDespre, // Valor: desPre
                (existing, replacement) -> existing,
                HashMap::new
            )));
            
            model.addAttribute("tallas", TallajeService.findAll().stream()
            .collect(Collectors.toMap(
                Tallaje::getCodtall, // Clave: codTall
                tallaje -> new Object[]{ // Valor: un array que incluye desTall y los valores t1 a t10
                    tallaje.getDestall(),
                    tallaje.getT1(),
                    tallaje.getT2(),
                    tallaje.getT3(),
                    tallaje.getT4(),
                    tallaje.getT5(),
                    tallaje.getT6(),
                    tallaje.getT7(),
                    tallaje.getT8(),
                    tallaje.getT9(),
                    tallaje.getT10()
                },
                (existing, replacement) -> existing, // Resolver duplicados
                HashMap::new // Crear un HashMap
            )));
            return "productos/edit_producto"; // Vuelve al formulario si hay errores
        }
        productoService.update(id, producto);
        return "redirect:/productos"; // Redirige a la lista de productos
    }

    // Eliminar un producto
    @GetMapping("/delete/{id}")
    public String deleteProducto(@PathVariable int id) {
        productoService.deleteById(id);
        return "redirect:/productos"; // Redirige a la lista de productos
    }
}
