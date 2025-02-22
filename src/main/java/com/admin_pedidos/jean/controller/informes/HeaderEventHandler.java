package com.admin_pedidos.jean.controller.informes;

import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.properties.TextAlignment;

import java.io.InputStream;
import java.io.IOException;

public class HeaderEventHandler implements IEventHandler {
    private final String title;
    private final String subtitle;
    private final String topRightText;

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
            PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
            PdfFont boldFont = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);

            float pageWidth = pdfDoc.getDefaultPageSize().getWidth();
            float pageHeight = pdfDoc.getDefaultPageSize().getHeight();
            Canvas canvas = new Canvas(pdfCanvas, pdfDoc.getDefaultPageSize());

            // Cargar imágenes correctamente desde el classpath
            Image image1 = loadImage("static/img/shock.png", 120, 65, 30, pageHeight - 65);
            Image image2 = loadImage("static/img/rolling.png", 120, 65, pageWidth - 120, pageHeight - 65);

            // Agregar imágenes al PDF
            if (image1 != null) canvas.add(image1);
            if (image2 != null) canvas.add(image2);

            // Texto superior derecha (12pt)
            canvas.setFont(font).setFontSize(12)
                .showTextAligned(topRightText, pageWidth - 120, pageHeight - 25, TextAlignment.RIGHT);

            // Subtítulo inferior derecha (10pt)
            canvas.setFont(font).setFontSize(10)
                .showTextAligned(subtitle, pageWidth - 120, pageHeight - 40, TextAlignment.RIGHT);

            // Título centrado (14pt, negrita)
            canvas.setFont(boldFont).setFontSize(13)
                .showTextAligned(title, pageWidth / 2, pageHeight - 80, TextAlignment.CENTER);

            // Datos empresariales
            canvas.setFont(font).setFontSize(10)
                .showTextAligned("SHOCK ROLLING CONFECCION\n" +
                                 "CELL: 315 4270560 - 5976010\n" +
                                 "MZ G5 LOTE 19 PRIMERA ETAPA ATALAYA", 
                                 220, pageHeight - 45, TextAlignment.CENTER);

            canvas.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            pdfCanvas.release();
        }
    }

    // Método para cargar imágenes desde el classpath
    private Image loadImage(String resourcePath, float width, float height, float x, float y) {
        try (InputStream imageStream = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            if (imageStream == null) {
                System.err.println("No se encontró la imagen: " + resourcePath);
                return null;
            }
            byte[] imageBytes = imageStream.readAllBytes();
            ImageData imageData = ImageDataFactory.create(imageBytes);
            Image image = new Image(imageData).scaleToFit(width, height).setFixedPosition(x, y);
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
