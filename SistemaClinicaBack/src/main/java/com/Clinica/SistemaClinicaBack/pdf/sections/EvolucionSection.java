package com.Clinica.SistemaClinicaBack.pdf.sections;

import com.Clinica.SistemaClinicaBack.entity.EvolucionPaciente;
import com.Clinica.SistemaClinicaBack.entity.HistoriaClinica;
import com.Clinica.SistemaClinicaBack.pdf.PdfCellFactory;
import com.Clinica.SistemaClinicaBack.pdf.PdfSeccion;
import com.Clinica.SistemaClinicaBack.pdf.PdfTableBuilder;
import com.Clinica.SistemaClinicaBack.repository.EvolucionPacienteRepository;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(8)
@RequiredArgsConstructor
public class EvolucionSection implements PdfSeccion {

    private final EvolucionPacienteRepository evolucionPacienteRepository;

    @Override
    public void agregar(Document document, HistoriaClinica hc) throws Exception {
        List<EvolucionPaciente> evoluciones =
                evolucionPacienteRepository.findByHistoriaClinica_IdHistoriaClinica(hc.getIdHistoriaClinica());

        PdfCellFactory.agregarTituloSeccion(document, "CONTROL DE EVOLUCIÓN");

        if (evoluciones == null || evoluciones.isEmpty()) {
            document.add(new Paragraph("No hay evoluciones registradas."));
            return;
        }

        PdfPTable tabla = new PdfPTable(2);
        tabla.setWidthPercentage(100);
        tabla.setWidths(new float[]{20, 80});
        tabla.setSpacingBefore(8f);
        tabla.setSpacingAfter(10f);

        PdfTableBuilder.of(tabla).encabezados("Fecha", "Comentario de control");

        for (EvolucionPaciente ev : evoluciones) {
            PdfCellFactory.agregarTexto(tabla,
                    ev.getFecha() != null ? ev.getFecha().toString() : "",
                    Element.ALIGN_CENTER);
            PdfCellFactory.agregarTexto(tabla,
                    PdfCellFactory.sanitizar(ev.getComentarioControl()),
                    Element.ALIGN_LEFT);
        }

        document.add(tabla);
    }
}