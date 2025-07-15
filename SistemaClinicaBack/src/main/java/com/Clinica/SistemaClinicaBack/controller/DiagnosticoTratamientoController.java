package com.Clinica.SistemaClinicaBack.controller;

import com.Clinica.SistemaClinicaBack.entity.DiagnosticoTratamiento;
import com.Clinica.SistemaClinicaBack.repository.DiagnosticoTratamientoRepository;
import com.Clinica.SistemaClinicaBack.service.DiagnosticoTratamientoService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author charly michel
 */

@RestController
@RequestMapping("/api/diagnosticotratamiento")
@CrossOrigin("http://localhost:4200")
public class DiagnosticoTratamientoController {
    
    private final DiagnosticoTratamientoService diagnosticoTratamientoService;
    private final DiagnosticoTratamientoRepository diagnosticoTratamientoRepository;

    public DiagnosticoTratamientoController(DiagnosticoTratamientoService diagnosticoTratamientoService, DiagnosticoTratamientoRepository diagnosticoTratamientoRepository) {
        this.diagnosticoTratamientoService = diagnosticoTratamientoService;
        this.diagnosticoTratamientoRepository = diagnosticoTratamientoRepository;
    }
    
    
    //localhost:8080/api/diagnosticotratamiento
    @GetMapping
    public List<DiagnosticoTratamiento> findAll(){
        return diagnosticoTratamientoRepository.findAll();
    }
    
    @GetMapping("/{idDiagnostico}")
    public DiagnosticoTratamiento findById(@PathVariable("idDiagnostico")Integer id){
        return diagnosticoTratamientoService.findById(id);      
    }
    
    @DeleteMapping("/{idDiagnostico}")
    public void deleteById(@PathVariable("idDiagnostico")Integer id){
        diagnosticoTratamientoService.deleteById(id);
    }
    
    @PostMapping
    public ResponseEntity<DiagnosticoTratamiento> save(@RequestBody DiagnosticoTratamiento diagnosticoTratamiento){
        return ResponseEntity.ok(diagnosticoTratamientoService.save(diagnosticoTratamiento));
    }

    @GetMapping("/existen/{curp}")
    public ResponseEntity<Boolean> existenDiagnosticoTratamientoPorCurp(@PathVariable String curp) {  
        boolean existen = diagnosticoTratamientoRepository.existsByCurp(curp);
        return ResponseEntity.ok(existen);
    }    
    
    @PutMapping
    public DiagnosticoTratamiento updateDiagnosticoTratamiento(@RequestBody DiagnosticoTratamiento diagnosticoTratamiento){
    
        DiagnosticoTratamiento diagnosticoTratamientodb = diagnosticoTratamientoService.findById(diagnosticoTratamiento.getIdDiagnostico());
        
        if (!diagnosticoTratamientodb.getCurp().equals(diagnosticoTratamiento.getCurp())) {
            throw new IllegalArgumentException("La CURP  del los diagnosticos no se puede modificar.");
        }
        
        diagnosticoTratamientodb.setInterpretacionRx(diagnosticoTratamiento.getInterpretacionRx());
        diagnosticoTratamientodb.setDiagnostico(diagnosticoTratamiento.getDiagnostico());
        diagnosticoTratamientodb.setResumenTratamiento(diagnosticoTratamiento.getResumenTratamiento());
        
        return diagnosticoTratamientoService.update(diagnosticoTratamientodb);
    }
    
    
}
