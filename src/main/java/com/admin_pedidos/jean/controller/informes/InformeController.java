package com.admin_pedidos.jean.controller.informes;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.admin_pedidos.jean.entity.Pedido;
import com.admin_pedidos.jean.entity.Producto;
import com.admin_pedidos.jean.service.ColeccionService;
import com.admin_pedidos.jean.service.DirectorioService;
import com.admin_pedidos.jean.service.PedidoService;
import com.admin_pedidos.jean.service.ProductoService;
import com.itextpdf.layout.element.Table;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/informes")
public class InformeController {
    @Autowired
    PedidoService pedidoService;
    @Autowired
    DirectorioService directorioService;
    @Autowired
    ColeccionService coleccionService;

    @Autowired
    ProductoService productoService;

     // Métodos para mostrar formularios
     @GetMapping("/vendedor-coleccion")
     public String formVendedorColeccion(Model model) {
         model.addAttribute("vendedores", directorioService.findByVddor("True"));
         model.addAttribute("colecciones", coleccionService.findAll());
         return "informes/vendedor-coleccion";
     }

    @PostMapping("/vendedor-coleccion")
    public void generarInformeVendedorColeccion(
        @RequestParam String vendedor,
        @RequestParam String coleccion,
        HttpServletResponse response) throws IOException {

        Map<String,String> referencias = productoService.findAll().stream()
        .collect(Collectors.toMap(
            Producto::getRef, // Clave: codCole
            Producto::getDescref, // Valor: desCole
            (existing, replacement) -> replacement, // Resolver duplicados
            HashMap::new // Crear un HashMap
        ));
        
        List<Pedido> pedidos = pedidoService.findByVddorAndColeccion(vendedor, coleccion);
        Map<String, Map<String, Integer>> datos = procesarDatos(pedidos);

       

        
        response.setContentType("application/pdf");
        try (PdfDocument pdf = new PdfDocument(new PdfWriter(response.getOutputStream()))) {
            pdf.setDefaultPageSize(PageSize.A4.rotate());
            String fechaActual = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
            pdf.addEventHandler(PdfDocumentEvent.START_PAGE, new HeaderEventHandler("Pedidos de la Coleccion " + coleccion + " "+ "Del vendedor: " + vendedor,
                                                                                    fechaActual,"INFORME DE PEDIDOS"));
            pdf.addEventHandler(PdfDocumentEvent.END_PAGE, new FooterEventHandler());
            Document document = new Document(pdf);
            document.setMargins(80, 25, 50, 25);
            Table table = PdfGeneratorUtil.createMainTable(false);
            
            datos.forEach((ref, valores) -> {
                table.addCell(ref + " " + referencias.get(ref));
                for(int i = 1; i <= 10; i++) {
                    table.addCell(String.valueOf(valores.get("t"+i)));
                }
                table.addCell(String.valueOf(valores.get("total")));
                table.addCell("");
            });
            
            document.add(table);
            PdfGeneratorUtil.addTotalRow(document, calcularTotalesGenerales(datos));
        }
    }
    
// Informe por Cliente y Colección
    @GetMapping("/cliente-coleccion")
    public String formClienteColeccion(Model model) {
        model.addAttribute("clientes", directorioService.findByClte("True"));
        model.addAttribute("colecciones", coleccionService.findAll());
        return "informes/cliente-coleccion";
    }

    @PostMapping("/cliente-coleccion")
    public void generarInformeClienteColeccion(
            @RequestParam String cliente,
            @RequestParam String coleccion,
            HttpServletResponse response) throws IOException {

        List<Pedido> pedidos = pedidoService.findByClteAndColeccion(cliente, coleccion);
        Map<String, Map<String, Integer>> datos = procesarDatos(pedidos);

        generarPdf(response, "Pedidos de la Colección " + coleccion + " del Cliente: " + cliente, datos, false);
    }

    // Informe por Referencia y Colección
    @GetMapping("/referencia-coleccion")
    public String formReferenciaColeccion(Model model) {
        model.addAttribute("colecciones", coleccionService.findAll());
        model.addAttribute("referencias", productoService.findAll().stream()
        .collect(Collectors.groupingBy(
            Producto::getCole, // Agrupa por colección
            Collectors.mapping(
                producto -> new Object[]{producto.getRef(), producto.getDescref()},
                Collectors.toList() // Colección de referencias y descripciones
            )
        )));
        return "informes/referencia-coleccion";
    }

    @PostMapping("/referencia-coleccion")
    public void generarInformeReferenciaColeccion(
            @RequestParam String referencia,
            @RequestParam String coleccion,
            @RequestParam String descripcion,
            HttpServletResponse response) throws IOException {

        List<Pedido> pedidos = pedidoService.findByRefAndColeccion(referencia, coleccion);

        Map<String, Map<String, Integer>> datos = new TreeMap<>(pedidos.stream()
                .collect(Collectors.groupingBy(
                        Pedido::getClte,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                lista -> lista.stream()
                                        .map(this::extraerTallas)
                                        .reduce(new HashMap<>(), this::combinarTallas)))));

