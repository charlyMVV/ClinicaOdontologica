package com.Clinica.SistemaClinicaBack.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cat_estatus_historia_clinica")
public class CatalogoEstatusHistoriaClinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estatus_hc")
    private Integer idEstatusHc;

    @Column(name = "clave", nullable = false, unique = true)
    private String clave;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    public CatalogoEstatusHistoriaClinica() {
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Integer getIdEstatusHc() {
        return idEstatusHc;
    }

    public void setIdEstatusHc(Integer idEstatusHc) {
        this.idEstatusHc = idEstatusHc;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}