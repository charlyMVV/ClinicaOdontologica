package com.Clinica.SistemaClinicaBack.repository;

import com.Clinica.SistemaClinicaBack.entity.TejidosBlandos;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author charly michel
 */
public interface TejidosBlandosRepository  extends JpaRepository<TejidosBlandos, Integer>{
    
    boolean existsByCurp(String Curp);
    
}
