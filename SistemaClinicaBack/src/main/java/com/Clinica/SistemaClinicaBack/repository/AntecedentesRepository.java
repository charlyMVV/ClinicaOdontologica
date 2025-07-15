package com.Clinica.SistemaClinicaBack.repository;

import com.Clinica.SistemaClinicaBack.entity.Antecedentes;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AntecedentesRepository extends JpaRepository<Antecedentes , Integer>{
    
     boolean existsByCurp(String curp);
    
}
