package com.Clinica.SistemaClinicaBack.service;

import com.Clinica.SistemaClinicaBack.entity.Tutor;
import java.util.List;

/**
 *
 * @author charly michel
 */
public interface TutorService {
    
    Tutor save(Tutor tutor);
    
    List<Tutor> findAll();
    
    Tutor findById(Integer id);
    
    void deleteById(Integer id);
    
    Tutor update(Tutor tutor);
    
}
