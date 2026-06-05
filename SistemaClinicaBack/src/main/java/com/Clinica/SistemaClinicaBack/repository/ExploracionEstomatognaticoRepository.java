
package com.Clinica.SistemaClinicaBack.repository;

import com.Clinica.SistemaClinicaBack.entity.ExploracionEstomatognatico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ExploracionEstomatognaticoRepository extends JpaRepository<ExploracionEstomatognatico, Integer>{
    
    boolean existsByCurp(String curp);
    

    Optional<ExploracionEstomatognatico> findByCurp(String curp);
}
