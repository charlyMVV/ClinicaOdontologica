package com.Clinica.SistemaClinicaBack.controller;

import com.Clinica.SistemaClinicaBack.entity.Antecedentes;
import com.Clinica.SistemaClinicaBack.repository.AntecedentesRepository;
import com.Clinica.SistemaClinicaBack.service.AntecedentesService;
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

@RestController
@RequestMapping("/api/antecedentes")
@CrossOrigin(origins = "http://localhost:4200")
public class AntecedentesController {

    private final AntecedentesService antecedentesService;
    private final AntecedentesRepository antecedentesRepository;

    public AntecedentesController(AntecedentesService antecedentesService, AntecedentesRepository antecedentesRepository) {
        this.antecedentesService = antecedentesService;
        this.antecedentesRepository = antecedentesRepository;
    }

    //localhost:8080/api/antecedentes
    @GetMapping
    public List<Antecedentes> findAll() {
        return antecedentesService.findAll();
    }

    //localhost:8080/api/antecedentes/"id"
    @GetMapping("/{idAntecedentes}")
    public Antecedentes findById(@PathVariable("idAntecedentes") Integer id) {
        return antecedentesService.findById(id);
    }

    //localhost:8080/api/antecedentes/"id"
    @DeleteMapping("/{idAntecedentes}")
    public void deleteById(@PathVariable("idAntecedentes") Integer id) {
        antecedentesService.deleteById(id);
    }

    //localhost:8080/api/antecedentes
    @PutMapping
    public Antecedentes updateAntecedentes(@RequestBody Antecedentes antecedentes) {

        Antecedentes antecedentesdb = antecedentesService.findById(antecedentes.getIdAntecedentes());

        if (!antecedentesdb.getCurp().equals(antecedentes.getCurp())) {
            throw new IllegalArgumentException("La CURP  del los antecedentes no se puede modificar.");
        }

        antecedentesdb.setDescripcionAntecedentes(antecedentes.getDescripcionAntecedentes());
        antecedentesdb.setTipoAntecedentes(antecedentes.getTipoAntecedentes());
        antecedentesdb.setRespuesta(antecedentes.getRespuesta());

        return antecedentesService.update(antecedentesdb);
    }

    
    @PostMapping
    public ResponseEntity<Antecedentes> save(@RequestBody Antecedentes antecedentes) {
        return ResponseEntity.ok(antecedentesService.save(antecedentes));
    }

    @GetMapping("/existen/{curp}")
    public ResponseEntity<Boolean> existenAntecedentesPorCurp(@PathVariable String curp) {  
        boolean existen = antecedentesRepository.existsByCurp(curp);
        return ResponseEntity.ok(existen);
    }

}
