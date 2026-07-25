package com.Clinica.SistemaClinicaBack.service;

import com.Clinica.SistemaClinicaBack.entity.Clinica;
import com.Clinica.SistemaClinicaBack.repository.ClinicaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicaService {

    private final ClinicaRepository clinicaRepository;

    public ClinicaService(ClinicaRepository clinicaRepository) {
        this.clinicaRepository = clinicaRepository;
    }

    public Clinica guardarClinica(Clinica clinica) {

        if (clinica.getNombreClinica() == null || clinica.getNombreClinica().trim().isEmpty()) {
            throw new RuntimeException("El nombre de la clínica es obligatorio.");
        }

        if (clinica.getResponsableClinica() == null || clinica.getResponsableClinica().trim().isEmpty()) {
            throw new RuntimeException("El responsable de la clínica es obligatorio.");
        }

        if (clinicaRepository.existsByNombreClinicaIgnoreCase(clinica.getNombreClinica().trim())) {
            throw new RuntimeException("Ya existe una clínica con ese nombre.");
        }

        clinica.setNombreClinica(clinica.getNombreClinica().trim());
        clinica.setResponsableClinica(clinica.getResponsableClinica().trim());

        return clinicaRepository.save(clinica);
    }

    public List<Clinica> listarClinicas() {
        return clinicaRepository.findAll();
    }
}