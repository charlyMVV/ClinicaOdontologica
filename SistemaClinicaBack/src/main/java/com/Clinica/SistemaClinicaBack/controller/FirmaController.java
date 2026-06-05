package com.Clinica.SistemaClinicaBack.controller;

import com.Clinica.SistemaClinicaBack.entity.Firma;
import com.Clinica.SistemaClinicaBack.entity.HistoriaClinica;
import com.Clinica.SistemaClinicaBack.service.FirmaService;
import com.Clinica.SistemaClinicaBack.service.HistoriaClinicaService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/firmas")
@CrossOrigin(origins = "http://localhost:4200")
public class FirmaController {

    private final FirmaService firmaService;
    private final HistoriaClinicaService historiaClinicaService;

    public FirmaController(
            FirmaService firmaService,
            HistoriaClinicaService historiaClinicaService) {

        this.firmaService = firmaService;
        this.historiaClinicaService = historiaClinicaService;
    }

    @PostMapping
    public Firma save(@RequestBody Firma firma){

        Firma guardada = firmaService.save(firma);

        HistoriaClinica hc =
                historiaClinicaService.findByCurpPaciente(
                        guardada.getCurp());

        hc.setFirma(guardada);

        historiaClinicaService.update(hc);

        return guardada;
    }

    @GetMapping
    public List<Firma> findAll(){
        return firmaService.findAll();
    }

    @GetMapping("/{idFirma}")
    public Firma FindById(@PathVariable("idFirma") Integer id){
        return firmaService.findById(id);
    }

    @DeleteMapping("/{idFirma}")
    public void deletById(@PathVariable("idFirma") Integer id){
        firmaService.deleteById(id);
    }
}