
package com.Clinica.SistemaClinicaBack.repository;

import com.Clinica.SistemaClinicaBack.entity.FotosInicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author charly michel
 */
@Repository
public interface FotosInicioRepository extends JpaRepository<FotosInicio, Integer>{
    
     boolean existsByCurp(String curp);
    
}
