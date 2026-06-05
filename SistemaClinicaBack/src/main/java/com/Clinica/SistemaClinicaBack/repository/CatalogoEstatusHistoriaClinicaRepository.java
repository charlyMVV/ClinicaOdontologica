package com.Clinica.SistemaClinicaBack.repository;

import com.Clinica.SistemaClinicaBack.entity.CatalogoEstatusHistoriaClinica;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogoEstatusHistoriaClinicaRepository
        extends JpaRepository<CatalogoEstatusHistoriaClinica, Integer> {

    Optional<CatalogoEstatusHistoriaClinica> findByClave(String clave);

}
