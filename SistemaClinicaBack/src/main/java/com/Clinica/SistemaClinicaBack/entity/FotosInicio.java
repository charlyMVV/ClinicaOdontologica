/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Clinica.SistemaClinicaBack.entity;

import jakarta.persistence.*;

/**
 *
 * @author charly michel
 */
@Entity
public class FotosInicio {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFotosInicio;
    @Lob
    private String fotos;
    private String curp;
    @ManyToOne
    @JoinColumn(name = "fk_historia_clinica")
    private HistoriaClinica historiaClinica;

    public FotosInicio() {
    }

    public FotosInicio(Long idFotosInicio, String fotos, String curp) {
        this.idFotosInicio = idFotosInicio;
        this.fotos = fotos;
        this.curp = curp;
    }

    public Long getIdFotosInicio() {
        return idFotosInicio;
    }

    public void setIdFotosInicio(Long idFotosInicio) {
        this.idFotosInicio = idFotosInicio;
    }

    public String getFotos() {
        return fotos;
    }

    public void setFotos(String fotos) {
        this.fotos = fotos;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public HistoriaClinica getHistoriaClinica() {
        return historiaClinica;
    }

    public void setHistoriaClinica(HistoriaClinica historiaClinica) {
        this.historiaClinica = historiaClinica;
    }
}
