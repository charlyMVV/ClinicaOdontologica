package com.Clinica.SistemaClinicaBack.controller;

import com.Clinica.SistemaClinicaBack.entity.CabezaCuello;
import com.Clinica.SistemaClinicaBack.repository.CabezaCuelloRepository;
import com.Clinica.SistemaClinicaBack.service.CabezaCuelloService;
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
@RequestMapping("/api/cabezacuello")
@CrossOrigin(origins = "http://localhost:4200")
public class CabezaCuelloController {

    private final CabezaCuelloService cabezaCuelloService;
    private final CabezaCuelloRepository cabezaCuelloRepository;

    public CabezaCuelloController(CabezaCuelloService cabezaCuelloService, CabezaCuelloRepository cabezaCuelloRepository) {
        this.cabezaCuelloService = cabezaCuelloService;
        this.cabezaCuelloRepository = cabezaCuelloRepository;
    }
    
    
    //localhost:8080/api/cabezacuello
    @GetMapping
    public List<CabezaCuello> findAll(){
        return cabezaCuelloService.findAll();
    } 
    
    //localhost:8080/api/cabezacuello/"id"
    @GetMapping("/{idExploracionCabezacuello}")
    public CabezaCuello findById(@PathVariable("idExploracionCabezacuello")Integer id){
        return cabezaCuelloService.findById(id);
    }
    
    //localhost:8080/api/cabezacuello/"id"
    @DeleteMapping("/{idExploracionCabezacuello}")
    public void deleteById(@PathVariable("idExploracionCabezacuello")Integer id){
        cabezaCuelloService.deleteById(id);
    }
    
    @PostMapping
    public ResponseEntity<CabezaCuello> save(@RequestBody CabezaCuello cabezaCuello){
        return ResponseEntity.ok(cabezaCuelloService.save(cabezaCuello));
    }
    
    @GetMapping("/existen/{curp}")
    public ResponseEntity<Boolean> existenCabezaCuelloPorCurp(@PathVariable String curp){
        boolean existen = cabezaCuelloRepository.existsByCurp(curp);
        return ResponseEntity.ok(existen);
    }
    
    @PutMapping
    public CabezaCuello updateCabezaCuello(@RequestBody CabezaCuello cabezaCuello){
        
        CabezaCuello cabezaCuellodb = cabezaCuelloService.findById(cabezaCuello.getIdExploracionCabezacuello());
        
        if (!cabezaCuellodb.getCurp().equals(cabezaCuello.getCurp())) {
            throw new IllegalArgumentException("la curp no se puede modificar.");
        }
        
        cabezaCuellodb.setExostosis(cabezaCuello.getExostosis());
        cabezaCuellodb.setEndotosis(cabezaCuello.getEndotosis());
        cabezaCuellodb.setDolicocefalico(cabezaCuello.getDolicocefalico());
        cabezaCuellodb.setMesocefalico(cabezaCuello.getMesocefalico());
        cabezaCuellodb.setBranquicefalico(cabezaCuello.getBranquicefalico());
        cabezaCuellodb.setAsimetriaTransversal(cabezaCuello.getAsimetriaTransversal());
        cabezaCuellodb.setAsimetriaLongitudinal(cabezaCuello.getAsimetriaLongitudinal());
        cabezaCuellodb.setPerfilConcavo(cabezaCuello.getPerfilConcavo());
        cabezaCuellodb.setPerfilConvexo(cabezaCuello.getPerfilConvexo());
        cabezaCuellodb.setPerfilRecto(cabezaCuello.getPerfilRecto());
        cabezaCuellodb.setPielNormal(cabezaCuello.getPielNormal());
        cabezaCuellodb.setPielPalida(cabezaCuello.getPielPalida());
        cabezaCuellodb.setPielCianotica(cabezaCuello.getPielCianotica());
        cabezaCuellodb.setPielEnrojecida(cabezaCuello.getPielEnrojecida());
        cabezaCuellodb.setMusculosHipotonicos(cabezaCuello.getMusculosHipotonicos());
        cabezaCuellodb.setMusculosHipertonicos(cabezaCuello.getMusculosHipertonicos());
        cabezaCuellodb.setMusculosEspasticos(cabezaCuello.getMusculosEspasticos());
        cabezaCuellodb.setCadenaGanglionar(cabezaCuello.getCadenaGanglionar());
        
        
        
        return cabezaCuelloService.update(cabezaCuellodb);
    }
    

}
