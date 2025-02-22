package com.admin_pedidos.jean.controller.informes;

import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
public class FooterEventHandler implements IEventHandler {

    private PdfFormXObject placeholder;
    private float fontSize;
    private float yPosition;

    /**
     * @param placeholder El objeto que actuará como placeholder para el total de páginas.
     * @param yPosition   La posición vertical (en puntos) donde se ubicará el footer.
     * @param fontSize    Tamaño de fuente para el texto del footer.
     */
    public FooterEventHandler(PdfFormXObject placeholder, float yPosition, float fontSize) {
        this.placeholder = placeholder;
        this.yPosition = yPosition;
        this.fontSize = fontSize;
    }

    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfDocument pdf = docEvent.getDocument();
        PdfPage page = docEvent.getPage();
        int pageNumber = pdf.getPageNumber(page);
        Rectangle pageSize = page.getPageSize();

        // Crear un canvas para escribir en la página (usamos el constructor que acepta PdfCanvas y Rectangle)
        PdfCanvas pdfCanvas = new PdfCanvas(page.newContentStreamAfter(), page.getResources(), pdf);
        Canvas canvas = new Canvas(pdfCanvas, pageSize);

        // Construir el texto "Página X de "
        Paragraph p = new Paragraph(String.format("Página %d de ", pageNumber))
                .setFontSize(fontSize);
        // Posicionar el texto centrado en la parte inferior
        float xPosition = pageSize.getWidth() - 200;
        canvas.showTextAligned(p, xPosition, pageSize.getBottom() + yPosition, TextAlignment.RIGHT);

        // Agregar el placeholder (convertido en imagen) justo a la derecha del texto anterior
        Image totalPageImage = new Image(placeholder)
                .setFixedPosition(xPosition + 2, pageSize.getBottom() + yPosition - (fontSize * 0.3f));
        canvas.add(totalPageImage);
        canvas.close();
    }
}