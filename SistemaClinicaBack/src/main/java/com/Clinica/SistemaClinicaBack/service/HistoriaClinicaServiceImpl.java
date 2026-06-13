package com.Clinica.SistemaClinicaBack.service;

import com.Clinica.SistemaClinicaBack.entity.*;
import com.Clinica.SistemaClinicaBack.exception.ResourceNotFoundException;
import com.Clinica.SistemaClinicaBack.repository.*;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HistoriaClinicaServiceImpl implements HistoriaClinicaService {

    private final HistoriaClinicaRepository historiaClinicaRepository;
    private final CatalogoTipoHistoriaClinicaRepository catalogoTipoHistoriaClinicaRepository;
    private final CatalogoEstatusHistoriaClinicaRepository catalogoEstatusHistoriaClinicaRepository;
    private final AntecedentesRepository antecedentesRepository;
    private final FotosInicioRepository fotosInicioRepository;

    @Override
    public HistoriaClinica save(HistoriaClinica historiaClinica) {
        return historiaClinicaRepository.save(historiaClinica);
    }

    @Override
    public HistoriaClinica update(HistoriaClinica historiaClinica) {
        return historiaClinicaRepository.save(historiaClinica);
    }

    @Override
    public HistoriaClinica findById(Integer id) {
        return historiaClinicaRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Historia clínica no encontrada con id: " + id));
    }

    @Override
    public List<HistoriaClinica> findAll() {
        return historiaClinicaRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        historiaClinicaRepository.deleteById(id);
    }

    @Override
    public HistoriaClinica findByPaciente(Integer idPaciente) {

        return historiaClinicaRepository
                .findByPaciente_IdPaciente(idPaciente)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe historia clínica para el paciente: "
                                        + idPaciente));
    }

    @Override
    public HistoriaClinica obtenerOCrearHistoriaClinica(
            Paciente paciente,
            Usuario usuario) {

        return historiaClinicaRepository
                .findByPaciente_IdPaciente(paciente.getIdPaciente())
                .orElseGet(() -> {

                    CatalogoTipoHistoriaClinica tipo =
                            catalogoTipoHistoriaClinicaRepository
                                    .findByClave("ODONTOLOGICA")
                                    .orElseThrow(() -> new ResourceNotFoundException(
                                            "No existe el tipo de historia clínica: ODONTOLOGICA"
                                    ));

                    CatalogoEstatusHistoriaClinica estatus =
                            catalogoEstatusHistoriaClinicaRepository
                                    .findByClave("BORRADOR")
                                    .orElseThrow(() -> new ResourceNotFoundException(
                                            "No existe el estatus de historia clínica: BORRADOR"
                                    ));

                    HistoriaClinica nuevaHistoria = new HistoriaClinica();

                    nuevaHistoria.setPaciente(paciente);
                    nuevaHistoria.setUsuario(usuario);
                    nuevaHistoria.setTipoHistoriaClinica(tipo);
                    nuevaHistoria.setEstatusHistoriaClinica(estatus);

                    return historiaClinicaRepository.save(nuevaHistoria);
                });
    }

    @Override
    public HistoriaClinica findByCurpPaciente(String curp) {
        return historiaClinicaRepository.findByPaciente_Curp(curp)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No existe historia clínica para la CURP: " + curp
                ));
    }

    @Override
    public List<HistoriaClinica> findAllByPaciente_Curp(String curp) {
        List<HistoriaClinica> historias =
                historiaClinicaRepository.findAllByPaciente_Curp(curp);

        if (historias.isEmpty()) {
            throw new ResourceNotFoundException(
                    "No existen historias clínicas para la CURP: " + curp
            );
        }

        return historias;
    }

    @Override
    public HistoriaClinica cambiarEstatus(Integer idHistoriaClinica, String claveEstatus) {

        HistoriaClinica historiaClinica = historiaClinicaRepository.findById(idHistoriaClinica)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No existe historia clínica con id: " + idHistoriaClinica
                ));

        CatalogoEstatusHistoriaClinica estatus =
                catalogoEstatusHistoriaClinicaRepository.findByClave(claveEstatus)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "No existe el estatus de historia clínica: " + claveEstatus
                        ));

        historiaClinica.setEstatusHistoriaClinica(estatus);

        return historiaClinicaRepository.save(historiaClinica);
    }

    @Override
    public void validarHistoriaCompleta(Integer idHistoriaClinica) {

        HistoriaClinica hc = findById(idHistoriaClinica);

        if (hc.getPaciente() == null) {
            throw new IllegalStateException("Faltan los datos del paciente.");
        }

        if (hc.getAntecedentesNoPatologicos() == null) {
            throw new IllegalStateException("Faltan antecedentes no patológicos.");
        }

        if (hc.getSignosVitales() == null) {
            throw new IllegalStateException("Faltan signos vitales.");
        }

        if (hc.getCabezaCuello() == null) {
            throw new IllegalStateException("Falta exploración cabeza y cuello.");
        }

        if (hc.getExploracionEstomatognatico() == null) {
            throw new IllegalStateException("Falta exploración estomatognática.");
        }

        if (hc.getTejidosBlandos() == null) {
            throw new IllegalStateException("Falta exploración de tejidos blandos.");
        }

        if (hc.getDiagnosticoTratamiento() == null) {
            throw new IllegalStateException("Falta diagnóstico y tratamiento.");
        }
        List<Antecedentes> antecedentes =
                antecedentesRepository.findByHistoriaClinica_IdHistoriaClinica(
                        idHistoriaClinica);

        if (antecedentes.isEmpty()) {
            throw new IllegalStateException(
                    "Debe capturar antecedentes."
            );
        }

        List<FotosInicio> fotos =
                fotosInicioRepository.findByHistoriaClinica_IdHistoriaClinica(
                        idHistoriaClinica);

        if (fotos.isEmpty()) {
            throw new IllegalStateException(
                    "Debe capturar al menos una fotografía."
            );
        }
    }

}