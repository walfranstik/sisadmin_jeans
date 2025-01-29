package com.admin_pedidos.jean.controller.informes;

import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;

import java.io.IOException;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.properties.TextAlignment;

public class HeaderEventHandler implements IEventHandler {
    private final String title; // Texto centrado (14pt)
    private final String subtitle; // Texto alineado a la derecha (10pt)
    private final String topRightText; // Texto alineado a la derecha (12pt)

    public HeaderEventHandler(String title, String subtitle, String topRightText) {
        this.title = title;
        this.subtitle = subtitle;
        this.topRightText = topRightText;
    }

    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfDocument pdfDoc = docEvent.getDocument();
        PdfPage page = docEvent.getPage();
        PdfCanvas pdfCanvas = new PdfCanvas(page);

        try {
            // Crear fuentes
            PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
            PdfFont boldFont = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);

            // Obtener tamaño de la página
            float pageWidth = pdfDoc.getDefaultPageSize().getWidth();
            float pageHeight = pdfDoc.getDefaultPageSize().getHeight();

            // Crear un Canvas a partir del PdfCanvas
            Canvas canvas = new Canvas(pdfCanvas, pdfDoc.getDefaultPageSize());

            // Texto superior derecha (12pt)
            canvas.setFont(font).setFontSize(12)
                .showTextAligned(topRightText, pageWidth - 50, pageHeight - 20, TextAlignment.RIGHT);

            // Subtítulo inferior derecha (10pt)
            canvas.setFont(font).setFontSize(10)
                .showTextAligned(subtitle, pageWidth - 50, pageHeight - 35, TextAlignment.RIGHT);

            // Título centrado (14pt, negrita)
            canvas.setFont(boldFont).setFontSize(14)
                .showTextAligned(title, pageWidth / 2, pageHeight - 50, TextAlignment.CENTER);

            canvas.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            pdfCanvas.release();
        }
    }
}