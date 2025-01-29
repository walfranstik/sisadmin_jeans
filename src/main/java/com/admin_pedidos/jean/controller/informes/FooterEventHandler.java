package com.admin_pedidos.jean.controller.informes;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.kernel.font.PdfFontFactory;

import java.io.IOException;

public class FooterEventHandler implements IEventHandler {
    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfDocument pdfDoc = docEvent.getDocument();
        PdfPage page = docEvent.getPage();
        int pageNumber = pdfDoc.getPageNumber(page);
        int totalPages = pdfDoc.getNumberOfPages();

        PdfCanvas pdfCanvas = new PdfCanvas(page);
        Canvas canvas = new Canvas(pdfCanvas, pdfDoc.getDefaultPageSize());

        try {
            canvas.setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA));
            canvas.setFontSize(10);

            // Pie de página: centrado
            String footerText = String.format("Página %d de %d", pageNumber, totalPages);
            canvas.showTextAligned(footerText, 100, 30, TextAlignment.CENTER);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Liberar canvas
            canvas.close();
        }
    }
}
