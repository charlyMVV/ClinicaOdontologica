package com.Clinica.SistemaClinicaBack.service;

import com.Clinica.SistemaClinicaBack.entity.EvolucionPaciente;
import com.Clinica.SistemaClinicaBack.exception.ResourceNotFoundException;
import com.Clinica.SistemaClinicaBack.repository.EvolucionPacienteRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author charly michel
 */
@Service
public class EvolucionPacienteServiceImpl  implements EvolucionPacienteService{
    
    private final EvolucionPacienteRepository evolucionPacienteRepository;

    public EvolucionPacienteServiceImpl(EvolucionPacienteRepository evolucionPacienteRepository) {
        this.evolucionPacienteRepository = evolucionPacienteRepository;
    }

    @Override
    public EvolucionPaciente save(EvolucionPaciente evolucionPaciente) {
        return evolucionPacienteRepository.save(evolucionPaciente);
    }

    @Override
    public List<EvolucionPaciente> findAll() {
        return evolucionPacienteRepository.findAll();
    }

    @Override
    public EvolucionPaciente findById(Integer id) {
        return evolucionPacienteRepository.
                findById(id).
                orElseThrow(() -> new ResourceNotFoundException(
                "La evolucion del paciente con id: "+ id + " No se ecuentra"));
    }

    @Override
    public void deleteById(Integer id) {
        evolucionPacienteRepository.deleteById(id);
    }

    @Override
    public EvolucionPaciente update(EvolucionPaciente evolucionPaciente) {
        return evolucionPacienteRepository.save(evolucionPaciente);
    }
    
    
    
}
