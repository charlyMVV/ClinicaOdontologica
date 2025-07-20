
package com.Clinica.SistemaClinicaBack.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.Date;

@Entity
@Table(name =  "antecedentes_no_patologicos")
public class AntecedentesNoPatologicos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private int idAntecedentesNoPatologicos;
    @Column(name = "CURP_fk_no_patologicos", unique= true)
    private String curp;
    private String frecuenciaLavadoDientes;
    private String usaAuxiliaresHigiene;
    private String tiposAuxiliaresHigiene;
    private String grupoSanguineo;
    private String factorRh;
    private String cartillaVacunacion;
    private String esquemaCompleto;
    private String vacunasFaltantes;
    private String AntecedentesAlergicos;
    private String cualAlergicos;
    private String antibioticos;
    private String analgesicos;
    private String anestesicos;       
    private String alimentos;
    private String otrasAlergias;
    private String golosinas;
    private String tieneAdicciones;
    private String tabaco;
    private String alcohol;
    private String otrasAdicciones;
    private String haSidoHospitalizado;
    private Date fechaHospitalizacion;
    private String motivoHospitalizacion;
    private String padecimientoActual;
    private String haSidoAnestesiado;
    private String haRecibidoTransfusion;
    private String haRecibidoPerforaciones;
    private String consumeMedicamento;
    private String embarazo;
    private String discapacidad;
    private String tieneIntervenciones;
    private String parteCuerpo;

    public AntecedentesNoPatologicos() {
    }

    public AntecedentesNoPatologicos(int idAntecedentesNoPatologicos, String curp, String frecuenciaLavadoDientes, String usaAuxiliaresHigiene, String tiposAuxiliaresHigiene, String grupoSanguineo, String factorRh, String cartillaVacunacion, String esquemaCompleto, String vacunasFaltantes, String AntecedentesAlergicos, String cualAlergicos, String antibioticos, String analgesicos, String anestesicos, String alimentos, String otrasAlergias, String golosinas, String tieneAdicciones, String tabaco, String alcohol, String otrasAdicciones, String haSidoHospitalizado, Date fechaHospitalizacion, String motivoHospitalizacion, String padecimientoActual, String haSidoAnestesiado, String haRecibidoTransfusion, String haRecibidoPerforaciones, String consumeMedicamento, String embarazo, String discapacidad, String tieneIntervenciones, String parteCuerpo) {
        this.idAntecedentesNoPatologicos = idAntecedentesNoPatologicos;
        this.curp = curp;
        this.frecuenciaLavadoDientes = frecuenciaLavadoDientes;
        this.usaAuxiliaresHigiene = usaAuxiliaresHigiene;
        this.tiposAuxiliaresHigiene = tiposAuxiliaresHigiene;
        this.grupoSanguineo = grupoSanguineo;
        this.factorRh = factorRh;
        this.cartillaVacunacion = cartillaVacunacion;
        this.esquemaCompleto = esquemaCompleto;
        this.vacunasFaltantes = vacunasFaltantes;
        this.AntecedentesAlergicos = AntecedentesAlergicos;
        this.cualAlergicos = cualAlergicos;
        this.antibioticos = antibioticos;
        this.analgesicos = analgesicos;
        this.anestesicos = anestesicos;
        this.alimentos = alimentos;
        this.otrasAlergias = otrasAlergias;
        this.golosinas = golosinas;
        this.tieneAdicciones = tieneAdicciones;
        this.tabaco = tabaco;
        this.alcohol = alcohol;
        this.otrasAdicciones = otrasAdicciones;
        this.haSidoHospitalizado = haSidoHospitalizado;
        this.fechaHospitalizacion = fechaHospitalizacion;
        this.motivoHospitalizacion = motivoHospitalizacion;
        this.padecimientoActual = padecimientoActual;
        this.haSidoAnestesiado = haSidoAnestesiado;
        this.haRecibidoTransfusion = haRecibidoTransfusion;
        this.haRecibidoPerforaciones = haRecibidoPerforaciones;
        this.consumeMedicamento = consumeMedicamento;
        this.embarazo = embarazo;
        this.discapacidad = discapacidad;
        this.tieneIntervenciones = tieneIntervenciones;
        this.parteCuerpo = parteCuerpo;
    }

    public String getGolosinas() {
        return golosinas;
    }

    public void setGolosinas(String golosinas) {
        this.golosinas = golosinas;
    }

   

    public int getIdAntecedentesNoPatologicos() {
        return idAntecedentesNoPatologicos;
    }

    public void setIdAntecedentesNoPatologicos(int idAntecedentesNoPatologicos) {
        this.idAntecedentesNoPatologicos = idAntecedentesNoPatologicos;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getFrecuenciaLavadoDientes() {
        return frecuenciaLavadoDientes;
    }

    public void setFrecuenciaLavadoDientes(String frecuenciaLavadoDientes) {
        this.frecuenciaLavadoDientes = frecuenciaLavadoDientes;
    }

    public String getUsaAuxiliaresHigiene() {
        return usaAuxiliaresHigiene;
    }

    public void setUsaAuxiliaresHigiene(String usaAuxiliaresHigiene) {
        this.usaAuxiliaresHigiene = usaAuxiliaresHigiene;
    }

    public String getTiposAuxiliaresHigiene() {
        return tiposAuxiliaresHigiene;
    }

    public void setTiposAuxiliaresHigiene(String tiposAuxiliaresHigiene) {
        this.tiposAuxiliaresHigiene = tiposAuxiliaresHigiene;
    }

    public String getGrupoSanguineo() {
        return grupoSanguineo;
    }

    public void setGrupoSanguineo(String grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }

    public String getFactorRh() {
        return factorRh;
    }

    public void setFactorRh(String factorRh) {
        this.factorRh = factorRh;
    }

    public String getCartillaVacunacion() {
        return cartillaVacunacion;
    }

    public void setCartillaVacunacion(String cartillaVacunacion) {
        this.cartillaVacunacion = cartillaVacunacion;
    }

    public String getEsquemaCompleto() {
        return esquemaCompleto;
    }

    public void setEsquemaCompleto(String esquemaCompleto) {
        this.esquemaCompleto = esquemaCompleto;
    }

    public String getVacunasFaltantes() {
        return vacunasFaltantes;
    }

    public void setVacunasFaltantes(String vacunasFaltantes) {
        this.vacunasFaltantes = vacunasFaltantes;
    }

    public String getAntecedentesAlergicos() {
        return AntecedentesAlergicos;
    }

    public void setAntecedentesAlergicos(String AntecedentesAlergicos) {
        this.AntecedentesAlergicos = AntecedentesAlergicos;
    }

    public String getCualAlergicos() {
        return cualAlergicos;
    }

    public void setCualAlergicos(String cualAlergicos) {
        this.cualAlergicos = cualAlergicos;
    }

    public String getAntibioticos() {
        return antibioticos;
    }

    public void setAntibioticos(String antibioticos) {
        this.antibioticos = antibioticos;
    }

    public String getAnalgesicos() {
        return analgesicos;
    }

    public void setAnalgesicos(String analgesicos) {
        this.analgesicos = analgesicos;
    }

    public String getAnestesicos() {
        return anestesicos;
    }

    public void setAnestesicos(String anestesicos) {
        this.anestesicos = anestesicos;
    }

    public String getAlimentos() {
        return alimentos;
    }

    public void setAlimentos(String alimentos) {
        this.alimentos = alimentos;
    }

    public String getOtrasAlergias() {
        return otrasAlergias;
    }

    public void setOtrasAlergias(String otrasAlergias) {
        this.otrasAlergias = otrasAlergias;
    }

    public String getTieneAdicciones() {
        return tieneAdicciones;
    }

    public void setTieneAdicciones(String tieneAdicciones) {
        this.tieneAdicciones = tieneAdicciones;
    }

    public String getTabaco() {
        return tabaco;
    }

    public void setTabaco(String tabaco) {
        this.tabaco = tabaco;
    }

    public String getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(String alcohol) {
        this.alcohol = alcohol;
    }
    
    

    public String getOtrasAdicciones() {
        return otrasAdicciones;
    }

    public void setOtrasAdicciones(String otrasAdicciones) {
        this.otrasAdicciones = otrasAdicciones;
    }

    public String getHaSidoHospitalizado() {
        return haSidoHospitalizado;
    }

    public void setHaSidoHospitalizado(String haSidoHospitalizado) {
        this.haSidoHospitalizado = haSidoHospitalizado;
    }

    public Date getFechaHospitalizacion() {
        return fechaHospitalizacion;
    }

    public void setFechaHospitalizacion(Date fechaHospitalizacion) {
        this.fechaHospitalizacion = fechaHospitalizacion;
    }

    public String getMotivoHospitalizacion() {
        return motivoHospitalizacion;
    }

    public void setMotivoHospitalizacion(String motivoHospitalizacion) {
        this.motivoHospitalizacion = motivoHospitalizacion;
    }

    public String getPadecimientoActual() {
        return padecimientoActual;
    }

    public void setPadecimientoActual(String padecimientoActual) {
        this.padecimientoActual = padecimientoActual;
    }

    public String getHaSidoAnestesiado() {
        return haSidoAnestesiado;
    }

    public void setHaSidoAnestesiado(String haSidoAnestesiado) {
        this.haSidoAnestesiado = haSidoAnestesiado;
    }

    public String getHaRecibidoTransfusion() {
        return haRecibidoTransfusion;
    }

    public void setHaRecibidoTransfusion(String haRecibidoTransfusion) {
        this.haRecibidoTransfusion = haRecibidoTransfusion;
    }

    public String getHaRecibidoPerforaciones() {
        return haRecibidoPerforaciones;
    }

    public void setHaRecibidoPerforaciones(String haRecibidoPerforaciones) {
        this.haRecibidoPerforaciones = haRecibidoPerforaciones;
    }

    public String getConsumeMedicamento() {
        return consumeMedicamento;
    }

    public void setConsumeMedicamento(String consumeMedicamento) {
        this.consumeMedicamento = consumeMedicamento;
    }

    public String getEmbarazo() {
        return embarazo;
    }

    public void setEmbarazo(String embarazo) {
        this.embarazo = embarazo;
    }

    public String getDiscapacidad() {
        return discapacidad;
    }

    public void setDiscapacidad(String discapacidad) {
        this.discapacidad = discapacidad;
    }

    public String getTieneIntervenciones() {
        return tieneIntervenciones;
    }

    public void setTieneIntervenciones(String tieneIntervenciones) {
        this.tieneIntervenciones = tieneIntervenciones;
    }

    public String getParteCuerpo() {
        return parteCuerpo;
    }

    public void setParteCuerpo(String parteCuerpo) {
        this.parteCuerpo = parteCuerpo;
    }

    

   
    
    
    
    
    
}
