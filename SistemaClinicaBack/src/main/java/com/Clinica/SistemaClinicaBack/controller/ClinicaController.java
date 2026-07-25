package com.Clinica.SistemaClinicaBack.controller;

import com.Clinica.SistemaClinicaBack.entity.Clinica;
import com.Clinica.SistemaClinicaBack.service.ClinicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historia-clinica/clinicas")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ClinicaController {

    private final ClinicaService clinicaService;

    @PostMapping
    public ResponseEntity<?> guardarClinica(@RequestBody Clinica clinica) {
        try {
            Clinica nuevaClinica = clinicaService.guardarClinica(clinica);
            return ResponseEntity.ok(nuevaClinica);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Clinica>> listarClinicas() {
        return ResponseEntity.ok(clinicaService.listarClinicas());
    }
}