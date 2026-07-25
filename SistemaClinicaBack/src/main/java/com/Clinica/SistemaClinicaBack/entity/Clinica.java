package com.Clinica.SistemaClinicaBack.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cat_clinicas")
public class Clinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_clinica")
    private Integer idClinica;

    @Column(name = "nombre_clinica", nullable = false, unique = true, length = 150)
    private String nombreClinica;

    @Column(name = "responsable_clinica", nullable = false, length = 150)
    private String responsableClinica;

    public Clinica() {
    }

    public Clinica(Integer idClinica, String nombreClinica, String responsableClinica) {
        this.idClinica = idClinica;
        this.nombreClinica = nombreClinica;
        this.responsableClinica = responsableClinica;
    }

    public Integer getIdClinica() {
        return idClinica;
    }

    public void setIdClinica(Integer idClinica) {
        this.idClinica = idClinica;
    }

    public String getNombreClinica() {
        return nombreClinica;
    }

    public void setNombreClinica(String nombreClinica) {
        this.nombreClinica = nombreClinica;
    }

    public String getResponsableClinica() {
        return responsableClinica;
    }

    public void setResponsableClinica(String responsableClinica) {
        this.responsableClinica = responsableClinica;
    }
}