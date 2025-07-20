
package com.Clinica.SistemaClinicaBack.controller;

import com.Clinica.SistemaClinicaBack.entity.ExploracionEstomatognatico;
import com.Clinica.SistemaClinicaBack.repository.ExploracionEstomatognaticoRepository;
import com.Clinica.SistemaClinicaBack.service.ExploracionEstomatognaticoService;
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
@RequestMapping("/api/estomatognatico")
@CrossOrigin(origins = "http://localhost:4200")
public class ExploracionEstomatognaticoController {
    
    private final ExploracionEstomatognaticoService exploracionEstomatognaticaService;
    private final ExploracionEstomatognaticoRepository exploracionEstomatognaticaRepository;

    public ExploracionEstomatognaticoController(ExploracionEstomatognaticoService exploracionEstomatognaticoService, ExploracionEstomatognaticoRepository exploracionEstomatognaticoRepository) {
        this.exploracionEstomatognaticaService = exploracionEstomatognaticoService;
        this.exploracionEstomatognaticaRepository = exploracionEstomatognaticoRepository;
    }
    
    //localhost:8080/api/estomatognatico
    @GetMapping
    public List<ExploracionEstomatognatico> findAll(){
        return exploracionEstomatognaticaService.findAll();
    }
    
    //localhost:8080/api/estomatognatico/"id"
    @GetMapping("/{idEstomatognatico}")
    public ExploracionEstomatognatico findById(@PathVariable("idEstomatognatico")Integer id){
        return exploracionEstomatognaticaService.findById(id);
    }
    
    @DeleteMapping("/{idEstomatognatico}")
    public void deleteById(@PathVariable("idEstomatognatico") Integer id){
        exploracionEstomatognaticaService.deleteById(id);
    }
    
    @PostMapping
    public ResponseEntity<ExploracionEstomatognatico> save(@RequestBody ExploracionEstomatognatico exploracionEstomatognatico){
        return ResponseEntity.ok(exploracionEstomatognaticaService.save(exploracionEstomatognatico));
    }
    
    @GetMapping("/existen/{curp}")
        public ResponseEntity<Boolean> existenExploracionEstomatognaticoPorCurp(@PathVariable String curp) {  
        boolean existen = exploracionEstomatognaticaRepository.existsByCurp(curp);
        return ResponseEntity.ok(existen);
    }

    
    
    @PutMapping
    public ExploracionEstomatognatico updateExploracionEstomatognatico(@RequestBody ExploracionEstomatognatico exploracionEstomatognatico) throws IllegalAccessException{
           
        ExploracionEstomatognatico exploracionEstomatognaticodb = exploracionEstomatognaticaService.findById(exploracionEstomatognatico.getIdEstomatognatico());
       
        if (!exploracionEstomatognaticodb.getCurp().equals(exploracionEstomatognatico.getCurp())) {
            throw new IllegalAccessException("La Curp del estomatognatico no se puede modificar");
        }
        
        exploracionEstomatognaticodb.setRuidos(exploracionEstomatognatico.isRuidos());
        exploracionEstomatognaticodb.setLateralidad(exploracionEstomatognatico.isLateralidad());
        exploracionEstomatognaticodb.setApertura(exploracionEstomatognatico.isApertura());
        exploracionEstomatognaticodb.setChasquidos(exploracionEstomatognatico.isChasquidos());
        exploracionEstomatognaticodb.setCrepitacion(exploracionEstomatognatico.isCrepitacion());
        exploracionEstomatognaticodb.setDificultadAbrirboca(exploracionEstomatognatico.isDificultadAbrirboca());
        exploracionEstomatognaticodb.setDolorAberturaLateralidad(exploracionEstomatognatico.isDolorAberturaLateralidad());
        exploracionEstomatognaticodb.setFatigaDolorMuscular(exploracionEstomatognatico.isFatigaDolorMuscular());
        exploracionEstomatognaticodb.setDisminuicionAbertura(exploracionEstomatognatico.isDisminuicionAbertura());
        exploracionEstomatognaticodb.setDesviacionAberturaCierre(exploracionEstomatognatico.isDesviacionAberturaCierre());
     
        return exploracionEstomatognaticaService.update(exploracionEstomatognaticodb);
    }
    
    
    
    
    
    
    
}
