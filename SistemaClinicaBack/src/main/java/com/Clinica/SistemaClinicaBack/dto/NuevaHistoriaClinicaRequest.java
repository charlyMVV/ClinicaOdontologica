package com.Clinica.SistemaClinicaBack.dto;

public class NuevaHistoriaClinicaRequest {

    private String curp;
    private String matricula;
    private String tipoHc;

    public NuevaHistoriaClinicaRequest() {
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getTipoHc() {
        return tipoHc;
    }

    public void setTipoHc(String tipoHc) {
        this.tipoHc = tipoHc;
    }
}