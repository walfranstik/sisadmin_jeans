package com.admin_pedidos.jean.controller.informes;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.admin_pedidos.jean.dto.RotuloDto;
import com.admin_pedidos.jean.entity.Directorio;
import com.admin_pedidos.jean.service.DirectorioService;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.io.font.constants.StandardFonts;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/rotulos")

public class RotuloController {
 @Autowired
    private DirectorioService directorioService;
    // Mostrar Formulario
        @GetMapping
        public String mostrarForm(Model model) {
            model.addAttribute("directorio", new RotuloDto());
            model.addAttribute("directorios", directorioService.findAll());
            return "informes/rotulo";
        }

        // Obtener datos para autocompletado
        @GetMapping("/obtener-directorio")
    @ResponseBody
    public ResponseEntity<Directorio> obtenerDatos(
            @RequestParam(required = false) String nit,
            @RequestParam(required = false) String nombre) {

        Directorio directorio = null;
        if (nit != null && !nit.isBlank()) {
            directorio = directorioService.findById(nit).orElse(null);
        } else if (nombre != null && !nombre.isBlank()) {
            directorio = directorioService.findByNomdir(nombre);
        }

        return directorio != null 
            ? ResponseEntity.ok(directorio) 
            : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping("/generar-rotulo")
    public void generarPDF(@ModelAttribute RotuloDto directorio, HttpServletResponse response) throws IOException {
        // Configurar respuesta
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=rotulo.pdf");
        
        // Configuración del documento
        try (PdfDocument pdf = new PdfDocument(new PdfWriter(response.getOutputStream()))) {
            
            
            Document document = new Document(pdf);
            
            // Fuente del documento
            PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
            document.setFont(font);
            document.setBottomMargin(0);
            document.setTopMargin(0);
            document.setLeftMargin(0);
            document.setRightMargin(0);

            
            // Tamaño A4 en puntos (595 x 842 puntos)
            float pageHeight = PageSize.A4.getHeight();
            
            // Conversión cm a puntos (1 cm = 28.35 puntos)
            float cm = 28.35f;
            
           
            
            // Agregar elementos con posicionamiento absoluto
            document.setFontSize(15);
          
            addText(document, "NIT:  " + directorio.getNitdir(), 13.6f*cm, pageHeight - 4*cm, 200);  // NIT
            
            document.setFontSize(16);
            
            addText(document, directorio.getNomdir(), 5.8f*cm, pageHeight - 5.2f*cm, 420);  // Nombre

            document.setFontSize(17);
            
            addText(document, directorio.getDiredir(), 6*cm, pageHeight - 6.5f*cm, 400); // Dirección

            document.setFontSize(15);

            addText(document, directorio.getAlmacen(), 6*cm, pageHeight - 7.1f*cm, 300); // Almacen

            document.setFontSize(17);

            addText(document, directorio.getPreteldir() + " - " + directorio.getTeldir(), 
                    6*cm, pageHeight - 8.4f*cm, 200); // Teléfono

            addText(document, "Cantidad:  " + String.valueOf(directorio.getCantidad()),
             13*cm, pageHeight - 8.4f*cm, 200); // cantidad

            addText(document, directorio.getCiudir() + "-" + directorio.getDptodir() ,
             6*cm, pageHeight - 10.3f*cm, 450);  // Ciudad-Departamento
            
            
            // Cerrar documento
            document.close();
        }
    }

    private void addText(Document document, String text, float x, float y, float width) {
        Paragraph paragraph = new Paragraph(text);
        paragraph.setFixedPosition(x, y, width);
        document.add(paragraph);
    }

}
