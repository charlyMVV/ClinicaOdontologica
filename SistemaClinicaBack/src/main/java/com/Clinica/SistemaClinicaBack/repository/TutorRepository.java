package com.Clinica.SistemaClinicaBack.repository;

import com.Clinica.SistemaClinicaBack.entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author charly michel
 */
@Repository
public interface TutorRepository extends JpaRepository<Tutor, Integer>{
    
    boolean existsByCurp(String curp);
}
