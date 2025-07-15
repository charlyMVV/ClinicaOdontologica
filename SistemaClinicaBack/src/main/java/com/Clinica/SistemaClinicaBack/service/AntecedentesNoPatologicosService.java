/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Clinica.SistemaClinicaBack.service;

import com.Clinica.SistemaClinicaBack.entity.AntecedentesNoPatologicos;
import java.util.List;

/**
 *
 * @author charly michel
 */
public interface AntecedentesNoPatologicosService {
    
    AntecedentesNoPatologicos save(AntecedentesNoPatologicos antecedentesNoPatologicos);
    
    List<AntecedentesNoPatologicos> findAll();
    
    AntecedentesNoPatologicos findById(Integer id);
    
    void deleteById(Integer id);
    
    AntecedentesNoPatologicos update(AntecedentesNoPatologicos antecedentesNoPatologicos);
    
}
