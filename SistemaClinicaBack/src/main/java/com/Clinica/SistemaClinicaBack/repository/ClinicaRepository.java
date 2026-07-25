package com.Clinica.SistemaClinicaBack.repository;

import com.Clinica.SistemaClinicaBack.entity.Clinica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicaRepository extends JpaRepository<Clinica, Integer> {

    boolean existsByNombreClinicaIgnoreCase(String nombreClinica);
}