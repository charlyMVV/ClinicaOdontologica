/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Clinica.SistemaClinicaBack.service;

import com.Clinica.SistemaClinicaBack.entity.Firma;
import com.Clinica.SistemaClinicaBack.entity.Paciente;
import java.util.List;

/**
 *
 * @author charly michel
 */
public interface FirmaService {

    Firma save(Firma firma);

    List<Firma> findAll();

    Firma findById(Integer id);

    void deleteById(Integer id);

    Firma update(Firma firma);

}
