
package com.Clinica.SistemaClinicaBack.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tutor")
public class Tutor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTutor;
    private String nombreTutor;
    private String edadTutor;
    private String domicilioTutor;
    private String telefonoCasaTutor;
    private String celularTutor;
    @Column(name = "CURP_fk_tutor", unique = true)
    private String curp;

    public Tutor() {
    }

    public Tutor(int idTutor, String nombreTutor, String edadTutor, String domicilioTutor, String telefonoCasaTutor, String celularTutor, String curp) {
        this.idTutor = idTutor;
        this.nombreTutor = nombreTutor;
        this.edadTutor = edadTutor;
        this.domicilioTutor = domicilioTutor;
        this.telefonoCasaTutor = telefonoCasaTutor;
        this.celularTutor = celularTutor;
        this.curp = curp;
    }

    public int getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(int idTutor) {
        this.idTutor = idTutor;
    }

    public String getNombreTutor() {
        return nombreTutor;
    }

    public void setNombreTutor(String nombreTutor) {
        this.nombreTutor = nombreTutor;
    }

    public String getEdadTutor() {
        return edadTutor;
    }

    public void setEdadTutor(String edadTutor) {
        this.edadTutor = edadTutor;
    }

    public String getDomicilioTutor() {
        return domicilioTutor;
    }

    public void setDomicilioTutor(String domicilioTutor) {
        this.domicilioTutor = domicilioTutor;
    }

    public String getTelefonoCasaTutor() {
        return telefonoCasaTutor;
    }

    public void setTelefonoCasaTutor(String telefonoCasaTutor) {
        this.telefonoCasaTutor = telefonoCasaTutor;
    }

    public String getCelularTutor() {
        return celularTutor;
    }

    public void setCelularTutor(String celularTutor) {
        this.celularTutor = celularTutor;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    
    
    
    
}
