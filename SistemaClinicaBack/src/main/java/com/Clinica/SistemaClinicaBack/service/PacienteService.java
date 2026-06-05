package com.Clinica.SistemaClinicaBack.service;

import com.Clinica.SistemaClinicaBack.entity.Paciente;
import java.util.List;

public interface PacienteService {

    Paciente save(Paciente paciente);

    List<Paciente> findAll();

    Paciente findById(Integer id);

    void deleteById(Integer id);

    Paciente update(Paciente paciente);

    Paciente findByCURP(String curp);

    boolean existsByCURP(String curp);
}
