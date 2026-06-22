package com.Clinica.SistemaClinicaBack.pdf;

import com.lowagie.text.Element;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

/**
 * Builder fluido para construir filas de tablas PDF de forma consistente.
 * Uso:
 *   PdfTableBuilder.of(tabla)
 *       .subtitulo("Grupo sanguíneo", 4)
 *       .fila("Grupo", paciente.getGrupo(), "Factor RH", paciente.getRh())
 *       .filaCompleta("Observaciones", paciente.getObs());
 */
public class PdfTableBuilder {

    private final PdfPTable tabla;

    private PdfTableBuilder(PdfPTable tabla) {
        this.tabla = tabla;
    }

    public static PdfTableBuilder of(PdfPTable tabla) {
        return new PdfTableBuilder(tabla);
    }

    /** Fila con dos pares etiqueta–valor (4 columnas). */
    public PdfTableBuilder fila(String campo1, String valor1, String campo2, String valor2) {
        tabla.addCell(PdfCellFactory.etiqueta(campo1));
        tabla.addCell(PdfCellFactory.valor(valor1));
        tabla.addCell(PdfCellFactory.etiqueta(campo2));
        tabla.addCell(PdfCellFactory.valor(valor2));
        return this;
    }

    /** Fila con un único campo que ocupa las 3 columnas restantes (colspan=3). */
    public PdfTableBuilder filaCompleta(String campo, String valor) {
        tabla.addCell(PdfCellFactory.etiqueta(campo));
        PdfPCell celda = PdfCellFactory.valor(valor);
        celda.setColspan(3);
        tabla.addCell(celda);
        return this;
    }

    /** Fila de dos checkboxes con sus etiquetas (4 columnas). */
    public PdfTableBuilder filaCheck(String campo1, boolean valor1,
                                     String campo2, boolean valor2,
                                     com.lowagie.text.Font checkFont) {
        tabla.addCell(PdfCellFactory.etiqueta(campo1));
        tabla.addCell(PdfCellFactory.check(valor1, checkFont));
        tabla.addCell(PdfCellFactory.etiqueta(campo2));
        tabla.addCell(PdfCellFactory.check(valor2, checkFont));
        return this;
    }

    /** Subtítulo que abarca todo el ancho (colspan configurable). */
    public PdfTableBuilder subtitulo(String texto, int colspan) {
        tabla.addCell(PdfCellFactory.subtitulo(texto, colspan));
        return this;
    }

    /** Fila tejido blando: etiqueta + valor en 2 columnas. */
    public PdfTableBuilder filaTejido(String campo, String valor) {
        tabla.addCell(PdfCellFactory.etiqueta(campo));
        tabla.addCell(PdfCellFactory.valor(valor));
        return this;
    }

    /** Fila de encabezados de tabla (texto centrado, fondo principal). */
    public PdfTableBuilder encabezados(String... columnas) {
        for (String col : columnas) {
            tabla.addCell(PdfCellFactory.encabezado(col));
        }
        return this;
    }
}