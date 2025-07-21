/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Clinica.SistemaClinicaBack.controller;

import com.Clinica.SistemaClinicaBack.entity.DiagnosticoTratamiento;
import com.Clinica.SistemaClinicaBack.entity.EvolucionPaciente;
import com.Clinica.SistemaClinicaBack.repository.EvolucionPacienteRepository;
import com.Clinica.SistemaClinicaBack.service.EvolucionPacienteService;
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
@RequestMapping("/api/evolucionpaciente")
@CrossOrigin("http://localhost:4200")
public class EvolucionPacienteController {

    private final EvolucionPacienteRepository evolucionPacienteRepository;
    private final EvolucionPacienteService evolucionPacienteService;

    public EvolucionPacienteController(EvolucionPacienteRepository evolucionPacienteRepository, EvolucionPacienteService evolucionPacienteService) {
        this.evolucionPacienteRepository = evolucionPacienteRepository;
        this.evolucionPacienteService = evolucionPacienteService;
    }

    //localhost:8080/api/diagnosticotratamiento
    @GetMapping
    public List<EvolucionPaciente> findAll() {
        return evolucionPacienteRepository.findAll();
    }

    @GetMapping("/{idControlEvolucion}")
    public EvolucionPaciente findById(@PathVariable("idControlEvolucion") Integer id) {
        return evolucionPacienteService.findById(id);
    }

    @DeleteMapping("/{idControlEvolucion}")
    public void deleteById(@PathVariable("idControlEvolucion") Integer id) {
        evolucionPacienteService.deleteById(id);
    }

    @PostMapping
    public ResponseEntity<EvolucionPaciente> save(@RequestBody EvolucionPaciente evolucionPaciente) {
        return ResponseEntity.ok(evolucionPacienteService.save(evolucionPaciente));
    }

    @GetMapping("/existen/{curp}")
    public ResponseEntity<Boolean> existenEvolucionPacientePorCurp(@PathVariable String curp) {
        boolean existen = evolucionPacienteRepository.existsByCurp(curp);
        return ResponseEntity.ok(existen);
    }
    
    @PutMapping
    public EvolucionPaciente updateEvolucionPaciente(@RequestBody EvolucionPaciente evolucionPaciente){
    
        EvolucionPaciente evolucionPacientedb = evolucionPacienteService.findById(evolucionPaciente.getIdControlEvolucion());
        
        if (!evolucionPacientedb.getCurp().equals(evolucionPaciente.getCurp())) {
            throw new IllegalArgumentException("La CURP  de la evolucion del paciente no se puede modificar.");
        }
        
        evolucionPacientedb.setFecha(evolucionPaciente.getFecha());
        evolucionPacientedb.setComentarioControl(evolucionPaciente.getComentarioControl());
        
        return evolucionPacienteService.update(evolucionPacientedb);
    }
    
}
