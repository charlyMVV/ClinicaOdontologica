/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Clinica.SistemaClinicaBack.service;

import com.Clinica.SistemaClinicaBack.entity.FotosInicio;
import java.util.List;

/**
 *
 * @author charly michel
 */
public interface FotosInicioService {

    FotosInicio save(FotosInicio fotosInicio);

    List<FotosInicio> findAll();

    FotosInicio findById(Integer id);

    void deleteById(Integer id);

    FotosInicio update(FotosInicio fotosInicio);

}
