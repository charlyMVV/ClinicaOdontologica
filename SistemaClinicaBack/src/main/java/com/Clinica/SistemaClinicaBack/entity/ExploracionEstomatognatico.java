package com.Clinica.SistemaClinicaBack.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "exploracion_estomatognatico")
public class ExploracionEstomatognatico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEstomatognatico;
    private boolean ruidos;
    private boolean lateralidad;
    private boolean apertura;
    private boolean chasquidos;
    private boolean crepitacion;
    private boolean dificultadAbrirboca;
    private boolean dolorAberturaLateralidad;
    private boolean fatigaDolorMuscular;
    private boolean disminuicionAbertura;
    private boolean desviacionAberturaCierre;
    @Column(name = "CURP_fk_estomatognatico", unique = true)
    private String curp;

    public ExploracionEstomatognatico() {
    }

    public ExploracionEstomatognatico(int idEstomatognatico, boolean ruidos, boolean lateralidad, boolean apertura, boolean chasquidos, boolean crepitacion, boolean dificultadAbrirboca, boolean dolorAberturaLateralidad, boolean fatigaDolorMuscular, boolean disminuicionAbertura, boolean desviacionAberturaCierre, String curp) {
        this.idEstomatognatico = idEstomatognatico;
        this.ruidos = ruidos;
        this.lateralidad = lateralidad;
        this.apertura = apertura;
        this.chasquidos = chasquidos;
        this.crepitacion = crepitacion;
        this.dificultadAbrirboca = dificultadAbrirboca;
        this.dolorAberturaLateralidad = dolorAberturaLateralidad;
        this.fatigaDolorMuscular = fatigaDolorMuscular;
        this.disminuicionAbertura = disminuicionAbertura;
        this.desviacionAberturaCierre = desviacionAberturaCierre;
        this.curp = curp;
    }

    public int getIdEstomatognatico() {
        return idEstomatognatico;
    }

    public void setIdEstomatognatico(int idEstomatognatico) {
        this.idEstomatognatico = idEstomatognatico;
    }

    public boolean isRuidos() {
        return ruidos;
    }

    public void setRuidos(boolean ruidos) {
        this.ruidos = ruidos;
    }

    public boolean isLateralidad() {
        return lateralidad;
    }

    public void setLateralidad(boolean lateralidad) {
        this.lateralidad = lateralidad;
    }

    public boolean isApertura() {
        return apertura;
    }

    public void setApertura(boolean apertura) {
        this.apertura = apertura;
    }

    public boolean isChasquidos() {
        return chasquidos;
    }

    public void setChasquidos(boolean chasquidos) {
        this.chasquidos = chasquidos;
    }

    public boolean isCrepitacion() {
        return crepitacion;
    }

    public void setCrepitacion(boolean crepitacion) {
        this.crepitacion = crepitacion;
    }

    public boolean isDificultadAbrirboca() {
        return dificultadAbrirboca;
    }

    public void setDificultadAbrirboca(boolean dificultadAbrirboca) {
        this.dificultadAbrirboca = dificultadAbrirboca;
    }

    public boolean isDolorAberturaLateralidad() {
        return dolorAberturaLateralidad;
    }

    public void setDolorAberturaLateralidad(boolean dolorAberturaLateralidad) {
        this.dolorAberturaLateralidad = dolorAberturaLateralidad;
    }

    public boolean isFatigaDolorMuscular() {
        return fatigaDolorMuscular;
    }

    public void setFatigaDolorMuscular(boolean fatigaDolorMuscular) {
        this.fatigaDolorMuscular = fatigaDolorMuscular;
    }

    public boolean isDisminuicionAbertura() {
        return disminuicionAbertura;
    }

    public void setDisminuicionAbertura(boolean disminuicionAbertura) {
        this.disminuicionAbertura = disminuicionAbertura;
    }

    public boolean isDesviacionAberturaCierre() {
        return desviacionAberturaCierre;
    }

    public void setDesviacionAberturaCierre(boolean desviacionAberturaCierre) {
        this.desviacionAberturaCierre = desviacionAberturaCierre;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

   
    
 
    
    
}
