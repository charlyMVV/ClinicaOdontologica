
package com.Clinica.SistemaClinicaBack.service;

import com.Clinica.SistemaClinicaBack.entity.Paciente;
import com.Clinica.SistemaClinicaBack.exception.ResourceNotFoundException;
import com.Clinica.SistemaClinicaBack.repository.PacienteRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PacienteServiceImp implements PacienteService{
    
    private final PacienteRepository pacienterepository;

    public PacienteServiceImp(PacienteRepository pacienterepository) {
        this.pacienterepository = pacienterepository;
    }

    @Override
    public Paciente save(Paciente paciente) {
        return pacienterepository.save(paciente);
    }

    @Override
    public List<Paciente> findAll() {
        return pacienterepository.findAll();
    }

    @Override
    public Paciente findById(Integer id) {
        Paciente paciente = pacienterepository.findById(id).orElseThrow(
        () ->{
                throw new ResourceNotFoundException("El paciente con matricula "+id+" no se encuentra");
            }  
        );       
        return paciente;
    }

    @Override
    public void deleteById(Integer id) {
         pacienterepository.deleteById(id);
    }

    @Override
    public Paciente update(Paciente paciente) {
        return pacienterepository.save(paciente);
    }

    @Override
    public Paciente findByCURP(String curp) {
        return pacienterepository.findByCurp(curp)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No existe paciente con CURP: " + curp
                ));
    }

    @Override
    public boolean existsByCURP(String curp) {
        return pacienterepository.existsByCurp(curp);
    }
    
}
