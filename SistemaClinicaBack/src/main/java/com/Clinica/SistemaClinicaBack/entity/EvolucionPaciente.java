package com.Clinica.SistemaClinicaBack.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

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
    @Column(name = "CURP_fk_control")
    private String curp;
    @ManyToOne
    @JoinColumn(name = "fk_historia_clinica")
    private HistoriaClinica historiaClinica;

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

    public HistoriaClinica getHistoriaClinica() {
        return historiaClinica;
    }

    public void setHistoriaClinica(HistoriaClinica historiaClinica) {
        this.historiaClinica = historiaClinica;
    }
}
