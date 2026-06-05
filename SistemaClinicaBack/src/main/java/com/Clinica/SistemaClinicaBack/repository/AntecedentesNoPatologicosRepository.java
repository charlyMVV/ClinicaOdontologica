
package com.Clinica.SistemaClinicaBack.repository;

import com.Clinica.SistemaClinicaBack.entity.AntecedentesNoPatologicos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AntecedentesNoPatologicosRepository extends JpaRepository<AntecedentesNoPatologicos, Integer>{
    
    boolean existsByCurp(String curp);

    Optional<AntecedentesNoPatologicos> findByCurp(String curp);
}
