
package com.Clinica.SistemaClinicaBack.repository;

import com.Clinica.SistemaClinicaBack.entity.DiagnosticoTratamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 *
 * @author charly michel
 */
@Repository
public interface DiagnosticoTratamientoRepository extends JpaRepository<DiagnosticoTratamiento, Integer> {
        
        boolean existsByCurp(String curp);
    

    Optional<DiagnosticoTratamiento> findByCurp(String curp);
}
