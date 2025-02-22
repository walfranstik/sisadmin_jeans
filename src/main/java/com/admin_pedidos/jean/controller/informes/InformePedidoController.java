package com.admin_pedidos.jean.controller.informes;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.admin_pedidos.jean.entity.Pedido;
import com.admin_pedidos.jean.service.ColeccionService;
import com.admin_pedidos.jean.service.DirectorioService;
import com.admin_pedidos.jean.service.PedidoService;
import com.admin_pedidos.jean.service.ProductoService;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Tab;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/informe-pedido")
public class InformePedidoController {
     
    @Autowired
    PedidoService pedidoService;

    @Autowired
    DirectorioService directorioService;

    @Autowired
    ColeccionService coleccionService;

    @Autowired
    ProductoService productoService;
    
     // Métodos para mostrar formularios
     @GetMapping()
     public String formPedidos( @RequestParam(defaultValue = "") String keyword,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        Model model) {

        Page<Pedido> pedidosPage = pedidoService.searchPedidos(keyword, page, size);

        model.addAttribute("keyword", (keyword.isEmpty()? "" : keyword));
        model.addAttribute("pedidos", pedidosPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pedidosPage.getTotalPages());

         List<Pedido> pedidos = pedidoService.findAll();
    
        // Extrae el atributo numped, elimina duplicados y ordena
        Set<String> numpeds = pedidos.stream()
            .map(Pedido::getNumped)
            .collect(Collectors.toCollection(TreeSet::new));
        
        model.addAttribute("numped", numpeds);
        
         model.addAttribute("vendedores", directorioService.findByVddor("True"));
         model.addAttribute("colecciones", coleccionService.findAll());
         
         return "informes/informe-pedido";
     }

    @PostMapping()
    public void generarInformePedidos(
        @RequestParam String numped,HttpServletResponse response) 
        throws IOException {
        List<Pedido> pedidos = pedidoService.findByNumped(numped);
        Map<String, List<Map<String, Object>>> datos = procesarDatos(pedidos);
        generarPdf(response, "Numero de pedido: " + numped, datos,pedidos.get(0).getClte(),pedidos.get(0).getCol(),pedidos.get(0).getVddor(),
        pedidos.get(0).getTotalFormateado(),pedidos.get(0).getObsgen());
        
        }
    
