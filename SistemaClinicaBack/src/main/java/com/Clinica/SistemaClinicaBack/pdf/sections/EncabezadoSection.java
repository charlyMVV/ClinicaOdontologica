package com.Clinica.SistemaClinicaBack.pdf.sections;

import com.Clinica.SistemaClinicaBack.entity.HistoriaClinica;
import com.Clinica.SistemaClinicaBack.pdf.PdfCellFactory;
import com.Clinica.SistemaClinicaBack.pdf.PdfSeccion;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.awt.Color;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@Order(1)
public class EncabezadoSection implements PdfSeccion {

    private static final Color COLOR_PRINCIPAL = new Color(96, 125, 139);
    private static final Color BLANCO = Color.WHITE;

    @Override
    public void agregar(Document document, HistoriaClinica hc) throws Exception {

        Font titulo      = new Font(Font.HELVETICA, 12, Font.BOLD, COLOR_PRINCIPAL);
        Font texto       = new Font(Font.HELVETICA, 9,  Font.NORMAL);
        Font tituloBlanco = new Font(Font.HELVETICA, 11, Font.BOLD, BLANCO);

        // Logo + nombre institución
        PdfPTable encabezado = new PdfPTable(2);
        encabezado.setWidthPercentage(100);
        encabezado.setWidths(new float[]{18, 82});

        PdfPCell celdaLogo = new PdfPCell();
        celdaLogo.setBorder(Rectangle.NO_BORDER);
        celdaLogo.setHorizontalAlignment(Element.ALIGN_CENTER);
        celdaLogo.setVerticalAlignment(Element.ALIGN_MIDDLE);
        try {
            Image logo = Image.getInstance(getClass().getResource("/static/img/logo.jpg"));
            logo.scaleToFit(70, 70);
            celdaLogo.addElement(logo);
        } catch (Exception e) {
            celdaLogo.addElement(new Paragraph("LOGO", texto));
        }

        PdfPCell celdaTexto = new PdfPCell();
        celdaTexto.setBorder(Rectangle.NO_BORDER);
        celdaTexto.setVerticalAlignment(Element.ALIGN_MIDDLE);

        Paragraph iesit = new Paragraph("INSTITUTO DE ESTUDIOS SUPERIORES DEL ISTMO DE TEHUANTEPEC", titulo);
        iesit.setAlignment(Element.ALIGN_CENTER);
        Paragraph escuela = new Paragraph("ESCUELA DE ODONTOLOGÍA", titulo);
        escuela.setAlignment(Element.ALIGN_CENTER);

        celdaTexto.addElement(iesit);
        celdaTexto.addElement(escuela);
        encabezado.addCell(celdaLogo);
        encabezado.addCell(celdaTexto);
        document.add(encabezado);

        // Línea separadora
        Paragraph linea = new Paragraph(" ");
        linea.setSpacingAfter(5);
        document.add(linea);

        // Fecha
        Font fechaFont = new Font(Font.HELVETICA, 9, Font.NORMAL);
        Paragraph fecha = new Paragraph(
                "Fecha: " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                fechaFont);
        fecha.setAlignment(Element.ALIGN_RIGHT);
        document.add(fecha);
        document.add(new Paragraph(" "));

        // Alumno / Matrícula
        PdfPTable filaAlumno = new PdfPTable(4);
        filaAlumno.setWidthPercentage(100);
        filaAlumno.setWidths(new float[]{15, 60, 10, 15});
        PdfCellFactory.agregarEtiqueta(filaAlumno, "Alumno");
        PdfCellFactory.agregarValor(filaAlumno, PdfCellFactory.sanitizar(hc.getUsuario().getNombreUsuario()));
        PdfCellFactory.agregarEtiqueta(filaAlumno, "Matrícula");
        PdfCellFactory.agregarValor(filaAlumno, PdfCellFactory.sanitizar(hc.getUsuario().getMatricula()));
        document.add(filaAlumno);

        // Titular / Clínica
        PdfPTable filaTitular = new PdfPTable(4);
        filaTitular.setWidthPercentage(100);
        filaTitular.setWidths(new float[]{15, 60, 10, 15});
        PdfCellFactory.agregarEtiqueta(filaTitular, "Titular");
        PdfCellFactory.agregarValor(filaTitular, "Pendiente");
        PdfCellFactory.agregarEtiqueta(filaTitular, "Clínica");
        PdfCellFactory.agregarValor(filaTitular, "Clínica Integral");
        document.add(filaTitular);

        document.add(new Paragraph(" "));

        // Título "HISTORIA CLÍNICA"
        PdfPTable tituloTabla = new PdfPTable(1);
        tituloTabla.setWidthPercentage(100);
        PdfPCell celdaTitulo = new PdfPCell(new Phrase("HISTORIA CLÍNICA", tituloBlanco));
        celdaTitulo.setBackgroundColor(COLOR_PRINCIPAL);
        celdaTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
        celdaTitulo.setPadding(7);
        celdaTitulo.setBorder(Rectangle.NO_BORDER);
        tituloTabla.addCell(celdaTitulo);
        document.add(tituloTabla);

        document.add(new Paragraph(" "));
    }
}