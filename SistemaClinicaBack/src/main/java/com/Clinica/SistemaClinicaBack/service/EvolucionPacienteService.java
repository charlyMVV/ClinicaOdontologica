/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Clinica.SistemaClinicaBack.service;

import com.Clinica.SistemaClinicaBack.entity.EvolucionPaciente;
import java.util.List;

/**
 *
 * @author charly michel
 */
public interface EvolucionPacienteService{
    
    EvolucionPaciente save(EvolucionPaciente evolucionPaciente);
    
    List<EvolucionPaciente> findAll();
    
    EvolucionPaciente findById(Integer id);
    
    void deleteById(Integer id);
    
    EvolucionPaciente update(EvolucionPaciente evolucionPaciente);
    
    
    
    
}
