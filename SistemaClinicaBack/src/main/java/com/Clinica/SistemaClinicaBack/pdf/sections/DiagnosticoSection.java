package com.Clinica.SistemaClinicaBack.pdf.sections;

import com.Clinica.SistemaClinicaBack.entity.DiagnosticoTratamiento;
import com.Clinica.SistemaClinicaBack.entity.HistoriaClinica;
import com.Clinica.SistemaClinicaBack.pdf.PdfCellFactory;
import com.Clinica.SistemaClinicaBack.pdf.PdfSeccion;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(6)
public class DiagnosticoSection implements PdfSeccion {

    @Override
    public void agregar(Document document, HistoriaClinica hc) throws Exception {
        DiagnosticoTratamiento dt = hc.getDiagnosticoTratamiento();

        PdfCellFactory.agregarTituloSeccion(document, "DIAGNÓSTICO Y TRATAMIENTO");

        if (dt == null) {
            document.add(new Paragraph("No hay diagnóstico y tratamiento registrado."));
            return;
        }

        PdfCellFactory.agregarCampoTextoLargo(document, "Interpretación RX",      dt.getInterpretacionRx());
        PdfCellFactory.agregarCampoTextoLargo(document, "Diagnóstico",            dt.getDiagnostico());
        PdfCellFactory.agregarCampoTextoLargo(document, "Resumen del tratamiento", dt.getResumenTratamiento());
    }
}