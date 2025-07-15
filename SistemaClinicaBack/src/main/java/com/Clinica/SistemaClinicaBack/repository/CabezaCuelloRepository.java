
package com.Clinica.SistemaClinicaBack.repository;

import com.Clinica.SistemaClinicaBack.entity.CabezaCuello;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CabezaCuelloRepository extends JpaRepository<CabezaCuello, Integer> {
    
    boolean existsByCurp(String curp);
    
}
