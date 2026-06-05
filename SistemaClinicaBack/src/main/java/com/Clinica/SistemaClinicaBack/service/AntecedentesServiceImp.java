package com.Clinica.SistemaClinicaBack.service;

import com.Clinica.SistemaClinicaBack.entity.Antecedentes;
import com.Clinica.SistemaClinicaBack.entity.HistoriaClinica;
import com.Clinica.SistemaClinicaBack.exception.ResourceNotFoundException;
import com.Clinica.SistemaClinicaBack.repository.AntecedentesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AntecedentesServiceImp implements AntecedentesService {

    private final AntecedentesRepository antecedentesRepository;
    private final HistoriaClinicaService historiaClinicaService;

    public AntecedentesServiceImp(
            AntecedentesRepository antecedentesRepository,
            HistoriaClinicaService historiaClinicaService) {

        this.antecedentesRepository = antecedentesRepository;
        this.historiaClinicaService = historiaClinicaService;
    }

    @Override
    public Antecedentes save(Antecedentes antecedentes) {
        return antecedentesRepository.save(antecedentes);
    }

    @Override
    public List<Antecedentes> findAll() {
        return antecedentesRepository.findAll();
    }

    @Override
    public Antecedentes findById(Integer id) {
        return antecedentesRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "El antecedente con id " + id + " no se encuentra"));
    }

    @Override
    public void deleteById(Integer id) {
        antecedentesRepository.deleteById(id);
    }

    @Override
    public Antecedentes update(Antecedentes antecedentes) {
        return antecedentesRepository.save(antecedentes);
    }

    @Override
    public Antecedentes upsert(Antecedentes antecedentes) {

        HistoriaClinica historiaClinica =
                historiaClinicaService.findByCurpPaciente(antecedentes.getCurp());

        Antecedentes existente = antecedentesRepository
                .findByCurpAndDescripcionAntecedentesAndTipoAntecedentes(
                        antecedentes.getCurp(),
                        antecedentes.getDescripcionAntecedentes(),
                        antecedentes.getTipoAntecedentes()
                )
                .orElse(null);

        if (existente == null) {
            antecedentes.setHistoriaClinica(historiaClinica);
            return antecedentesRepository.save(antecedentes);
        }

        existente.setRespuesta(antecedentes.getRespuesta());
        existente.setDetalle(antecedentes.getDetalle());
        existente.setHistoriaClinica(historiaClinica);

        return antecedentesRepository.save(existente);
    }
}