package com.Clinica.SistemaClinicaBack.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tejidos_blandos")
public class TejidosBlandos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTejidosBlandos;
    private String ganglios;
    private String glandulasSalivales;
    private String labioExterno;
    private String bordeBermellon;
    private String labioInterno;
    private String comisuras;
    private String carrillos;
    private String fondoDeSaco;
    private String frenillos;
    private String lenguaTercioMedio;
    private String paladarDuro;
    private String paladarBlando;
    private String istmoBucofaringe;
    private String lenguaDorso;
    private String lenguaBordes;
    private String lenguaVentral;
    private String pisoBoca;
    private String dientes;
    private String mucosaAlveolar;
    private String encia;
    @Column(name = "CURP_fk_tejidos", unique = true)
    private String curp;

    public TejidosBlandos() {
    }

    public TejidosBlandos(int idTejidosBlandos, String ganglios, String glandulasSalivales, String labioExterno, String bordeBermellon, String labioInterno, String comisuras, String cariilos, String fondoDeSaco, String frenillos, String lenguaTercioMedio, String paladarDuro, String paladarBlando, String istmoBucofaringe, String lenguaDorso, String lenguaBordes, String lenguaVentral, String pisoBoca, String dientes, String muscosaAveolar, String encia, String curp) {
        this.idTejidosBlandos = idTejidosBlandos;
        this.ganglios = ganglios;
        this.glandulasSalivales = glandulasSalivales;
        this.labioExterno = labioExterno;
        this.bordeBermellon = bordeBermellon;
        this.labioInterno = labioInterno;
        this.comisuras = comisuras;
        this.carrillos = cariilos;
        this.fondoDeSaco = fondoDeSaco;
        this.frenillos = frenillos;
        this.lenguaTercioMedio = lenguaTercioMedio;
        this.paladarDuro = paladarDuro;
        this.paladarBlando = paladarBlando;
        this.istmoBucofaringe = istmoBucofaringe;
        this.lenguaDorso = lenguaDorso;
        this.lenguaBordes = lenguaBordes;
        this.lenguaVentral = lenguaVentral;
        this.pisoBoca = pisoBoca;
        this.dientes = dientes;
        this.mucosaAlveolar = muscosaAveolar;
        this.encia = encia;
        this.curp = curp;
    }

    
    
    public int getIdTejidosBlandos() {
        return idTejidosBlandos;
    }

    public void setIdTejidosBlandos(int idTejidosBlandos) {
        this.idTejidosBlandos = idTejidosBlandos;
    }

    public String getGanglios() {
        return ganglios;
    }

    public void setGanglios(String ganglios) {
        this.ganglios = ganglios;
    }

    public String getGlandulasSalivales() {
        return glandulasSalivales;
    }

    public void setGlandulasSalivales(String glandulasSalivales) {
        this.glandulasSalivales = glandulasSalivales;
    }

    public String getLabioExterno() {
        return labioExterno;
    }

    public void setLabioExterno(String labioExterno) {
        this.labioExterno = labioExterno;
    }

    public String getBordeBermellon() {
        return bordeBermellon;
    }

    public void setBordeBermellon(String bordeBermellon) {
        this.bordeBermellon = bordeBermellon;
    }

    public String getLabioInterno() {
        return labioInterno;
    }

    public void setLabioInterno(String labioInterno) {
        this.labioInterno = labioInterno;
    }

    public String getComisuras() {
        return comisuras;
    }

    public void setComisuras(String comisuras) {
        this.comisuras = comisuras;
    }

    public String getCarrillos() {
        return carrillos;
    }

    public void setCarrillos(String carrillos) {
        this.carrillos = carrillos;
    }

    

    public String getFondoDeSaco() {
        return fondoDeSaco;
    }

    public void setFondoDeSaco(String fondoDeSaco) {
        this.fondoDeSaco = fondoDeSaco;
    }

    public String getFrenillos() {
        return frenillos;
    }

    public void setFrenillos(String frenillos) {
        this.frenillos = frenillos;
    }

    public String getLenguaTercioMedio() {
        return lenguaTercioMedio;
    }

    public void setLenguaTercioMedio(String lenguaTercioMedio) {
        this.lenguaTercioMedio = lenguaTercioMedio;
    }

    public String getPaladarDuro() {
        return paladarDuro;
    }

    public void setPaladarDuro(String paladarDuro) {
        this.paladarDuro = paladarDuro;
    }

    public String getPaladarBlando() {
        return paladarBlando;
    }

    public void setPaladarBlando(String paladarBlando) {
        this.paladarBlando = paladarBlando;
    }

    public String getIstmoBucofaringe() {
        return istmoBucofaringe;
    }

    public void setIstmoBucofaringe(String istmoBucofaringe) {
        this.istmoBucofaringe = istmoBucofaringe;
    }

    public String getLenguaDorso() {
        return lenguaDorso;
    }

    public void setLenguaDorso(String lenguaDorso) {
        this.lenguaDorso = lenguaDorso;
    }

    public String getLenguaBordes() {
        return lenguaBordes;
    }

    public void setLenguaBordes(String lenguaBordes) {
        this.lenguaBordes = lenguaBordes;
    }

    public String getLenguaVentral() {
        return lenguaVentral;
    }

    public void setLenguaVentral(String lenguaVentral) {
        this.lenguaVentral = lenguaVentral;
    }

    public String getPisoBoca() {
        return pisoBoca;
    }

    public void setPisoBoca(String pisoBoca) {
        this.pisoBoca = pisoBoca;
    }

    public String getDientes() {
        return dientes;
    }

    public void setDientes(String dientes) {
        this.dientes = dientes;
    }

    public String getMucosaAlveolar() {
        return mucosaAlveolar;
    }

    public void setMucosaAlveolar(String mucosaAlveolar) {
        this.mucosaAlveolar = mucosaAlveolar;
    }

    
    public String getEncia() {
        return encia;
    }

    public void setEncia(String encia) {
        this.encia = encia;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }
    
    
    
    
}

