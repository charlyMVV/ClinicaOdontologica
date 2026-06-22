package com.Clinica.SistemaClinicaBack.pdf;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

import java.awt.Color;

public class PdfCellFactory {

    private static final Color COLOR_PRINCIPAL = new Color(96, 125, 139);
    private static final Color GRIS_CLARO      = new Color(235, 235, 235);
    private static final Color GRIS_BORDE      = new Color(180, 180, 180);
    private static final Color BLANCO          = Color.WHITE;

    private PdfCellFactory() {}

    // ── Celdas individuales ──────────────────────────────────────────────────

    public static PdfPCell etiqueta(String texto) {
        Font font = new Font(Font.HELVETICA, 8, Font.BOLD, COLOR_PRINCIPAL);
        PdfPCell celda = new PdfPCell(new Phrase(texto, font));
        celda.setBackgroundColor(GRIS_CLARO);
        celda.setBorderColor(GRIS_BORDE);
        celda.setPadding(5);
        celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return celda;
    }

    public static PdfPCell valor(String texto) {
        Font font = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.BLACK);
        PdfPCell celda = new PdfPCell(new Phrase(sanitizar(texto), font));
        celda.setBorderColor(GRIS_BORDE);
        celda.setPadding(5);
        celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return celda;
    }

    public static PdfPCell encabezado(String texto) {
        Font font = new Font(Font.HELVETICA, 8, Font.BOLD, BLANCO);
        PdfPCell celda = new PdfPCell(new Phrase(texto, font));
        celda.setBackgroundColor(COLOR_PRINCIPAL);
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
        celda.setPadding(6);
        celda.setBorderColor(GRIS_BORDE);
        return celda;
    }

    public static PdfPCell texto(String contenido, int alineacion) {
        Font font = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.BLACK);
        PdfPCell celda = new PdfPCell(new Phrase(sanitizar(contenido), font));
        celda.setHorizontalAlignment(alineacion);
        celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
        celda.setPadding(5);
        celda.setBorderColor(GRIS_BORDE);
        return celda;
    }

    public static PdfPCell check(boolean valor, Font checkFont) {
        PdfPCell celda = new PdfPCell(new Phrase(valor ? "\u2713" : "X", checkFont));
        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
        celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
        celda.setPadding(5);
        celda.setBorderColor(GRIS_BORDE);
        return celda;
    }

    public static PdfPCell subtitulo(String texto, int colspan) {
        Font font = new Font(Font.HELVETICA, 8, Font.BOLD, BLANCO);
        PdfPCell celda = new PdfPCell(new Phrase(texto, font));
        celda.setColspan(colspan);
        celda.setBackgroundColor(COLOR_PRINCIPAL);
        celda.setHorizontalAlignment(Element.ALIGN_LEFT);
        celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
        celda.setPadding(5);
        celda.setBorderColor(GRIS_BORDE);
        return celda;
    }

    // ── Helpers de tabla ─────────────────────────────────────────────────────

    public static void agregarEncabezado(PdfPTable tabla, String texto) {
        tabla.addCell(encabezado(texto));
    }

    public static void agregarEtiqueta(PdfPTable tabla, String texto) {
        tabla.addCell(etiqueta(texto));
    }

    public static void agregarValor(PdfPTable tabla, String texto) {
        tabla.addCell(valor(texto));
    }

    public static void agregarTexto(PdfPTable tabla, String texto, int alineacion) {
        tabla.addCell(texto(texto, alineacion));
    }

    public static void agregarCheck(PdfPTable tabla, boolean valor, Font checkFont) {
        tabla.addCell(check(valor, checkFont));
    }

    public static void agregarSubtitulo(PdfPTable tabla, String texto, int colspan) {
        tabla.addCell(subtitulo(texto, colspan));
    }

    // ── Secciones de documento ───────────────────────────────────────────────

    public static void agregarTituloSeccion(Document document, String titulo) throws Exception {
        Font font = new Font(Font.HELVETICA, 10, Font.BOLD, BLANCO);
        PdfPTable tabla = new PdfPTable(1);
        tabla.setWidthPercentage(100);
        PdfPCell celda = new PdfPCell(new Phrase(titulo, font));
        celda.setBackgroundColor(COLOR_PRINCIPAL);
        celda.setPadding(6);
        celda.setBorder(Rectangle.NO_BORDER);
        tabla.addCell(celda);
        document.add(tabla);
    }

    public static void agregarCampoTextoLargo(Document document, String titulo, String valor) throws Exception {
        PdfPTable tabla = new PdfPTable(1);
        tabla.setWidthPercentage(100);
        tabla.setSpacingBefore(5f);
        tabla.setSpacingAfter(8f);

        PdfPCell encabezado = new PdfPCell(
                new Phrase(titulo, new Font(Font.HELVETICA, 8, Font.BOLD, BLANCO)));
        encabezado.setBackgroundColor(COLOR_PRINCIPAL);
        encabezado.setPadding(5);
        encabezado.setBorderColor(GRIS_BORDE);
        tabla.addCell(encabezado);

        PdfPCell contenido = new PdfPCell(
                new Phrase(sanitizar(valor), new Font(Font.HELVETICA, 9, Font.NORMAL)));
        contenido.setMinimumHeight(50f);
        contenido.setPadding(6);
        contenido.setBorderColor(GRIS_BORDE);
        tabla.addCell(contenido);

        document.add(tabla);
    }

    // ── Utilidades ───────────────────────────────────────────────────────────

    public static String sanitizar(String texto) {
        if (texto == null || texto.trim().isEmpty() || texto.equalsIgnoreCase("null")) {
            return "";
        }
        return texto;
    }

    public static boolean esSi(String respuesta) {
        return respuesta != null && respuesta.equalsIgnoreCase("Sí");
    }

    public static boolean esNo(String respuesta) {
        return respuesta != null && respuesta.equalsIgnoreCase("NO");
    }
}