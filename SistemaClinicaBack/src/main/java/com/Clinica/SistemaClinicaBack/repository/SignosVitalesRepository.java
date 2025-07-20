
package com.Clinica.SistemaClinicaBack.repository;

import com.Clinica.SistemaClinicaBack.entity.SignosVitales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignosVitalesRepository extends JpaRepository<SignosVitales, Integer>{
    boolean existsByCurp(String curp);
}
