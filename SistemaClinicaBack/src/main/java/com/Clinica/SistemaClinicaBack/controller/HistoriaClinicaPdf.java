package com.Clinica.SistemaClinicaBack.controller;

import com.Clinica.SistemaClinicaBack.service.HistoriaClinicaPdfService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class HistoriaClinicaPdf{

    private final HistoriaClinicaPdfService historiaClinicaPdfService;

    @GetMapping("/api/historia-clinica/{idHistoriaClinica}/pdf")
    public ResponseEntity<byte[]> generarPdf(
            @PathVariable Integer idHistoriaClinica) {

        byte[] pdf = historiaClinicaPdfService.generarPdf(idHistoriaClinica);

        return ResponseEntity.ok()
                .header("Content-Disposition","inline; filename=historia-clinica-" + idHistoriaClinica + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

}


