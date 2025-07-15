
package com.Clinica.SistemaClinicaBack.service;

import com.Clinica.SistemaClinicaBack.entity.ExploracionEstomatognatico;
import java.util.List;

/**
 *
 * @author charly michel
 */
public interface ExploracionEstomatognaticoService {
    
    ExploracionEstomatognatico save(ExploracionEstomatognatico exploracionEstomatognatico);
    
    List<ExploracionEstomatognatico> findAll();
    
    ExploracionEstomatognatico findById(Integer id);
    
    void deleteById(Integer id);
    
    ExploracionEstomatognatico update(ExploracionEstomatognatico exploracionEstomatognatico);
    
}
