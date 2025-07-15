
package com.Clinica.SistemaClinicaBack.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "exploracion_cabezacuello")
public class CabezaCuello {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idExploracionCabezacuello;
    private int exostosis;
    private int endotosis;      
    private int dolicocefalico;
    private int mesocefalico;
    private int branquicefalico;       
    private int asimetriaTransversal;       
    private int asimetriaLongitudinal;       
    private int perfilConcavo;       
    private int perfilConvexo;       
    private int perfilRecto;
    private int pielNormal;
    private int pielPalida;       
    private int pielCianotica;       
    private int pielEnrojecida;      
    private int musculosHipotonicos;
    private int musculosHipertonicos;      
    private int musculosEspasticos;        
    private int cadenaGanglionar;
    @Column(unique = true, name = "CURP_fk_exploracion")
    private String curp;

    public CabezaCuello() {
    }

    public CabezaCuello(int idExploracionCabezacuello, int exostosis, int endotosis, int dolicocefalico, int mesocefalico, int branquicefalico, int asimetriaTransversal, int asimetriaLongitudinal, int perfilConcavo, int perfilConvexo, int perfilRecto, int pielNormal, int pielPalida, int pielCianotica, int pielEnrojecida, int musculosHipotonicos, int musculosHipertonicos, int musculosEspasticos, int cadenaGanglionar, String curp) {
        this.idExploracionCabezacuello = idExploracionCabezacuello;
        this.exostosis = exostosis;
        this.endotosis = endotosis;
        this.dolicocefalico = dolicocefalico;
        this.mesocefalico = mesocefalico;
        this.branquicefalico = branquicefalico;
        this.asimetriaTransversal = asimetriaTransversal;
        this.asimetriaLongitudinal = asimetriaLongitudinal;
        this.perfilConcavo = perfilConcavo;
        this.perfilConvexo = perfilConvexo;
        this.perfilRecto = perfilRecto;
        this.pielNormal = pielNormal;
        this.pielPalida = pielPalida;
        this.pielCianotica = pielCianotica;
        this.pielEnrojecida = pielEnrojecida;
        this.musculosHipotonicos = musculosHipotonicos;
        this.musculosHipertonicos = musculosHipertonicos;
        this.musculosEspasticos = musculosEspasticos;
        this.cadenaGanglionar = cadenaGanglionar;
        this.curp = curp;
    }
    
    

    

    public int getIdExploracionCabezacuello() {
        return idExploracionCabezacuello;
    }

    public void setIdExploracionCabezacuello(int idExploracionCabezacuello) {
        this.idExploracionCabezacuello = idExploracionCabezacuello;
    }

    public int getExostosis() {
        return exostosis;
    }

    public void setExostosis(int exostosis) {
        this.exostosis = exostosis;
    }

    public int getEndotosis() {
        return endotosis;
    }

    public void setEndotosis(int endotosis) {
        this.endotosis = endotosis;
    }

    public int getDolicocefalico() {
        return dolicocefalico;
    }

    public void setDolicocefalico(int dolicocefalico) {
        this.dolicocefalico = dolicocefalico;
    }

    

    public int getMesocefalico() {
        return mesocefalico;
    }

    public void setMesocefalico(int mesocefalico) {
        this.mesocefalico = mesocefalico;
    }

    public int getBranquicefalico() {
        return branquicefalico;
    }

    public void setBranquicefalico(int branquicefalico) {
        this.branquicefalico = branquicefalico;
    }

    public int getAsimetriaTransversal() {
        return asimetriaTransversal;
    }

    public void setAsimetriaTransversal(int asimetriaTransversal) {
        this.asimetriaTransversal = asimetriaTransversal;
    }

    public int getAsimetriaLongitudinal() {
        return asimetriaLongitudinal;
    }

    public void setAsimetriaLongitudinal(int asimetriaLongitudinal) {
        this.asimetriaLongitudinal = asimetriaLongitudinal;
    }

    public int getPerfilConcavo() {
        return perfilConcavo;
    }

    public void setPerfilConcavo(int perfilConcavo) {
        this.perfilConcavo = perfilConcavo;
    }

    public int getPerfilConvexo() {
        return perfilConvexo;
    }

    public void setPerfilConvexo(int perfilConvexo) {
        this.perfilConvexo = perfilConvexo;
    }

    public int getPerfilRecto() {
        return perfilRecto;
    }

    public void setPerfilRecto(int perfilRecto) {
        this.perfilRecto = perfilRecto;
    }

    public int getPielNormal() {
        return pielNormal;
    }

    public void setPielNormal(int pielNormal) {
        this.pielNormal = pielNormal;
    }

    public int getPielPalida() {
        return pielPalida;
    }

    public void setPielPalida(int pielPalida) {
        this.pielPalida = pielPalida;
    }

    public int getPielCianotica() {
        return pielCianotica;
    }

    public void setPielCianotica(int pielCianotica) {
        this.pielCianotica = pielCianotica;
    }

    public int getPielEnrojecida() {
        return pielEnrojecida;
    }

    public void setPielEnrojecida(int pielEnrojecida) {
        this.pielEnrojecida = pielEnrojecida;
    }

    public int getMusculosHipotonicos() {
        return musculosHipotonicos;
    }

    public void setMusculosHipotonicos(int musculosHipotonicos) {
        this.musculosHipotonicos = musculosHipotonicos;
    }

    public int getMusculosHipertonicos() {
        return musculosHipertonicos;
    }

    public void setMusculosHipertonicos(int musculosHipertonicos) {
        this.musculosHipertonicos = musculosHipertonicos;
    }

    public int getMusculosEspasticos() {
        return musculosEspasticos;
    }

    public void setMusculosEspasticos(int musculosEspasticos) {
        this.musculosEspasticos = musculosEspasticos;
    }

    public int getCadenaGanglionar() {
        return cadenaGanglionar;
    }

    public void setCadenaGanglionar(int cadenaGanglionar) {
        this.cadenaGanglionar = cadenaGanglionar;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }
    
    
            
            
}
