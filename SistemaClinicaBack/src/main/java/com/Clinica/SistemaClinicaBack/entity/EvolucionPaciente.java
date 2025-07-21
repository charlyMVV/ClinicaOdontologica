package com.Clinica.SistemaClinicaBack.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "control_evolucion")
public class EvolucionPaciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idControlEvolucion;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha")
    private LocalDate fecha;
    private String comentarioControl;
    @Column(name = "CURP_fk_control", unique = true)
    private String curp;

    public EvolucionPaciente() {
    }

    public EvolucionPaciente(int idControlEvolucion, LocalDate fecha, String comentarioControl, String curp) {
        this.idControlEvolucion = idControlEvolucion;
        this.fecha = fecha;
        this.comentarioControl = comentarioControl;
        this.curp = curp;
    }

    public int getIdControlEvolucion() {
        return idControlEvolucion;
    }

    public void setIdControlEvolucion(int idControlEvolucion) {
        this.idControlEvolucion = idControlEvolucion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getComentarioControl() {
        return comentarioControl;
    }

    public void setComentarioControl(String comentarioControl) {
        this.comentarioControl = comentarioControl;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    
        
}
