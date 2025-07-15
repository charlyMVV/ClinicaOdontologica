
package com.Clinica.SistemaClinicaBack.service;

import com.Clinica.SistemaClinicaBack.entity.CabezaCuello;
import java.util.List;
import org.springframework.stereotype.Service;


public interface CabezaCuelloService  {
    
    CabezaCuello save(CabezaCuello cabezaCuello);
    
    List<CabezaCuello> findAll();
    
    CabezaCuello findById(Integer id);
    
    void deleteById(Integer id);
    
    CabezaCuello update(CabezaCuello cabezaCuello);
}
