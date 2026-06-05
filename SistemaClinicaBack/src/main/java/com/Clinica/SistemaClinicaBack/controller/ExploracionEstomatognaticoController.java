
package com.Clinica.SistemaClinicaBack.controller;

import com.Clinica.SistemaClinicaBack.entity.ExploracionEstomatognatico;
import com.Clinica.SistemaClinicaBack.entity.HistoriaClinica;
import com.Clinica.SistemaClinicaBack.repository.ExploracionEstomatognaticoRepository;
import com.Clinica.SistemaClinicaBack.service.ExploracionEstomatognaticoService;
import java.util.List;

import com.Clinica.SistemaClinicaBack.service.HistoriaClinicaService;
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
    private final HistoriaClinicaService historiaClinicaService;

    public ExploracionEstomatognaticoController(
            ExploracionEstomatognaticoService exploracionEstomatognaticoService,
            ExploracionEstomatognaticoRepository exploracionEstomatognaticoRepository,
            HistoriaClinicaService historiaClinicaService) {

        this.exploracionEstomatognaticaService = exploracionEstomatognaticoService;
        this.exploracionEstomatognaticaRepository = exploracionEstomatognaticoRepository;
        this.historiaClinicaService = historiaClinicaService;
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
    public ResponseEntity<ExploracionEstomatognatico> save(
            @RequestBody ExploracionEstomatognatico exploracionEstomatognatico) {

        ExploracionEstomatognatico guardado =
                exploracionEstomatognaticaService.save(
                        exploracionEstomatognatico);

        HistoriaClinica hc =
                historiaClinicaService.findByCurpPaciente(
                        guardado.getCurp());

        hc.setExploracionEstomatognatico(guardado);

        historiaClinicaService.update(hc);

        return ResponseEntity.ok(guardado);
    }
    
    @GetMapping("/existen/{curp}")
        public ResponseEntity<Boolean> existenExploracionEstomatognaticoPorCurp(@PathVariable String curp) {  
        boolean existen = exploracionEstomatognaticaRepository.existsByCurp(curp);
        return ResponseEntity.ok(existen);
    }


    @PutMapping("/curp/{curp}")
    public ExploracionEstomatognatico updateExploracionEstomatognatico(
            @PathVariable String curp,
            @RequestBody ExploracionEstomatognatico exploracionEstomatognatico) throws IllegalAccessException {

        ExploracionEstomatognatico exploracionEstomatognaticodb =
                exploracionEstomatognaticaService.findByCurp(curp);

        if (!exploracionEstomatognaticodb.getCurp().equals(curp)) {
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

        ExploracionEstomatognatico actualizado =
                exploracionEstomatognaticaService.update(
                        exploracionEstomatognaticodb);

        HistoriaClinica hc =
                historiaClinicaService.findByCurpPaciente(
                        actualizado.getCurp());

        hc.setExploracionEstomatognatico(actualizado);

        historiaClinicaService.update(hc);

        return actualizado;
    }

}
