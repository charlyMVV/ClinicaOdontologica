package com.Clinica.SistemaClinicaBack.service;

import com.Clinica.SistemaClinicaBack.entity.SignosVitales;
import com.Clinica.SistemaClinicaBack.exception.ResourceNotFoundException;
import com.Clinica.SistemaClinicaBack.repository.SignosVitalesRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignosVitalesImpl implements SignosVitalesService {

    private final SignosVitalesRepository signosVitalesRepository;

    @Autowired
    public SignosVitalesImpl(SignosVitalesRepository signosVitalesRepository) {
        this.signosVitalesRepository = signosVitalesRepository;
    }

    @Override
    public SignosVitales save(SignosVitales signosVitales) {
        return signosVitalesRepository.save(signosVitales);
    }

    @Override
    public List<SignosVitales> findAll() {
        return signosVitalesRepository.findAll();
    }

    @Override
    public SignosVitales findById(Integer id) {

        return signosVitalesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "El Signo vital con id: " + id + " no se encuentra"));

    }

    @Override
    public void deleteById(Integer id) {
        if (!signosVitalesRepository.existsById(id)) {
            throw new ResourceNotFoundException("El Signo vital con id: " + id + " no se encuentra");
        }
        signosVitalesRepository.deleteById(id);
    }

    @Override
    public SignosVitales update(SignosVitales signosVitales) {
        return signosVitalesRepository.save(signosVitales);
    }

    @Override
    public SignosVitales findByCurp(String curp) {
        return signosVitalesRepository.findByCurp(curp)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No existen signos vitales para la CURP: " + curp
                ));
    }
}
