
package com.Clinica.SistemaClinicaBack.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "diagnostico_tratamiento")
public class DiagnosticoTratamiento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDiagnostico;
    private String interpretacionRx;
    private String diagnostico;
    private String resumenTratamiento;
    @Column(name = "CURP_fk_diagnostico", unique = true)
    private String curp;

    public DiagnosticoTratamiento() {
    }

    public DiagnosticoTratamiento(int idDiagnostico, String interpretacionRx, String diagnostico, String resumenTratamiento, String curp) {
        this.idDiagnostico = idDiagnostico;
        this.interpretacionRx = interpretacionRx;
        this.diagnostico = diagnostico;
        this.resumenTratamiento = resumenTratamiento;
        this.curp = curp;
    }

    public int getIdDiagnostico() {
        return idDiagnostico;
    }

    public void setIdDiagnostico(int idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }

    public String getInterpretacionRx() {
        return interpretacionRx;
    }

    public void setInterpretacionRx(String interpretacionRx) {
        this.interpretacionRx = interpretacionRx;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getResumenTratamiento() {
        return resumenTratamiento;
    }

    public void setResumenTratamiento(String resumenTratamiento) {
        this.resumenTratamiento = resumenTratamiento;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }
    
    
    
    
}
