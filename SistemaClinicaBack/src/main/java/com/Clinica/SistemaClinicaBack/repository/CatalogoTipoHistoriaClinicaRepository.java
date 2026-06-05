package com.Clinica.SistemaClinicaBack.repository;
import com.Clinica.SistemaClinicaBack.entity.CatalogoTipoHistoriaClinica;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogoTipoHistoriaClinicaRepository
        extends JpaRepository<CatalogoTipoHistoriaClinica, Integer> {

    Optional<CatalogoTipoHistoriaClinica> findByClave(String clave);

}