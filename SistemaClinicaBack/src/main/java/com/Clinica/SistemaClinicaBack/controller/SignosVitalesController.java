package com.Clinica.SistemaClinicaBack.controller;

import com.Clinica.SistemaClinicaBack.entity.SignosVitales;
import com.Clinica.SistemaClinicaBack.repository.SignosVitalesRepository;
import com.Clinica.SistemaClinicaBack.service.SignosVitalesService;
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
@RequestMapping("/api/signosvitales")
@CrossOrigin(origins = "http://localhost:4200")
public class SignosVitalesController {

    private final SignosVitalesService signosVitalesService;
    private final SignosVitalesRepository signosVitalesRepository;

    public SignosVitalesController(SignosVitalesService signosVitalesService, SignosVitalesRepository signosVitalesRepository) {
        this.signosVitalesService = signosVitalesService;
        this.signosVitalesRepository = signosVitalesRepository;
    }

  

    @PostMapping
    public SignosVitales save(@RequestBody SignosVitales signosVitales) {
        return signosVitalesService.save(signosVitales);
    }

    @GetMapping
    public List<SignosVitales> findAll() {
        return signosVitalesService.findAll();
    }

    @GetMapping("/{idSignosVitales}")
    public SignosVitales findById(@PathVariable("idSignosVitales")Integer id){
        return signosVitalesService.findById(id);
    }
    
    @DeleteMapping("/{idSignosVitales}")
    public void deleteById(@PathVariable("idSignosVitales") Integer id){
        signosVitalesService.deleteById(id);
    }
    
    @PutMapping
    public SignosVitales updateSignosVitales(@RequestBody SignosVitales signosVitales){
        
        SignosVitales svdb = signosVitalesService.findById(signosVitales.getIdSignosVitales());
        
        svdb.setTemperatura(signosVitales.getTemperatura());
        svdb.setFrecuenciaRespiratoria(signosVitales.getFrecuenciaRespiratoria());
        svdb.setTensionArterial(signosVitales.getTensionArterial());
        svdb.setFrecuenciaCardiaca(signosVitales.getFrecuenciaCardiaca());
        svdb.setPeso(signosVitales.getPeso());
        svdb.setTalla(signosVitales.getTalla());
        
        return updateSignosVitales(svdb);
    }
    
    
       @GetMapping("/existen/{curp}")
    public ResponseEntity<Boolean> existenAntecedentesNoPatologicosPorCurp(@PathVariable String curp) {  
        boolean existen = signosVitalesRepository.existsByCurp(curp);
        return ResponseEntity.ok(existen);
    }

}