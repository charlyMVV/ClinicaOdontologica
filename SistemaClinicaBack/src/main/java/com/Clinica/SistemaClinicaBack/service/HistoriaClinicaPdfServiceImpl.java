package com.Clinica.SistemaClinicaBack.service;

import com.Clinica.SistemaClinicaBack.entity.HistoriaClinica;
import com.Clinica.SistemaClinicaBack.pdf.PdfSeccion;
import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoriaClinicaPdfServiceImpl implements HistoriaClinicaPdfService {

    private final HistoriaClinicaService historiaClinicaService;
    private final List<PdfSeccion> secciones; // Spring inyecta todas en orden por @Order

    @Override
    public byte[] generarPdf(Integer idHistoriaClinica) {
        HistoriaClinica hc = historiaClinicaService.findById(idHistoriaClinica);

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Document document = new Document(PageSize.LETTER, 40, 40, 35, 35);
            PdfWriter.getInstance(document, baos);
            document.open();

            for (PdfSeccion seccion : secciones) {
                seccion.agregar(document, hc);
            }

            document.close();
            return baos.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Error al generar PDF de historia clínica", e);
        }
    }
}