/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Clinica.SistemaClinicaBack.service;

import com.Clinica.SistemaClinicaBack.entity.DiagnosticoTratamiento;
import java.util.List;

/**
 *
 * @author charly michel
 */
public interface DiagnosticoTratamientoService {
    
    DiagnosticoTratamiento save(DiagnosticoTratamiento diagnosticoTramiento);
    
    List<DiagnosticoTratamiento> findAlll();
    
    DiagnosticoTratamiento findById(Integer id);
    
    void deleteById(Integer id);
    
    DiagnosticoTratamiento update(DiagnosticoTratamiento diagnosticoTratamiento);
}
