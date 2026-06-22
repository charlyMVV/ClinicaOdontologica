package com.Clinica.SistemaClinicaBack.repository;

import com.Clinica.SistemaClinicaBack.entity.Antecedentes;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AntecedentesRepository extends JpaRepository<Antecedentes , Integer>{

     boolean existsByCurp(String curp);

    Optional<Antecedentes> findByCurpAndDescripcionAntecedentesAndTipoAntecedentes(
            String curp,
            String descripcionAntecedentes,
            String tipoAntecedentes
    );

    List<Antecedentes> findByCurp(String curp);

    List<Antecedentes> findByHistoriaClinica_IdHistoriaClinica(Integer idHistoriaClinica);

}
