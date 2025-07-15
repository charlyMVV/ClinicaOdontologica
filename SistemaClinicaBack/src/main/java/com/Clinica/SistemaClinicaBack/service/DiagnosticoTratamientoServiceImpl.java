package com.Clinica.SistemaClinicaBack.service;

import com.Clinica.SistemaClinicaBack.entity.DiagnosticoTratamiento;
import com.Clinica.SistemaClinicaBack.exception.ResourceNotFoundException;
import com.Clinica.SistemaClinicaBack.repository.DiagnosticoTratamientoRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author charly michel
 */

@Service
public class DiagnosticoTratamientoServiceImpl implements DiagnosticoTratamientoService{
    
    private final DiagnosticoTratamientoRepository diagnosticoTratamientoRepository;

    public DiagnosticoTratamientoServiceImpl(DiagnosticoTratamientoRepository diagnosticoTratamientoRepository) {
        this.diagnosticoTratamientoRepository = diagnosticoTratamientoRepository;
    }
    
    

    @Override
    public DiagnosticoTratamiento save(DiagnosticoTratamiento diagnosticoTramiento) {
        return diagnosticoTratamientoRepository.save(diagnosticoTramiento);
    }

    @Override
    public List<DiagnosticoTratamiento> findAlll() {
        return diagnosticoTratamientoRepository.findAll();
    }

    @Override
    public DiagnosticoTratamiento findById(Integer id) {
       return diagnosticoTratamientoRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(
                "El Diagnostico con id: " + id + " no se encuentra"));
    }

    @Override
    public void deleteById(Integer id) {
        diagnosticoTratamientoRepository.deleteById(id);
    }

    @Override
    public DiagnosticoTratamiento update(DiagnosticoTratamiento diagnosticoTratamiento) {
        return diagnosticoTratamientoRepository.save(diagnosticoTratamiento);
    }
    
    
    
}
