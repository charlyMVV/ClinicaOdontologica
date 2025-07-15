
package com.Clinica.SistemaClinicaBack.service;

import com.Clinica.SistemaClinicaBack.entity.AntecedentesNoPatologicos;
import com.Clinica.SistemaClinicaBack.exception.ResourceNotFoundException;
import com.Clinica.SistemaClinicaBack.repository.AntecedentesNoPatologicosRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AntecedentesNoPatologicosImpl implements AntecedentesNoPatologicosService{
    
    private final AntecedentesNoPatologicosRepository antecedentesNoPatologicosRepository;
    
    @Autowired
    public AntecedentesNoPatologicosImpl(AntecedentesNoPatologicosRepository antecedentesNoPatologicosRepository) {
        this.antecedentesNoPatologicosRepository = antecedentesNoPatologicosRepository;
    }

    @Override
    public AntecedentesNoPatologicos save(AntecedentesNoPatologicos antecedentesNoPatologicos) {
        return antecedentesNoPatologicosRepository.save(antecedentesNoPatologicos);
    }

    @Override
    public List<AntecedentesNoPatologicos> findAll() {
        return antecedentesNoPatologicosRepository.findAll();
    }

    @Override
    public AntecedentesNoPatologicos findById(Integer id) {
        return antecedentesNoPatologicosRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                "El antecedente con id " + id + " no se encuentra"));
    }

    @Override
    public void deleteById(Integer id) {
        antecedentesNoPatologicosRepository.deleteById(id);
    }

    @Override
    public AntecedentesNoPatologicos update(AntecedentesNoPatologicos antecedentesNoPatologicos) {
        return antecedentesNoPatologicosRepository.save(antecedentesNoPatologicos);
    }
    
    
    
}
