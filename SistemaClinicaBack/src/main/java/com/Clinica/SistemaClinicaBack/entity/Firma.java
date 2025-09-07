/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Clinica.SistemaClinicaBack.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

/**
 *
 * @author charly michel
 */
@Entity
public class Firma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFirma;
    @Lob
    private String firma;
    private String curp;

    public Firma() {
    }

    public Firma(Long idFirma, String firma, String curp) {
        this.idFirma = idFirma;
        this.firma = firma;
        this.curp = curp;
    }

    public Long getIdFirma() {
        return idFirma;
    }

    public void setIdFirma(Long idFirma) {
        this.idFirma = idFirma;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }
    
    
}
