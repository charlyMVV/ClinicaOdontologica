/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Clinica.SistemaClinicaBack.repository;

import com.Clinica.SistemaClinicaBack.entity.Firma;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/**
 *
 * @author charly michel
 */
@Repository
public interface FirmaRepository extends JpaRepository<Firma, Integer> {

    boolean existsByCurp(String curp);

    List<Firma> findByCurp(String curp);

    Optional<Firma> findFirstByCurp(String curp);
}
