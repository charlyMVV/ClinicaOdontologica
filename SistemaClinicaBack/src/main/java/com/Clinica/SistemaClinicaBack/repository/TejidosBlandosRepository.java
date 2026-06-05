package com.Clinica.SistemaClinicaBack.repository;

import com.Clinica.SistemaClinicaBack.entity.TejidosBlandos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
/**
 *
 * @author charly michel
 */
public interface TejidosBlandosRepository  extends JpaRepository<TejidosBlandos, Integer>{
    
    boolean existsByCurp(String Curp);
    

    Optional<TejidosBlandos> findByCurp(String curp);
}
