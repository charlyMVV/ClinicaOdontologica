package com.Clinica.SistemaClinicaBack.pdf;

import com.Clinica.SistemaClinicaBack.entity.HistoriaClinica;
import com.lowagie.text.Document;

public interface PdfSeccion {
    void agregar(Document document, HistoriaClinica hc) throws Exception;
}
