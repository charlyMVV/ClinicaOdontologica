package com.Clinica.SistemaClinicaBack.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "historia_clinica")
public class HistoriaClinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historia_clinica")
    private Integer idHistoriaClinica;

    @ManyToOne
    @JoinColumn(name = "matricula_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "fk_paciente", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "fk_tipo_hc", nullable = false)
    private CatalogoTipoHistoriaClinica tipoHistoriaClinica;

    @ManyToOne
    @JoinColumn(name = "fk_estatus_hc", nullable = false)
    private CatalogoEstatusHistoriaClinica estatusHistoriaClinica;

    @OneToOne
    @JoinColumn(name = "fk_tutor")
    private Tutor tutor;

    @OneToOne
    @JoinColumn(name = "fk_antecedentes_no_patologicos")
    private AntecedentesNoPatologicos antecedentesNoPatologicos;

    @OneToOne
    @JoinColumn(name = "fk_signos_vitales")
    private SignosVitales signosVitales;

    @OneToOne
    @JoinColumn(name = "fk_cabeza_cuello")
    private CabezaCuello cabezaCuello;

    @OneToOne
    @JoinColumn(name = "fk_tejidos_blandos")
    private TejidosBlandos tejidosBlandos;

    @OneToOne
    @JoinColumn(name = "fk_estomatognatico")
    private ExploracionEstomatognatico exploracionEstomatognatico;

    @OneToOne
    @JoinColumn(name = "fk_diagnostico_tratamiento")
    private DiagnosticoTratamiento diagnosticoTratamiento;

    @OneToOne
    @JoinColumn(name = "fk_firma")
    private Firma firma;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @PrePersist
    public void prePersist() {
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
    }

    public HistoriaClinica() {
    }

    public Integer getIdHistoriaClinica() {
        return idHistoriaClinica;
    }

    public void setIdHistoriaClinica(Integer idHistoriaClinica) {
        this.idHistoriaClinica = idHistoriaClinica;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public CatalogoTipoHistoriaClinica getTipoHistoriaClinica() {
        return tipoHistoriaClinica;
    }

    public void setTipoHistoriaClinica(CatalogoTipoHistoriaClinica tipoHistoriaClinica) {
        this.tipoHistoriaClinica = tipoHistoriaClinica;
    }

    public CatalogoEstatusHistoriaClinica getEstatusHistoriaClinica() {
        return estatusHistoriaClinica;
    }

    public void setEstatusHistoriaClinica(CatalogoEstatusHistoriaClinica estatusHistoriaClinica) {
        this.estatusHistoriaClinica = estatusHistoriaClinica;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }

    public AntecedentesNoPatologicos getAntecedentesNoPatologicos() {
        return antecedentesNoPatologicos;
    }

    public void setAntecedentesNoPatologicos(AntecedentesNoPatologicos antecedentesNoPatologicos) {
        this.antecedentesNoPatologicos = antecedentesNoPatologicos;
    }

    public SignosVitales getSignosVitales() {
        return signosVitales;
    }

    public void setSignosVitales(SignosVitales signosVitales) {
        this.signosVitales = signosVitales;
    }

    public CabezaCuello getCabezaCuello() {
        return cabezaCuello;
    }

    public void setCabezaCuello(CabezaCuello cabezaCuello) {
        this.cabezaCuello = cabezaCuello;
    }

    public TejidosBlandos getTejidosBlandos() {
        return tejidosBlandos;
    }

    public void setTejidosBlandos(TejidosBlandos tejidosBlandos) {
        this.tejidosBlandos = tejidosBlandos;
    }

    public ExploracionEstomatognatico getExploracionEstomatognatico() {
        return exploracionEstomatognatico;
    }

    public void setExploracionEstomatognatico(ExploracionEstomatognatico exploracionEstomatognatico) {
        this.exploracionEstomatognatico = exploracionEstomatognatico;
    }

    public DiagnosticoTratamiento getDiagnosticoTratamiento() {
        return diagnosticoTratamiento;
    }

    public void setDiagnosticoTratamiento(DiagnosticoTratamiento diagnosticoTratamiento) {
        this.diagnosticoTratamiento = diagnosticoTratamiento;
    }

    public Firma getFirma() {
        return firma;
    }

    public void setFirma(Firma firma) {
        this.firma = firma;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
}