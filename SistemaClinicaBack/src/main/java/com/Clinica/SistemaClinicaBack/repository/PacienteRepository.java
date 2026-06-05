
package com.Clinica.SistemaClinicaBack.repository;

import com.Clinica.SistemaClinicaBack.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {


    Optional<Paciente> findByCurp(String curp);

    boolean existsByCurp(String curp);
}
