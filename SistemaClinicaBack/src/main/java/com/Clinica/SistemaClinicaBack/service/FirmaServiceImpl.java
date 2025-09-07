/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Clinica.SistemaClinicaBack.service;

import com.Clinica.SistemaClinicaBack.entity.Firma;
import com.Clinica.SistemaClinicaBack.exception.ResourceNotFoundException;
import com.Clinica.SistemaClinicaBack.repository.FirmaRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author charly michel
 */
@Service
public class FirmaServiceImpl implements FirmaService{
    
    private final FirmaRepository firmaRepository;

    public FirmaServiceImpl(FirmaRepository firmaRepository) {
        this.firmaRepository = firmaRepository;
    }

    @Override
    public Firma save(Firma firma) {
        return firmaRepository.save(firma);
    }

    @Override
    public List<Firma> findAll() {
         return firmaRepository.findAll();
    }

    @Override
    public Firma findById(Integer id) {
            Firma paciente = firmaRepository.findById(id).orElseThrow(
        () ->{
                throw new ResourceNotFoundException("El paciente con matricula "+id+" no se encuentra");
            }  
        );       
        return paciente;
    }

    @Override
    public void deleteById(Integer id) {
         firmaRepository.deleteById(id);
    }

    @Override
    public Firma update(Firma firma) {
          return firmaRepository.save(firma);
    }
    
    
    
}
