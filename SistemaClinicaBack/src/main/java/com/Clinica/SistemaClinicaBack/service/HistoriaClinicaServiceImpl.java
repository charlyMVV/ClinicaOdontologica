package com.Clinica.SistemaClinicaBack.service;

import com.Clinica.SistemaClinicaBack.entity.*;
import com.Clinica.SistemaClinicaBack.exception.ResourceNotFoundException;
import com.Clinica.SistemaClinicaBack.repository.CatalogoEstatusHistoriaClinicaRepository;
import com.Clinica.SistemaClinicaBack.repository.CatalogoTipoHistoriaClinicaRepository;
import com.Clinica.SistemaClinicaBack.repository.HistoriaClinicaRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class HistoriaClinicaServiceImpl implements HistoriaClinicaService {

    private final HistoriaClinicaRepository historiaClinicaRepository;
    private final CatalogoTipoHistoriaClinicaRepository catalogoTipoHistoriaClinicaRepository;
    private final CatalogoEstatusHistoriaClinicaRepository catalogoEstatusHistoriaClinicaRepository;

    public HistoriaClinicaServiceImpl(
            HistoriaClinicaRepository historiaClinicaRepository,
            CatalogoTipoHistoriaClinicaRepository catalogoTipoHistoriaClinicaRepository,
            CatalogoEstatusHistoriaClinicaRepository catalogoEstatusHistoriaClinicaRepository) {

        this.historiaClinicaRepository = historiaClinicaRepository;
        this.catalogoTipoHistoriaClinicaRepository = catalogoTipoHistoriaClinicaRepository;
        this.catalogoEstatusHistoriaClinicaRepository = catalogoEstatusHistoriaClinicaRepository;
    }

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
}