package com.Clinica.SistemaClinicaBack.service;

import com.Clinica.SistemaClinicaBack.entity.CabezaCuello;
import com.Clinica.SistemaClinicaBack.exception.ResourceNotFoundException;
import com.Clinica.SistemaClinicaBack.repository.CabezaCuelloRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author charly michel
 */
@Service
public class CabezaCuelloServiceImpl implements CabezaCuelloService {

    private final CabezaCuelloRepository cabezaCuelloRepository;
    
    @Autowired
    public CabezaCuelloServiceImpl(CabezaCuelloRepository cabezaCuelloRepository) {
        this.cabezaCuelloRepository = cabezaCuelloRepository;
    }

   
    @Override
    public CabezaCuello save(CabezaCuello cabezaCuello) {
        return cabezaCuelloRepository.save(cabezaCuello);
    }

    @Override
    public List<CabezaCuello> findAll() {
        return cabezaCuelloRepository.findAll();
    }

    @Override
    public CabezaCuello findById(Integer id) {
        return cabezaCuelloRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                "La exploracion cabeza cuello con id: " + id + " no se encuentra"));
    }

    @Override
    public void deleteById(Integer id) {
        cabezaCuelloRepository.deleteById(id);
    }

    @Override
    public CabezaCuello update(CabezaCuello cabezaCuello) {
        return cabezaCuelloRepository.save(cabezaCuello);
    }

}
