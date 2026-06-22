package com.Clinica.SistemaClinicaBack.pdf.sections;

import com.Clinica.SistemaClinicaBack.entity.HistoriaClinica;
import com.Clinica.SistemaClinicaBack.entity.Tutor;
import com.Clinica.SistemaClinicaBack.pdf.PdfCellFactory;
import com.Clinica.SistemaClinicaBack.pdf.PdfSeccion;
import com.Clinica.SistemaClinicaBack.pdf.PdfTableBuilder;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(5)
public class TutorSection implements PdfSeccion {

    @Override
    public void agregar(Document document, HistoriaClinica hc) throws Exception {
        Tutor tutor = hc.getTutor();
        if (tutor == null) return;

        PdfCellFactory.agregarTituloSeccion(document, "DATOS DEL TUTOR RESPONSABLE");

        PdfPTable tabla = new PdfPTable(4);
        tabla.setWidthPercentage(100);
        tabla.setWidths(new float[]{15, 35, 15, 35});
        tabla.setSpacingBefore(8f);
        tabla.setSpacingAfter(10f);

        PdfTableBuilder.of(tabla)
                .fila("Nombre",        tutor.getNombreTutor(),      "Edad",    tutor.getEdadTutor())
                .fila("Teléfono casa", tutor.getTelefonoCasaTutor(), "Celular", tutor.getCelularTutor());

        // Domicilio ocupa 3 columnas
        PdfCellFactory.agregarEtiqueta(tabla, "Domicilio");
        PdfPCell domicilio = PdfCellFactory.valor(tutor.getDomicilioTutor());
        domicilio.setColspan(3);
        tabla.addCell(domicilio);

        document.add(tabla);
    }
}