    private void generarPdf (HttpServletResponse response, String titulo, Map<String, List<Map<String, Object>>> datos,String cliente,String coleccion,String vendedor,
     String precioTotal,String observacionesGen) throws IOException {
    response.setContentType("application/pdf");

    try (PdfDocument pdf = new PdfDocument(new PdfWriter(response.getOutputStream()))) {
        // Establecer tamaño de página
        pdf.setDefaultPageSize(PageSize.A4);
        
        // Formatear fecha para el header (según tu ejemplo)
        String fechaActual = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        
        // Agregar header (suponiendo que ya tienes implementado HeaderEventHandler)
        pdf.addEventHandler(PdfDocumentEvent.START_PAGE, new HeaderEventHandler(titulo, fechaActual, "FECHA"));
        
        // Crear el documento
        Document document = new Document(pdf);
        document.setMargins(90, 25, 35, 25);

        // Crear un placeholder para el total de páginas (dimensiones arbitrarias)
        PdfFormXObject placeholder = new PdfFormXObject(new Rectangle(0, 0, 30, 12));
        
        // Agregar el footer con el handler (ajusta yPosition y fontSize según necesites)
        pdf.addEventHandler(PdfDocumentEvent.END_PAGE, new FooterEventHandler(placeholder, 15, 10));
       
        LineSeparator line = new LineSeparator(new SolidLine(2));
        line.setMarginLeft(35);  // La línea inicia en x=300
        line.setMarginRight(35);  // La línea inicia en x=300
        document.add(line);

        // Agregar el texto "Cliente" y el valor de la variable cliente en una nueva línea
        Paragraph pCliente = new Paragraph("Cliente: " + cliente);
        pCliente.setMarginLeft(35);
        pCliente.setFontSize(11);
        pCliente.setBold();
        document.add(pCliente);

        // Crear un párrafo para "Coleccion" y "Vendedor" en la misma línea, separados por un tabulador
        Paragraph pColeccionVendedor = new Paragraph();
        pColeccionVendedor.add("Coleccion: " + coleccion);
        pColeccionVendedor.add(new Tab());
        pColeccionVendedor.add("Vendedor: " + vendedor);
        pColeccionVendedor.setMarginLeft(35);
        pColeccionVendedor.setFontSize(11);
        pColeccionVendedor.setBold();

        document.add(pColeccionVendedor);

        // Aquí agregas tu contenido (por ejemplo, una tabla)
        Table table = PdfGeneratorUtil.tablaInforme();

        datos.forEach((key, valoress) -> {

                if (valoress.size() == 1) {
                    Cell cell5 =  new Cell().add(new Paragraph(key)).setBorder(Border.NO_BORDER).setFontSize(10).setBold().setTextAlignment(TextAlignment.CENTER);
                    table.addCell(cell5);
                }
            for (Map<String,Object> valores : valoress) {
                if (valoress.size() > 1) {
                    Cell cell5 =  new Cell().add(new Paragraph(key)).setBorder(Border.NO_BORDER).setFontSize(10).setBold().setTextAlignment(TextAlignment.CENTER);
                    table.addCell(cell5);
                }
                for (int i = 1; i <= 10; i++) {
                    Cell cell = new Cell().add(new Paragraph(String.valueOf(valores.get("t" + i) != null ? valores.get("t" + i) : "0")))
                            .setTextAlignment(TextAlignment.CENTER)
                            .setVerticalAlignment(VerticalAlignment.MIDDLE)
                            .setFontSize(11)
                            .setBorder(Border.NO_BORDER)
                            .setBold();
                    table.addCell(cell);
                }
                Object totalValue = valores.get("total");
                Cell cell2 = new Cell().add(new Paragraph(totalValue != null ? String.valueOf(totalValue) : "N/A"))
                        .setTextAlignment(TextAlignment.CENTER)
                        .setVerticalAlignment(VerticalAlignment.MIDDLE)
                        .setFontSize(11)
                        .setBorder(Border.NO_BORDER)
                        .setBold();
                table.addCell(cell2);
                Object precio = valores.get("precio");
                Cell cell3 = new Cell().add(new Paragraph(precio != null ? "$ " +  String.valueOf(precio) : "N/A"))
                        .setTextAlignment(TextAlignment.CENTER)
                        .setVerticalAlignment(VerticalAlignment.MIDDLE)
                        .setFontSize(11)
                        .setBorder(Border.NO_BORDER)
                        .setBold();
                table.addCell(cell3);

                Object obs = valores.get("obs");
                Cell cell4 = new Cell().add(new Paragraph(obs != null ? String.valueOf(obs) : "N/A"))
                        .setTextAlignment(TextAlignment.CENTER)
                        .setVerticalAlignment(VerticalAlignment.MIDDLE)
                        .setFontSize(11)
                        .setBorder(Border.NO_BORDER)
                        .setBold();
                table.addCell(cell4);
            }
            
        });
        // Agrega el contenido, la tabla, etc.
        document.add(table);

        PdfGeneratorUtil.tablaTotal(document, calcularTotalesGenerales(datos),precioTotal,observacionesGen);

        // --- Actualizar el placeholder con el total de páginas ---
        // En este punto ya se han agregado todas las páginas, pero aún no se ha cerrado el documento
        int totalPages = pdf.getNumberOfPages();
        PdfCanvas canvasPlaceholder = new PdfCanvas(placeholder, pdf);
        canvasPlaceholder.beginText();
        canvasPlaceholder.setFontAndSize(PdfFontFactory.createFont(StandardFonts.HELVETICA), 10);
        canvasPlaceholder.moveText(0, 5);
        canvasPlaceholder.showText(String.valueOf(totalPages));
        canvasPlaceholder.endText();
        canvasPlaceholder.release();

        // Ahora cerrar el documento (lo que también cierra el PdfDocument)
        document.close();
    }
}

// Método principal que agrupa los pedidos por referencia sin sumar sus datos
private Map<String, List<Map<String, Object>>> procesarDatos(List<Pedido> pedidos) {
    Map<String, List<Map<String, Object>>> agrupados = pedidos.stream()
        .collect(Collectors.groupingBy(
            Pedido::getRef, // Agrupar por referencia
            Collectors.mapping(
                this::extraerDatos, // Extrae los datos de cada pedido en un mapa
                Collectors.toList()
            )
        ));

    // Ordenamos el mapa resultante según el valor máximo de "total" en cada lista (orden descendente)
    return agrupados.entrySet().stream()
        .sorted((entry1, entry2) -> {
            int max1 = entry1.getValue().stream().mapToInt(m -> (int) m.get("total")).max().orElse(0);
            int max2 = entry2.getValue().stream().mapToInt(m -> (int) m.get("total")).max().orElse(0);
            return Integer.compare(max2, max1);
        })
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            (e1, e2) -> e1, // En caso de conflicto no ocurre ya que se preserva la lista
            LinkedHashMap::new // Mantiene el orden
        ));
}

// Método que extrae los datos de un pedido en un mapa (ahora con tipo Object para permitir distintos tipos de datos)
private Map<String, Object> extraerDatos(Pedido p) {
    Map<String, Object> datos = new HashMap<>();
    datos.put("t1", p.getT1());
    datos.put("t2", p.getT2());
    datos.put("t3", p.getT3());
    datos.put("t4", p.getT4());
    datos.put("t5", p.getT5());
    datos.put("t6", p.getT6());
    datos.put("t7", p.getT7());
    datos.put("t8", p.getT8());
    datos.put("t9", p.getT9());
    datos.put("t10", p.getT10());
    datos.put("total", p.getPrendref());
    datos.put("precio", (int) p.getVuniref());
    datos.put("obs", p.getObsref());
    return datos;
}

private Map<String, Integer> calcularTotalesGenerales(Map<String, List<Map<String, Object>>> datos) {
    Map<String, Integer> totales = new HashMap<>();
    
    datos.values().forEach(lista -> {
        for (Map<String, Object> map : lista) {
            for (int i = 1; i <= 10; i++) {
                String key = "t" + i;
                totales.put(key, totales.getOrDefault(key, 0) + (Integer) map.get(key));
            }
        }
    });
    
    return totales;
}


}
