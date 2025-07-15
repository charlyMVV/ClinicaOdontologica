
package com.Clinica.SistemaClinicaBack.controller;

import com.Clinica.SistemaClinicaBack.entity.AntecedentesNoPatologicos;
import com.Clinica.SistemaClinicaBack.repository.AntecedentesNoPatologicosRepository;
import com.Clinica.SistemaClinicaBack.service.AntecedentesNoPatologicosService;
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
@RequestMapping("/api/antecedentesnopatologicos")
@CrossOrigin(origins = "http://localhost:4200")
public class AntecedentesNoPatologicosController {
    
    private final AntecedentesNoPatologicosService antecedentesNoPatologicosService;
    private final AntecedentesNoPatologicosRepository antecedentesNoPatologicosRepository;

    public AntecedentesNoPatologicosController(AntecedentesNoPatologicosService antecedentesNoPatologicosService, AntecedentesNoPatologicosRepository antecedentesNoPatologicosRepository) {
        this.antecedentesNoPatologicosService = antecedentesNoPatologicosService;
        this.antecedentesNoPatologicosRepository = antecedentesNoPatologicosRepository;
    }
    
    @PostMapping
    public ResponseEntity<AntecedentesNoPatologicos> save(@RequestBody AntecedentesNoPatologicos antecedentesNoPatologicos){
        return ResponseEntity.ok(antecedentesNoPatologicosService.save(antecedentesNoPatologicos));
    }
    
    //localhost:8080/api/antecedentesnopatologicos
    @GetMapping
    public List<AntecedentesNoPatologicos> findAll(){
        return antecedentesNoPatologicosService.findAll();
    }
    
    //localhost:8080/api/antecedentesnopatologicos/"id"
    @GetMapping("/{idAntecedentesNoPatologicos}")
    public AntecedentesNoPatologicos findById(@PathVariable("idAntecedentesNoPatologicos")Integer id){
        return antecedentesNoPatologicosService.findById(id);
    }
    
    //localhost:8080/api/antecedentesnopatologicos/"id"
    @DeleteMapping("/{idAntecedentesNoPatologicos}")
    public void deleteById(@PathVariable("idAntecedentesNoPatologicos")Integer id){
        antecedentesNoPatologicosService.deleteById(id);
    }
   
    //localhost:8080/api/antecedentes
    @PutMapping
    public AntecedentesNoPatologicos updateAntecedentesNoPatologicos(@RequestBody AntecedentesNoPatologicos antecedentesNoPatologicos){
       
    AntecedentesNoPatologicos antecedentesNoPatologicosdb = antecedentesNoPatologicosService.findById(antecedentesNoPatologicos.getIdAntecedentesNoPatologicos());
        
    if(!antecedentesNoPatologicosdb.getCurp().equals(antecedentesNoPatologicos.getCurp())) {
            throw new IllegalArgumentException("La CURP no se puede modificar.");
        }
        
    antecedentesNoPatologicosdb.setFrecuenciaLavadoDientes(antecedentesNoPatologicos.getFrecuenciaLavadoDientes());
    antecedentesNoPatologicosdb.setUsaAuxiliaresHigiene(antecedentesNoPatologicos.getUsaAuxiliaresHigiene());
    antecedentesNoPatologicosdb.setTiposAuxiliaresHigiene(antecedentesNoPatologicos.getTiposAuxiliaresHigiene());
    antecedentesNoPatologicosdb.setGrupoSanguineo(antecedentesNoPatologicos.getGrupoSanguineo());
    antecedentesNoPatologicosdb.setFactorRh(antecedentesNoPatologicos.getFactorRh());
    antecedentesNoPatologicosdb.setEsquemaCompleto(antecedentesNoPatologicos.getEsquemaCompleto());
    antecedentesNoPatologicosdb.setVacunasFaltantes(antecedentesNoPatologicos.getVacunasFaltantes());
    antecedentesNoPatologicosdb.setAntecedentesAlergicos(antecedentesNoPatologicos.getAntecedentesAlergicos());
    antecedentesNoPatologicosdb.setCualAlergicos(antecedentesNoPatologicos.getCualAlergicos());
    antecedentesNoPatologicosdb.setAntibioticos(antecedentesNoPatologicos.getAntibioticos());   
    antecedentesNoPatologicosdb.setAnalgesicos(antecedentesNoPatologicos.getAnalgesicos());
    antecedentesNoPatologicosdb.setAnestesicos(antecedentesNoPatologicos.getAnestesicos());
    antecedentesNoPatologicosdb.setAlimentos(antecedentesNoPatologicos.getAlimentos());
    antecedentesNoPatologicosdb.setOtrasAlergias(antecedentesNoPatologicos.getOtrasAlergias());
    antecedentesNoPatologicosdb.setTieneAdicciones(antecedentesNoPatologicos.getTieneAdicciones());
    antecedentesNoPatologicosdb.setTabaco(antecedentesNoPatologicos.getTabaco());
    antecedentesNoPatologicosdb.setAlcohol(antecedentesNoPatologicos.getAlcohol());
    antecedentesNoPatologicosdb.setOtrasAdicciones(antecedentesNoPatologicos.getOtrasAdicciones());
    antecedentesNoPatologicosdb.setHaSidoHospitalizado(antecedentesNoPatologicos.getHaSidoHospitalizado());
    antecedentesNoPatologicosdb.setFechaHospitalizacion(antecedentesNoPatologicos.getFechaHospitalizacion());
    antecedentesNoPatologicosdb.setMotivoHospitalizacion(antecedentesNoPatologicos.getMotivoHospitalizacion());
    antecedentesNoPatologicosdb.setPadecimientoActual(antecedentesNoPatologicos.getPadecimientoActual());
    antecedentesNoPatologicosdb.setHaSidoAnestesiado(antecedentesNoPatologicos.getHaSidoAnestesiado());
    antecedentesNoPatologicosdb.setHaRecibidoTransfusion(antecedentesNoPatologicos.getHaRecibidoTransfusion());
    antecedentesNoPatologicosdb.setHaRecibidoPerforaciones(antecedentesNoPatologicos.getHaRecibidoPerforaciones());
    antecedentesNoPatologicosdb.setConsumeMedicamento(antecedentesNoPatologicos.getConsumeMedicamento());
    antecedentesNoPatologicosdb.setEmbarazo(antecedentesNoPatologicos.getEmbarazo());
    antecedentesNoPatologicosdb.setDiscapacidad(antecedentesNoPatologicos.getDiscapacidad());
    antecedentesNoPatologicosdb.setTieneIntervenciones(antecedentesNoPatologicos.getTieneIntervenciones());
    antecedentesNoPatologicosdb.setParteCuerpo(antecedentesNoPatologicos.getParteCuerpo());
    
    return antecedentesNoPatologicosService.update(antecedentesNoPatologicosdb);
    }
    
    @GetMapping("/existen/{curp}")
    public ResponseEntity<Boolean> existenAntecedentesNoPatologicosPorCurp(@PathVariable String curp) {  
        boolean existen = antecedentesNoPatologicosRepository.existsByCurp(curp);
        return ResponseEntity.ok(existen);
    }
}
