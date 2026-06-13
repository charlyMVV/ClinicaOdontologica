package com.Clinica.SistemaClinicaBack.service;

import com.Clinica.SistemaClinicaBack.entity.HistoriaClinica;
import com.Clinica.SistemaClinicaBack.entity.Paciente;
import com.Clinica.SistemaClinicaBack.entity.Usuario;

import java.util.List;

public interface HistoriaClinicaService {

    HistoriaClinica save(HistoriaClinica historiaClinica);

    HistoriaClinica update(HistoriaClinica historiaClinica);

    HistoriaClinica findById(Integer id);

    List<HistoriaClinica> findAll();

    void deleteById(Integer id);

    HistoriaClinica findByPaciente(Integer idPaciente);

    HistoriaClinica obtenerOCrearHistoriaClinica(
            Paciente paciente,
            Usuario usuario
    );

    HistoriaClinica findByCurpPaciente(String curp);

    List<HistoriaClinica> findAllByPaciente_Curp(String curp);

    HistoriaClinica cambiarEstatus(Integer idHistoriaClinica, String claveEstatus);

    public void validarHistoriaCompleta(Integer idHistoriaClinica);

    HistoriaClinica crearNuevaHistoriaClinica(String curp, String matricula, String tipoHc);
}