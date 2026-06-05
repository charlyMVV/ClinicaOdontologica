package com.Clinica.SistemaClinicaBack.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cat_tipo_historia_clinica")
public class CatalogoTipoHistoriaClinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_hc")
    private Integer idTipoHc;

    @Column(name = "clave", nullable = false, unique = true)
    private String clave;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    public CatalogoTipoHistoriaClinica() {
    }

    public Integer getIdTipoHc() {
        return idTipoHc;
    }

    public void setIdTipoHc(Integer idTipoHc) {
        this.idTipoHc = idTipoHc;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}