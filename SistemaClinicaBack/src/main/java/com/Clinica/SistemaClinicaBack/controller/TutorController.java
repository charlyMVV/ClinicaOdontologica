package com.Clinica.SistemaClinicaBack.controller;

import com.Clinica.SistemaClinicaBack.entity.Tutor;
import com.Clinica.SistemaClinicaBack.repository.TutorRepository;
import com.Clinica.SistemaClinicaBack.service.TutorService;
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
@RequestMapping("/api/tutor")
@CrossOrigin(origins = "http://localhost:4200")
public class TutorController {
    
    public final TutorService tutorService;
    public final TutorRepository tutorRepository;

    public TutorController(TutorService tutorService, TutorRepository tutorRepository) {
        this.tutorService = tutorService;
        this.tutorRepository = tutorRepository;
    }
    
    //localhost:8080/api/tutor
    @GetMapping
    public List<Tutor> findAll() {
        return tutorService.findAll();
    }
    
    @GetMapping("/{idTutor}")
    public Tutor findById(@PathVariable("idTutor")Integer id){
        return tutorService.findById(id);
    }
    
    @DeleteMapping("/{idTutor}")
    public void deleteById(@PathVariable("idTutor")Integer id){
        tutorService.deleteById(id);
    }
    
    @PostMapping
    public ResponseEntity<Tutor> save(@RequestBody Tutor tutor){
        return ResponseEntity.ok(tutorService.save(tutor));
    }
    
    @GetMapping("/existen/{curp}")
    public ResponseEntity<Boolean> existenTutorPorCurp(@PathVariable String curp) {  
        boolean existen = tutorRepository.existsByCurp(curp);
        return ResponseEntity.ok(existen);
    }
    
    @PutMapping
    public Tutor updateTutor(@RequestBody Tutor tutor){
        
        Tutor tutordb = tutorService.findById(tutor.getIdTutor());
        
        if (!tutordb.getCurp().equals(tutor.getCurp())) {
            throw new IllegalArgumentException("La CURP  del los antecedentes no se puede modificar.");
        }
        
        tutordb.setNombre(tutor.getNombre());
        tutordb.setEdad(tutor.getEdad());
        tutordb.setDomicilio(tutor.getDomicilio());
        tutordb.setTelefonoCasa(tutor.getTelefonoCasa());
        tutordb.setCelular(tutor.getCelular());
        
        
        return tutorService.update(tutordb);
        
        
    }
    
}
