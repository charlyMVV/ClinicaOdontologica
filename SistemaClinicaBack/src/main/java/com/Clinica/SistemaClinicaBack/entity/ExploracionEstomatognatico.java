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
    private int ruidos;
    private int lateralidad;
    private int apertura;
    private int chasquidos;
    private int crepitacion;
    private int dificultadAbrirboca;
    private int dolorAberturaLateralidad;
    private int fatigaDolorMuscular;
    private int disminuicionAbertura;
    private int desviacionAberturaCierre;
    @Column(name = "CURP_fk_estomatognatico", unique = true)
    private String curp;

    public ExploracionEstomatognatico() {
    }

    public ExploracionEstomatognatico(int idEstomatognatico, int ruidos, int lateralidad, int apertura, int chasquidos, int crepitacion, int dificultadAbrirboca, int dolorAberturaLateralidad, int fatigaDolorMuscular, int disminuicionAbertura, int desviacionAberturaCierre, String curp) {
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

    public int getRuidos() {
        return ruidos;
    }

    public void setRuidos(int ruidos) {
        this.ruidos = ruidos;
    }

    public int getLateralidad() {
        return lateralidad;
    }

    public void setLateralidad(int lateralidad) {
        this.lateralidad = lateralidad;
    }

    public int getApertura() {
        return apertura;
    }

    public void setApertura(int apertura) {
        this.apertura = apertura;
    }

    public int getChasquidos() {
        return chasquidos;
    }

    public void setChasquidos(int chasquidos) {
        this.chasquidos = chasquidos;
    }

    public int getCrepitacion() {
        return crepitacion;
    }

    public void setCrepitacion(int crepitacion) {
        this.crepitacion = crepitacion;
    }

    public int getDificultadAbrirboca() {
        return dificultadAbrirboca;
    }

    public void setDificultadAbrirboca(int dificultadAbrirboca) {
        this.dificultadAbrirboca = dificultadAbrirboca;
    }

    public int getDolorAberturaLateralidad() {
        return dolorAberturaLateralidad;
    }

    public void setDolorAberturaLateralidad(int dolorAberturaLateralidad) {
        this.dolorAberturaLateralidad = dolorAberturaLateralidad;
    }

    public int getFatigaDolorMuscular() {
        return fatigaDolorMuscular;
    }

    public void setFatigaDolorMuscular(int fatigaDolorMuscular) {
        this.fatigaDolorMuscular = fatigaDolorMuscular;
    }

    public int getDisminuicionAbertura() {
        return disminuicionAbertura;
    }

    public void setDisminuicionAbertura(int disminuicionAbertura) {
        this.disminuicionAbertura = disminuicionAbertura;
    }

    public int getDesviacionAberturaCierre() {
        return desviacionAberturaCierre;
    }

    public void setDesviacionAberturaCierre(int desviacionAberturaCierre) {
        this.desviacionAberturaCierre = desviacionAberturaCierre;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }
    
 
    
    
}
