package com.Clinica.SistemaClinicaBack.service;

import com.Clinica.SistemaClinicaBack.entity.TejidosBlandos;
import java.util.List;

/**
 *
 * @author charly michel
 */

public interface TejidosBlandosService {

    TejidosBlandos save(TejidosBlandos tejidosBlandos);
    
    List<TejidosBlandos> findAll();
    
    TejidosBlandos findById(Integer id);
    
    void deleteById(Integer id);
    
    TejidosBlandos update(TejidosBlandos tejidosBlandos);
    
    
}
