
package com.Clinica.SistemaClinicaBack.service;

import com.Clinica.SistemaClinicaBack.entity.ExploracionEstomatognatico;
import com.Clinica.SistemaClinicaBack.exception.ResourceNotFoundException;
import com.Clinica.SistemaClinicaBack.repository.ExploracionEstomatognaticoRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author charly michel
 */
@Service
public class ExploracionEstomatognaticoServiceImpl implements ExploracionEstomatognaticoService{
    
    private final ExploracionEstomatognaticoRepository exploracionEstomatognaticoRepository;

    public ExploracionEstomatognaticoServiceImpl(ExploracionEstomatognaticoRepository exploracionEstomatognaticoRepository) {
        this.exploracionEstomatognaticoRepository = exploracionEstomatognaticoRepository;
    }
    


    @Override
    public ExploracionEstomatognatico save(ExploracionEstomatognatico exploracionEstomatognatico) {
        return exploracionEstomatognaticoRepository.save(exploracionEstomatognatico);
    }

    @Override
    public List<ExploracionEstomatognatico> findAll() {
        return exploracionEstomatognaticoRepository.findAll();
    }

    @Override
    public ExploracionEstomatognatico findById(Integer id) {
        return exploracionEstomatognaticoRepository.
                findById(id).
                orElseThrow(() -> new ResourceNotFoundException(
                "La exploracion Estomatognatica con id: "+ id + " No se ecuentra"));
    }

    @Override
    public void deleteById(Integer id) {
        exploracionEstomatognaticoRepository.deleteById(id);
    }

    @Override
    public ExploracionEstomatognatico update(ExploracionEstomatognatico exploracionEstomatognatico) {
        return exploracionEstomatognaticoRepository.save(exploracionEstomatognatico);
    }
    
    
    
    
    
}
