/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Clinica.SistemaClinicaBack.service;

import com.Clinica.SistemaClinicaBack.entity.FotosInicio;
import com.Clinica.SistemaClinicaBack.exception.ResourceNotFoundException;
import com.Clinica.SistemaClinicaBack.repository.FotosInicioRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author charly michel
 */

@Service
public class FotosInicioServiceImpl implements FotosInicioService{
    private final FotosInicioRepository fotosInicioRepository;

    public FotosInicioServiceImpl(FotosInicioRepository fotosInicioRepository) {
        this.fotosInicioRepository = fotosInicioRepository;
    }

    @Override
    public FotosInicio save(FotosInicio fotosInicio) {
        return fotosInicioRepository.save(fotosInicio);
    }

    @Override
    public List<FotosInicio> findAll() {
        return fotosInicioRepository.findAll();
    }

    @Override
    public FotosInicio findById(Integer id) {
         FotosInicio paciente = fotosInicioRepository.findById(id).orElseThrow(
        () ->{
                throw new ResourceNotFoundException("El paciente con matricula "+id+" no se encuentra");
            }  
        );       
        return paciente;
    }

    @Override
    public void deleteById(Integer id) {
        fotosInicioRepository.deleteById(id);
    }

    @Override
    public FotosInicio update(FotosInicio fotosInicio) {
        return fotosInicioRepository.save(fotosInicio);
    }
    
    
}
