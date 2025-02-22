package com.admin_pedidos.jean.controller.informes;

import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.Property;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.DoubleBorder;
import com.itextpdf.layout.borders.SolidBorder;

import java.util.Map;

public class PdfGeneratorUtil {
    
    private static final String[] TALLA_LABELS = {"28/6", "30/8", "32/10", "34/12", "36/14", "38/16", "40/18", "42/20", "44/22", "46/24"};
    
    public static Table createMainTable(boolean includeClient) {
        Table table = new Table((includeClient) ? 12 : 13);

       // Establecer un borde doble en la parte superior e inferior
        DoubleBorder doubleBorder = new DoubleBorder(new DeviceRgb(0, 0, 0), 3, 4);
        table.setProperty(Property.BORDER_TOP, doubleBorder);
        table.setProperty(Property.BORDER_BOTTOM, doubleBorder);
        
        if(includeClient) table.addHeaderCell("CLIENTE").setFontSize(11).setBold();
        else {
            table.addHeaderCell("REF").setFontSize(10).setBold();
            table.addHeaderCell("DESCRIPCION").setFontSize(10).setBold();
    
    }
        for(String talla : TALLA_LABELS) {
            Cell cell = new Cell().add(new Paragraph("TALLA\n" + talla));
            cell.setTextAlignment(TextAlignment.CENTER); // Align text
            cell.setVerticalAlignment(VerticalAlignment.MIDDLE);
            cell.setWidth(33);
            cell.setFontSize(10);
            table.addHeaderCell(cell);        
        }

        Cell cell2 = new Cell().add(new Paragraph("TOTAL"));
            cell2.setTextAlignment(TextAlignment.CENTER); // Align text
            cell2.setVerticalAlignment(VerticalAlignment.MIDDLE);
            cell2.setFontSize(10);
            cell2.setWidth(45);

        table.addHeaderCell(cell2);
        


        return table;
    }
    
    public static void addTotalRow(Document document, Map<String, Integer> totales) {
        
        String[] TALLA_LABELST = {"28/6", "30/8", "32/10", "34/12", "36/14", "38/16", "40/18", "42/20", "44/22", "46/24"};

        Table totalTable = new Table(12);
        totalTable.setMarginTop(20);
        totalTable.setMarginLeft(30);
        totalTable.setFontSize(12);
        totalTable.setBold();
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

        
    public static Table tablaInforme() {
       Table table = new Table(14);
            table.setHorizontalAlignment(HorizontalAlignment.CENTER); // Centrar la tabla
            Cell cell5 = new Cell().add(new Paragraph("REF"));
            cell5.setTextAlignment(TextAlignment.CENTER);
            cell5.setVerticalAlignment(VerticalAlignment.MIDDLE);
            cell5.setPadding(15);
            cell5.setFontSize(10);
            cell5.setBold();
            cell5.setBorder(Border.NO_BORDER);
            cell5.setBorderBottom(new SolidBorder(2f)); // Línea doble inferior
            table.addHeaderCell(cell5);

            for (String talla : TALLA_LABELS) {
                Cell cell = new Cell().add(new Paragraph(talla));
                cell.setTextAlignment(TextAlignment.CENTER);
                cell.setVerticalAlignment(VerticalAlignment.MIDDLE);
                cell.setFontSize(10);
                cell.setBorder(Border.NO_BORDER);
                cell.setBorderBottom(new SolidBorder(2f)); // Línea doble inferior
                cell.setBold();

                table.addHeaderCell(cell);
            }

            Cell cell2 = new Cell().add(new Paragraph("TOTAL"));
            cell2.setTextAlignment(TextAlignment.CENTER);
            cell2.setVerticalAlignment(VerticalAlignment.MIDDLE);
            cell2.setFontSize(10);
            cell2.setBorder(Border.NO_BORDER);
            cell2.setBold();

            cell2.setBorderBottom(new SolidBorder(2f)); // Línea doble inferior
            table.addHeaderCell(cell2);

            Cell cell3 = new Cell().add(new Paragraph("PRECIO"));
            cell3.setTextAlignment(TextAlignment.CENTER);
            cell3.setVerticalAlignment(VerticalAlignment.MIDDLE);
            cell3.setFontSize(10);
            cell3.setBorder(Border.NO_BORDER);
            cell3.setBold();

            cell3.setBorderBottom(new SolidBorder(2f)); // Línea doble inferior
            table.addHeaderCell(cell3);

            Cell cell4 = new Cell().add(new Paragraph("Observación"));
            cell4.setTextAlignment(TextAlignment.CENTER);
            cell4.setVerticalAlignment(VerticalAlignment.MIDDLE);
            cell4.setFontSize(10);
            cell4.setPadding(5);
            cell4.setBold();

            cell4.setBorder(Border.NO_BORDER);
            cell4.setBorderBottom(new SolidBorder(2f)); // Línea doble inferior
            table.addHeaderCell(cell4);

        return table;
    }
    
    public static void tablaTotal(Document document, Map<String, Integer> totales,String precioTotal,String observacionesGen) {
        LineSeparator line = new LineSeparator(new SolidLine(2));
        line.setMarginLeft(35);  // La línea inicia en x=300
        line.setMarginRight(35);  // La línea inicia en x=300
        document.add(line);
         // Agregar el texto "OBSERVACIONES" y el valor de la variable cliente en una nueva línea
         Paragraph obsGen = new Paragraph("OBSERVACIONES: " + observacionesGen);
         obsGen.setMarginLeft(35);
         obsGen.setFontSize(11);
         obsGen.setBold();
         document.add(obsGen);

        String[] TALLA_LABELST = {"28/6", "30/8", "32/10", "34/12", "36/14", "38/16", "40/18", "42/20", "44/22", "46/24"};

        Table totalTable = new Table(12);
        totalTable.setMarginTop(20);
        totalTable.setMarginLeft(5);
        totalTable.setFontSize(12);
        totalTable.setBold();
        totalTable.setHorizontalAlignment(HorizontalAlignment.CENTER); // Centrar la tabla

        
        for(String talla : TALLA_LABELST) {
            Cell cell = new Cell().add(new Paragraph( talla));
            cell.setPadding(3); 
            totalTable.addHeaderCell(cell).setFontSize(10).setBold();
            
        }
        Cell cell = new Cell().add(new Paragraph("Total \n Prendas"));
        cell.setPadding(3); 
        cell.setTextAlignment(TextAlignment.CENTER);
        totalTable.addHeaderCell(cell).setFontSize(10).setBold();

        Cell cellTotal = new Cell().add(new Paragraph( "TOTAL $")).setFontSize(15).setBold().setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE);

        totalTable.addHeaderCell(cellTotal);

        int granTotal = 0;
        for(int i = 1; i <= 10; i++) {
            int total = totales.getOrDefault("t"+i, 0);
            Cell cellt = new Cell().add(new Paragraph(String.valueOf(total)));
                    
            totalTable.addCell(cellt).setFontSize(10).setTextAlignment(TextAlignment.CENTER).setBold();
            granTotal += total;
        }

        Cell cellgt = new Cell().add(new Paragraph(String.valueOf(granTotal)));
        totalTable.addCell(cellgt).setFontSize(10).setTextAlignment(TextAlignment.CENTER).setBold();

        Cell cellPT = new Cell().add(new Paragraph(String.valueOf(precioTotal))).setFontSize(13).setTextAlignment(TextAlignment.CENTER).setBold();
        totalTable.addCell(cellPT);
        document.add(totalTable);
       
    }

}
