
package com.Clinica.SistemaClinicaBack.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "antecedentes")
public class Antecedentes {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAntecedentes;
    private String descripcionAntecedentes;
    private String tipoAntecedentes;
    private String respuesta;
    private String detalle;
    @Column(name = "CURP_fk_antecedentes")
    private String curp;
    @ManyToOne
    @JoinColumn(name = "fk_historia_clinica")
    private HistoriaClinica historiaClinica;

    public Antecedentes() {
    }

    public Antecedentes(int idAntecedentes, String descripcionAntecedentes, String tipoAntecedentes, String respuesta, String descripcion, String curp) {
        this.idAntecedentes = idAntecedentes;
        this.descripcionAntecedentes = descripcionAntecedentes;
        this.tipoAntecedentes = tipoAntecedentes;
        this.respuesta = respuesta;
        this.detalle = descripcion;
        this.curp = curp;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }




    public int getIdAntecedentes() {
        return idAntecedentes;
    }

    public void setIdAntecedentes(int idAntecedentes) {
        this.idAntecedentes = idAntecedentes;
    }

    public String getDescripcionAntecedentes() {
        return descripcionAntecedentes;
    }

    public void setDescripcionAntecedentes(String descripcionAntecedentes) {
        this.descripcionAntecedentes = descripcionAntecedentes;
    }

    public String getTipoAntecedentes() {
        return tipoAntecedentes;
    }

    public void setTipoAntecedentes(String tipoAntecedentes) {
        this.tipoAntecedentes = tipoAntecedentes;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
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
