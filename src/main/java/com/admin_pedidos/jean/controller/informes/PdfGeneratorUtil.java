package com.admin_pedidos.jean.controller.informes;

import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.Document;
import java.util.Map;

public class PdfGeneratorUtil {
    
    private static final String[] TALLA_LABELS = {"28/6", "30/8", "32/10", "34/12", "36/14", "38/16", "40/18", "42/20", "44/22", "46/24"};
    
    public static Table createMainTable(boolean includeClient) {
        Table table = new Table(includeClient ? 13 : 13);
        
        if(includeClient) table.addHeaderCell("Cliente");
        else table.addHeaderCell("Referencia");
        for(String talla : TALLA_LABELS) {
            table.addHeaderCell("Talla\n"+talla);
        }
        table.addHeaderCell("Total");
        Cell cell = new Cell().add(new Paragraph(String.valueOf("Observaciones")));
        cell.setPadding(10); // Agregar espacio interno a la celda
        table.addHeaderCell(cell);


        return table;
    }
    
    public static void addTotalRow(Document document, Map<String, Integer> totales) {
        
        String[] TALLA_LABELST = {"28/6", "30/8", "32/10", "34/12", "36/14", "38/16", "40/18", "42/20", "44/22", "46/24"};

        Table totalTable = new Table(12);
        totalTable.setMarginTop(20);
        totalTable.setMarginLeft(25);
        totalTable.setFontSize(13);
        totalTable.addHeaderCell("Totales Generales");
        for(String talla : TALLA_LABELST) {
            Cell cell = new Cell().add(new Paragraph("Talla\n"+ talla));
            cell.setPadding(10); 
            totalTable.addHeaderCell(cell);
            
        }
        Cell cell = new Cell().add(new Paragraph("Total"));
        cell.setPadding(10); 
        totalTable.addHeaderCell(cell);


        totalTable.addCell("");

        int granTotal = 0;
        for(int i = 1; i <= 10; i++) {
            int total = totales.getOrDefault("t"+i, 0);
            Cell cellt = new Cell().add(new Paragraph(String.valueOf(total)));
            cellt.setPadding(10); // Agregar espacio interno a la celda
            totalTable.addCell(cellt);
            granTotal += total;
        }
        Cell cellgt = new Cell().add(new Paragraph(String.valueOf(granTotal)));
        cellgt.setPadding(10); // Agregar espacio interno a la celda
        totalTable.addCell(cellgt);
        document.add(totalTable);
    }
}
