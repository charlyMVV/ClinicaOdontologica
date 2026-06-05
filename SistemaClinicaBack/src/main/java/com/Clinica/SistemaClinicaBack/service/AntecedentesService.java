
package com.Clinica.SistemaClinicaBack.service;

import com.Clinica.SistemaClinicaBack.entity.Antecedentes;
import java.util.List;


public interface AntecedentesService {
    
    Antecedentes save(Antecedentes antecedentes);
    
    List<Antecedentes> findAll();
    
    Antecedentes findById(Integer id);
    
    void deleteById(Integer id);
    
    Antecedentes update(Antecedentes antecedentes);

    Antecedentes upsert(Antecedentes antecedentes);
    
}
