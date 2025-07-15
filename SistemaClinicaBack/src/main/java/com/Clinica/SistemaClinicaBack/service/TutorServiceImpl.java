package com.Clinica.SistemaClinicaBack.service;

import com.Clinica.SistemaClinicaBack.entity.Tutor;
import com.Clinica.SistemaClinicaBack.exception.ResourceNotFoundException;
import com.Clinica.SistemaClinicaBack.repository.TutorRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author charly michel
 */
@Service
public class TutorServiceImpl implements TutorService{
    
    private final TutorRepository tutorRepository;

    public TutorServiceImpl(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }

    @Override
    public Tutor save(Tutor tutor) {
        return tutorRepository.save(tutor);
    }

    @Override
    public List<Tutor> findAll() {
        return tutorRepository.findAll();
    }

    @Override
    public Tutor findById(Integer id) {
        return tutorRepository.
                findById(id).
                orElseThrow(() -> new ResourceNotFoundException(
                "El tutor con id: "+ id+ "no se encuentra"));
    }

    @Override
    public void deleteById(Integer id) {
        tutorRepository.deleteById(id);
    }

    @Override
    public Tutor update(Tutor tutor) {
        return tutorRepository.save(tutor);
    }
    
    
    
    
}
