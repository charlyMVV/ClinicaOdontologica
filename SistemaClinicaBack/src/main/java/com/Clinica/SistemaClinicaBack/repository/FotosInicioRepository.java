
package com.Clinica.SistemaClinicaBack.repository;

import com.Clinica.SistemaClinicaBack.entity.FotosInicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 *
 * @author charly michel
 */
@Repository
public interface FotosInicioRepository extends JpaRepository<FotosInicio, Integer>{
    
     boolean existsByCurp(String curp);

    List<FotosInicio> findByCurp(String curp);

    void deleteByCurp(String curp);

    List<FotosInicio> findByHistoriaClinica_IdHistoriaClinica(Integer idHistoriaClinica);
}
