package com.Clinica.SistemaClinicaBack.service;

import com.Clinica.SistemaClinicaBack.entity.TejidosBlandos;
import com.Clinica.SistemaClinicaBack.exception.ResourceNotFoundException;
import com.Clinica.SistemaClinicaBack.repository.TejidosBlandosRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author charly michel
 */

@Service
public class TejidosBlandosServiceImpl implements TejidosBlandosService{
    
    private final TejidosBlandosRepository tejidosBlandosRepository;

    @Autowired
    public TejidosBlandosServiceImpl(TejidosBlandosRepository tejidosBlandosRepository) {
        this.tejidosBlandosRepository = tejidosBlandosRepository;
    }

    @Override
    public TejidosBlandos save(TejidosBlandos tejidosBlandos) {
        return tejidosBlandosRepository.save(tejidosBlandos);
    }

    @Override
    public List<TejidosBlandos> findAll() {
        return tejidosBlandosRepository.findAll();
    }

    @Override
    public TejidosBlandos findById(Integer id) {
        return tejidosBlandosRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(
                "El tejido blando con id "+ id + " no se encuentra"));
    }

    @Override
    public void deleteById(Integer id) {
        tejidosBlandosRepository.deleteById(id);
    }

    @Override
    public TejidosBlandos update(TejidosBlandos tejidosBlandos) {
        return tejidosBlandosRepository.save(tejidosBlandos);
    }
    
    
    
}
