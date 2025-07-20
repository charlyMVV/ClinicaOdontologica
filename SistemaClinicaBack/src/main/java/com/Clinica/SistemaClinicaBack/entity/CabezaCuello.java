
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
    private boolean exostosis;
    private boolean endotosis;      
    private boolean dolicocefalico;
    private boolean mesocefalico;
    private boolean branquicefalico;       
    private boolean asimetriaTransversal;       
    private boolean asimetriaLongitudinal;       
    private boolean perfilConcavo;       
    private boolean perfilConvexo;       
    private boolean perfilRecto;
    private boolean pielNormal;
    private boolean pielPalida;       
    private boolean pielCianotica;       
    private boolean pielEnrojecida;      
    private boolean musculosHipotonicos;
    private boolean musculosHipertonicos;      
    private boolean musculosEspasticos;        
    private boolean cadenaGanglionar;
    @Column(unique = true, name = "CURP_fk_exploracion")
    private String curp;

    public CabezaCuello() {
    }

    public CabezaCuello(int idExploracionCabezacuello, boolean exostosis, boolean endotosis, boolean dolicocefalico, boolean mesocefalico, boolean branquicefalico, boolean asimetriaTransversal, boolean asimetriaLongitudinal, boolean perfilConcavo, boolean perfilConvexo, boolean perfilRecto, boolean pielNormal, boolean pielPalida, boolean pielCianotica, boolean pielEnrojecida, boolean musculosHipotonicos, boolean musculosHipertonicos, boolean musculosEspasticos, boolean cadenaGanglionar, String curp) {
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

    public boolean isExostosis() {
        return exostosis;
    }

    public void setExostosis(boolean exostosis) {
        this.exostosis = exostosis;
    }

    public boolean isEndotosis() {
        return endotosis;
    }

    public void setEndotosis(boolean endotosis) {
        this.endotosis = endotosis;
    }

    public boolean isDolicocefalico() {
        return dolicocefalico;
    }

    public void setDolicocefalico(boolean dolicocefalico) {
        this.dolicocefalico = dolicocefalico;
    }

    public boolean isMesocefalico() {
        return mesocefalico;
    }

    public void setMesocefalico(boolean mesocefalico) {
        this.mesocefalico = mesocefalico;
    }

    public boolean isBranquicefalico() {
        return branquicefalico;
    }

    public void setBranquicefalico(boolean branquicefalico) {
        this.branquicefalico = branquicefalico;
    }

    public boolean isAsimetriaTransversal() {
        return asimetriaTransversal;
    }

    public void setAsimetriaTransversal(boolean asimetriaTransversal) {
        this.asimetriaTransversal = asimetriaTransversal;
    }

    public boolean isAsimetriaLongitudinal() {
        return asimetriaLongitudinal;
    }

    public void setAsimetriaLongitudinal(boolean asimetriaLongitudinal) {
        this.asimetriaLongitudinal = asimetriaLongitudinal;
    }

    public boolean isPerfilConcavo() {
        return perfilConcavo;
    }

    public void setPerfilConcavo(boolean perfilConcavo) {
        this.perfilConcavo = perfilConcavo;
    }

    public boolean isPerfilConvexo() {
        return perfilConvexo;
    }

    public void setPerfilConvexo(boolean perfilConvexo) {
        this.perfilConvexo = perfilConvexo;
    }

    public boolean isPerfilRecto() {
        return perfilRecto;
    }

    public void setPerfilRecto(boolean perfilRecto) {
        this.perfilRecto = perfilRecto;
    }

    public boolean isPielNormal() {
        return pielNormal;
    }

    public void setPielNormal(boolean pielNormal) {
        this.pielNormal = pielNormal;
    }

    public boolean isPielPalida() {
        return pielPalida;
    }

    public void setPielPalida(boolean pielPalida) {
        this.pielPalida = pielPalida;
    }

    public boolean isPielCianotica() {
        return pielCianotica;
    }

    public void setPielCianotica(boolean pielCianotica) {
        this.pielCianotica = pielCianotica;
    }

    public boolean isPielEnrojecida() {
        return pielEnrojecida;
    }

    public void setPielEnrojecida(boolean pielEnrojecida) {
        this.pielEnrojecida = pielEnrojecida;
    }

    public boolean isMusculosHipotonicos() {
        return musculosHipotonicos;
    }

    public void setMusculosHipotonicos(boolean musculosHipotonicos) {
        this.musculosHipotonicos = musculosHipotonicos;
    }

    public boolean isMusculosHipertonicos() {
        return musculosHipertonicos;
    }

    public void setMusculosHipertonicos(boolean musculosHipertonicos) {
        this.musculosHipertonicos = musculosHipertonicos;
    }

    public boolean isMusculosEspasticos() {
        return musculosEspasticos;
    }

    public void setMusculosEspasticos(boolean musculosEspasticos) {
        this.musculosEspasticos = musculosEspasticos;
    }

    public boolean isCadenaGanglionar() {
        return cadenaGanglionar;
    }

    public void setCadenaGanglionar(boolean cadenaGanglionar) {
        this.cadenaGanglionar = cadenaGanglionar;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    
    
    
    

    

    
}
