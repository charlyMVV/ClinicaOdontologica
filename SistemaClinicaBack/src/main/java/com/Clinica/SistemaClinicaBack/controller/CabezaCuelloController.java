package com.Clinica.SistemaClinicaBack.controller;

import com.Clinica.SistemaClinicaBack.entity.CabezaCuello;
import com.Clinica.SistemaClinicaBack.entity.HistoriaClinica;
import com.Clinica.SistemaClinicaBack.repository.CabezaCuelloRepository;
import com.Clinica.SistemaClinicaBack.service.CabezaCuelloService;
import com.Clinica.SistemaClinicaBack.service.HistoriaClinicaService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cabezacuello")
@CrossOrigin(origins = "http://localhost:4200")
public class CabezaCuelloController {

    private final CabezaCuelloService cabezaCuelloService;
    private final CabezaCuelloRepository cabezaCuelloRepository;
    private final HistoriaClinicaService historiaClinicaService;

    public CabezaCuelloController(
            CabezaCuelloService cabezaCuelloService,
            CabezaCuelloRepository cabezaCuelloRepository,
            HistoriaClinicaService historiaClinicaService) {

        this.cabezaCuelloService = cabezaCuelloService;
        this.cabezaCuelloRepository = cabezaCuelloRepository;
        this.historiaClinicaService = historiaClinicaService;
    }

    @GetMapping
    public List<CabezaCuello> findAll() {
        return cabezaCuelloService.findAll();
    }

    @GetMapping("/{idExploracionCabezacuello}")
    public CabezaCuello findById(@PathVariable("idExploracionCabezacuello") Integer id) {
        return cabezaCuelloService.findById(id);
    }

    @DeleteMapping("/{idExploracionCabezacuello}")
    public void deleteById(@PathVariable("idExploracionCabezacuello") Integer id) {
        cabezaCuelloService.deleteById(id);
    }

    @PostMapping
    public ResponseEntity<CabezaCuello> save(@RequestBody CabezaCuello cabezaCuello) {

        CabezaCuello guardado = cabezaCuelloService.save(cabezaCuello);

        HistoriaClinica hc =
                historiaClinicaService.findByCurpPaciente(guardado.getCurp());

        hc.setCabezaCuello(guardado);

        historiaClinicaService.update(hc);

        return ResponseEntity.ok(guardado);
    }

    @GetMapping("/existen/{curp}")
    public ResponseEntity<Boolean> existenCabezaCuelloPorCurp(@PathVariable String curp) {
        boolean existen = cabezaCuelloRepository.existsByCurp(curp);
        return ResponseEntity.ok(existen);
    }

    @PutMapping("/curp/{curp}")
    public CabezaCuello updateCabezaCuello(
            @PathVariable String curp,
            @RequestBody CabezaCuello cabezaCuello) {

        CabezaCuello cabezaCuellodb = cabezaCuelloService.findByCurp(curp);

        if (!cabezaCuellodb.getCurp().equals(curp)) {
            throw new IllegalArgumentException("La CURP no se puede modificar.");
        }

        cabezaCuellodb.setExostosis(cabezaCuello.isExostosis());
        cabezaCuellodb.setEndotosis(cabezaCuello.isEndotosis());
        cabezaCuellodb.setDolicocefalico(cabezaCuello.isDolicocefalico());
        cabezaCuellodb.setMesocefalico(cabezaCuello.isMesocefalico());
        cabezaCuellodb.setBranquicefalico(cabezaCuello.isBranquicefalico());
        cabezaCuellodb.setAsimetriaTransversal(cabezaCuello.isAsimetriaTransversal());
        cabezaCuellodb.setAsimetriaLongitudinal(cabezaCuello.isAsimetriaLongitudinal());
        cabezaCuellodb.setPerfilConcavo(cabezaCuello.isPerfilConcavo());
        cabezaCuellodb.setPerfilConvexo(cabezaCuello.isPerfilConvexo());
        cabezaCuellodb.setPerfilRecto(cabezaCuello.isPerfilRecto());
        cabezaCuellodb.setPielNormal(cabezaCuello.isPielNormal());
        cabezaCuellodb.setPielPalida(cabezaCuello.isPielPalida());
        cabezaCuellodb.setPielCianotica(cabezaCuello.isPielCianotica());
        cabezaCuellodb.setPielEnrojecida(cabezaCuello.isPielEnrojecida());
        cabezaCuellodb.setMusculosHipotonicos(cabezaCuello.isMusculosHipotonicos());
        cabezaCuellodb.setMusculosHipertonicos(cabezaCuello.isMusculosHipertonicos());
        cabezaCuellodb.setMusculosEspasticos(cabezaCuello.isMusculosEspasticos());
        cabezaCuellodb.setCadenaGanglionar(cabezaCuello.isCadenaGanglionar());

        CabezaCuello actualizado = cabezaCuelloService.update(cabezaCuellodb);

        HistoriaClinica hc =
                historiaClinicaService.findByCurpPaciente(actualizado.getCurp());

        hc.setCabezaCuello(actualizado);

        historiaClinicaService.update(hc);

        return actualizado;
    }
}