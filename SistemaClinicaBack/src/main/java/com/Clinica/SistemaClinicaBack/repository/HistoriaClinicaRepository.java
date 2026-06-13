package com.Clinica.SistemaClinicaBack.repository;

import com.Clinica.SistemaClinicaBack.entity.HistoriaClinica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HistoriaClinicaRepository
        extends JpaRepository<HistoriaClinica, Integer> {

    Optional<HistoriaClinica> findByPaciente_IdPaciente(Integer idPaciente);

    Optional<HistoriaClinica> findByPaciente_Curp(String curp);

    List<HistoriaClinica> findByEstatusHistoriaClinica_Clave(String clave);

    List<HistoriaClinica> findByUsuario_Matricula(String matricula);

    List<HistoriaClinica> findAllByPaciente_Curp(String curp);

    List<HistoriaClinica> findByPaciente_CurpAndEstatusHistoriaClinica_ClaveIn(
            String curp,
            List<String> claves
    );
}