        generarPdf(response, "Pedidos de la Referencia " + referencia + ": "+ descripcion +
        " -> Coleccion: " + coleccion, datos, true);
    }
    // Informe por Colección
    @GetMapping("/coleccion")
    public String formColeccion(Model model) {
        model.addAttribute("colecciones", coleccionService.findAll());
        return "informes/coleccion";
    }

    @PostMapping("/coleccion")
    public void generarInformeColeccion(
            @RequestParam String coleccion,
            HttpServletResponse response) throws IOException {

        List<Pedido> pedidos = pedidoService.findByColeccion(coleccion);
        Map<String, Map<String, Integer>> datos = procesarDatos(pedidos);

        generarPdf(response, "Pedidos de la Colección: " + coleccion, datos, false);
    }

    private void generarPdf(HttpServletResponse response, String titulo, Map<String, Map<String, Integer>> datos, boolean mostrarCliente) throws IOException {
        response.setContentType("application/pdf");
        try (PdfDocument pdf = new PdfDocument(new PdfWriter(response.getOutputStream()))) {
            pdf.setDefaultPageSize(PageSize.A4.rotate());
            String fechaActual = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
            pdf.addEventHandler(PdfDocumentEvent.START_PAGE, new HeaderEventHandler(titulo, fechaActual, "INFORME DE PEDIDOS"));
            pdf.addEventHandler(PdfDocumentEvent.END_PAGE, new FooterEventHandler());
            Document document = new Document(pdf);
            document.setMargins(80, 25, 50, 25);
            Table table = PdfGeneratorUtil.createMainTable(mostrarCliente);

            Map<String,String> referencias = productoService.findAll().stream()
            .collect(Collectors.toMap(
                Producto::getRef, // Clave: codCole
                Producto::getDescref, // Valor: desCole
                (existing, replacement) -> replacement, // Resolver duplicados
                HashMap::new // Crear un HashMap
            ));
            datos.forEach((key, valores) -> {
                if (mostrarCliente) {
                table.addCell(key);
                    
                }else {
                
                table.addCell(key + "-" +referencias.get(key));
                }
                for (int i = 1; i <= 10; i++) {
                    table.addCell(String.valueOf(valores.get("t" + i)));
                }
                table.addCell(String.valueOf(valores.get("total")));
                table.addCell(" ");
            });

            document.add(table);
            PdfGeneratorUtil.addTotalRow(document, calcularTotalesGenerales(datos));
        }
    }


    private Map<String, Map<String, Integer>> procesarDatos(List<Pedido> pedidos) {
    // Procesar y agrupar los datos
    Map<String, Map<String, Integer>> agrupados = pedidos.stream()
        .collect(Collectors.groupingBy(
            Pedido::getRef, // Agrupar por referencia
            Collectors.collectingAndThen(
                Collectors.toList(),
                lista -> {
                    // Combina los mapas de tallas para la lista de pedidos
                    Map<String, Integer> acumulador = new HashMap<>();
                    lista.forEach(p -> combinarTallas(acumulador, extraerTallas(p)));
                    return acumulador;
                }
            )
        ));

        return agrupados.entrySet().stream()
        .sorted((entry1, entry2) -> Integer.compare(
            entry2.getValue().get("total"), // Cambia el orden (descendente)
            entry1.getValue().get("total")
        ))
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            (e1, e2) -> e1, // Maneja conflictos (no aplican aquí)
            LinkedHashMap::new // Asegura el orden del mapa resultante
        ));
    
}

    
    
    
    
    private Map<String, Integer> extraerTallas(Pedido p) {
        Map<String, Integer> tallas = new HashMap<>();
        tallas.put("t1", p.getT1());
        tallas.put("t2", p.getT2());
        tallas.put("t3", p.getT3());
        tallas.put("t4", p.getT4());
        tallas.put("t5", p.getT5());
        tallas.put("t6", p.getT6());
        tallas.put("t7", p.getT7());
        tallas.put("t8", p.getT8());
        tallas.put("t9", p.getT9());
        tallas.put("t10", p.getT10());
        tallas.put("total", p.getPrendref());
        return tallas;
    }
    
    private Map<String, Integer> combinarTallas(Map<String, Integer> existente, Map<String, Integer> nuevo) {
        nuevo.forEach((key, value) -> 
            existente.merge(key, value, Integer::sum) // Suma los valores de las claves comunes
        );
        return existente;
    }
    
    
    // Similar para otros 3 informes...

    
    
    private Map<String, Integer> calcularTotalesGenerales(Map<String, Map<String, Integer>> datos) {
        Map<String, Integer> totales = new HashMap<>();
        
        datos.values().forEach(valores -> {
            for(int i = 1; i <= 10; i++) {
                String key = "t" + i;
                totales.put(key, totales.getOrDefault(key, 0) + valores.get(key));
            }
        });
        
        return totales;
    }
}