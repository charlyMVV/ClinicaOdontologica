
package com.Clinica.SistemaClinicaBack.service;

import com.Clinica.SistemaClinicaBack.entity.SignosVitales;
import java.util.List;


public interface SignosVitalesService {
    
    SignosVitales save(SignosVitales signosVitales);
    
    List<SignosVitales> findAll();
    
    SignosVitales findById(Integer id);
    
    void deleteById(Integer id);
    
    SignosVitales update(SignosVitales signosVitales);

    SignosVitales findByCurp(String curp);
    
}
