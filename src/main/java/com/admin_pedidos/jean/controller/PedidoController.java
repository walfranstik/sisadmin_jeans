package com.admin_pedidos.jean.controller;


import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.admin_pedidos.jean.service.ColeccionService;
import com.admin_pedidos.jean.service.DirectorioService;
import com.admin_pedidos.jean.service.PedidoService;
import com.admin_pedidos.jean.service.ProductoService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.admin_pedidos.jean.entity.Pedido;
import com.admin_pedidos.jean.dto.Referencia;


import jakarta.validation.Valid;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ColeccionService coleccionService;

    @Autowired
    private DirectorioService directorioService;

    @Autowired
    private ProductoService productoService;

    // Mostrar todos los pedidos

   

    @GetMapping
    public String listPedidos(
        @RequestParam(defaultValue = "") String keyword,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        Model model) {

        Page<Pedido> pedidosPage = pedidoService.searchPedidos(keyword, page, size);

        model.addAttribute("keyword", (keyword.isEmpty()? "" : keyword));
        model.addAttribute("pedidos", pedidosPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pedidosPage.getTotalPages());
        model.addAttribute("keyword", keyword);

        return "pedidos/list_pedidos";
    }

    // Mostrar un formulario para crear un nuevo pedido
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        Pedido pedido = new Pedido();
        pedido.setFechaped(new Date());
        model.addAttribute("pedido", pedido);
        model.addAttribute("colecciones", coleccionService.findAll());
        model.addAttribute("vendedores", directorioService.findByVddor("True"));
        model.addAttribute("clientes", directorioService.findByClte("True"));
        model.addAttribute("productos", productoService.findAll());
        model.addAttribute("referenciasCompradas", "[]");



        return "pedidos/create_pedido"; // Renderiza la plantilla create_pedido.html en templates/pedidos
    }

    // Guardar un nuevo pedido
    @PostMapping
    public String savePedido(@RequestParam("referencias") String referenciasJson, @Valid @ModelAttribute Pedido pedido, BindingResult result, Model model) {
        if (result.hasErrors()) {
            pedido.setFechaped(new Date());
            model.addAttribute("pedido", pedido);
            model.addAttribute("colecciones", coleccionService.findAll());
            model.addAttribute("vendedores", directorioService.findByVddor("True"));
            model.addAttribute("clientes", directorioService.findByClte("True"));
            model.addAttribute("productos", productoService.findAll());
            model.addAttribute("referenciasCompradas", referenciasJson != null ? referenciasJson : "[]"); // JSON o vacío    
            return "pedidos/create_pedido"; // Vuelve al formulario si hay errores
        }
                ObjectMapper objectMapper = new ObjectMapper();
                try {
            // Deserializar el JSON a una lista de mapas
            List<Referencia> referencias = objectMapper.readValue(referenciasJson, new TypeReference<List<Referencia>>() {});


            if(referencias.isEmpty()){
            result.rejectValue("ref", "required.pedido", "Tienen que haber referencias compradas para realizar el pedido");
            pedido.setFechaped(new Date());
            model.addAttribute("pedido", pedido);
            model.addAttribute("colecciones", coleccionService.findAll());
            model.addAttribute("vendedores", directorioService.findByVddor("True"));
            model.addAttribute("clientes", directorioService.findByClte("True"));
            model.addAttribute("productos", productoService.findAll());
            model.addAttribute("referenciasCompradas", referenciasJson != null ? referenciasJson : "[]"); // JSON o vacío    
            return "pedidos/create_pedido"; // Vuelve al formulario si hay errores
            }
            // Iterar sobre la lista de referencias
        for (Referencia referencia : referencias) {
            // Crear un nuevo objeto Pedido para cada referencia
            Pedido nuevoPedido = new Pedido();

            // Copiar los valores comunes del pedido original
            nuevoPedido.setFechaped(pedido.getFechaped());
            nuevoPedido.setVddor(pedido.getVddor());
            nuevoPedido.setCol(pedido.getCol());
            nuevoPedido.setNumped(pedido.getNumped());
            nuevoPedido.setClte(pedido.getClte());
            nuevoPedido.setTotal(pedido.getTotal());
            nuevoPedido.setTprendas(pedido.getTprendas());
            nuevoPedido.setObsgen(pedido.getObsgen());

            // Asignar los valores específicos de la referencia
            nuevoPedido.setT1(referencia.getT1());
            nuevoPedido.setT2(referencia.getT2());
            nuevoPedido.setT3(referencia.getT3());
            nuevoPedido.setT4(referencia.getT4());
            nuevoPedido.setT5(referencia.getT5());
            nuevoPedido.setT6(referencia.getT6());
            nuevoPedido.setT7(referencia.getT7());
            nuevoPedido.setT8(referencia.getT8());
            nuevoPedido.setT9(referencia.getT9());
            nuevoPedido.setT10(referencia.getT10());
            nuevoPedido.setRef(referencia.getReferencia());
            nuevoPedido.setPrendref(referencia.getCantidad());
            nuevoPedido.setVuniref(referencia.getPrecio());
            nuevoPedido.setObsref(referencia.getObs());
            nuevoPedido.setDescref(referencia.getDescref());

            // Guardar el nuevo pedido en la base de datos
            pedidoService.save(nuevoPedido);
        }
        }catch (IOException e) {
            e.printStackTrace();
            // Manejar la excepción
        }

        // pedidoService.save(pedido);
        return "redirect:/pedidos"; // Redirige a la lista de pedidos
    }


    // Eliminar un pedido
    @GetMapping("/delete/{id}")
    public String deletePedido(@PathVariable Long id) {
        pedidoService.deleteById(id);
        return "redirect:/pedidos"; // Redirige a la lista de pedidos
    }
}
