package com.Clinica.SistemaClinicaBack.repository;

import com.Clinica.SistemaClinicaBack.entity.HistoriaClinica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HistoriaClinicaRepository
        extends JpaRepository<HistoriaClinica, Integer> {

    Optional<HistoriaClinica> findByPaciente_IdPaciente(Integer idPaciente);

    Optional<HistoriaClinica> findByPaciente_Curp(String curp);
}
