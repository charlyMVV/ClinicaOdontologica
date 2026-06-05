package com.Clinica.SistemaClinicaBack.dto;

import com.Clinica.SistemaClinicaBack.entity.Paciente;

public class CrearPacienteRequest {

    private Paciente paciente;
    private String matricula;

    public CrearPacienteRequest() {
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}