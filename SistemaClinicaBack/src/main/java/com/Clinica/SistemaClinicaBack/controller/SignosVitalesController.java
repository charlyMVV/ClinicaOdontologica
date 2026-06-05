package com.Clinica.SistemaClinicaBack.controller;

import com.Clinica.SistemaClinicaBack.entity.HistoriaClinica;
import com.Clinica.SistemaClinicaBack.entity.SignosVitales;
import com.Clinica.SistemaClinicaBack.repository.SignosVitalesRepository;
import com.Clinica.SistemaClinicaBack.service.HistoriaClinicaService;
import com.Clinica.SistemaClinicaBack.service.SignosVitalesService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/signosvitales")
@CrossOrigin(origins = "http://localhost:4200")
public class SignosVitalesController {

    private final SignosVitalesService signosVitalesService;
    private final SignosVitalesRepository signosVitalesRepository;
    private final HistoriaClinicaService historiaClinicaService;

    public SignosVitalesController(
            SignosVitalesService signosVitalesService,
            SignosVitalesRepository signosVitalesRepository,
            HistoriaClinicaService historiaClinicaService) {

        this.signosVitalesService = signosVitalesService;
        this.signosVitalesRepository = signosVitalesRepository;
        this.historiaClinicaService = historiaClinicaService;
    }

    @PostMapping
    public SignosVitales save(@RequestBody SignosVitales signosVitales) {

        SignosVitales guardado = signosVitalesService.save(signosVitales);

        HistoriaClinica hc =
                historiaClinicaService.findByCurpPaciente(guardado.getCurp());

        hc.setSignosVitales(guardado);

        historiaClinicaService.update(hc);

        return guardado;
    }

    @GetMapping
    public List<SignosVitales> findAll() {
        return signosVitalesService.findAll();
    }

    @GetMapping("/{idSignosVitales}")
    public SignosVitales findById(@PathVariable("idSignosVitales") Integer id) {
        return signosVitalesService.findById(id);
    }

    @DeleteMapping("/{idSignosVitales}")
    public void deleteById(@PathVariable("idSignosVitales") Integer id) {
        signosVitalesService.deleteById(id);
    }

    @PutMapping("/curp/{curp}")
    public SignosVitales updateSignosVitales(
            @PathVariable String curp,
            @RequestBody SignosVitales signosVitales) {

        SignosVitales svdb = signosVitalesService.findByCurp(curp);

        if (!svdb.getCurp().equals(curp)) {
            throw new IllegalArgumentException("La CURP no se puede modificar.");
        }

        svdb.setTemperatura(signosVitales.getTemperatura());
        svdb.setFrecuenciaRespiratoria(signosVitales.getFrecuenciaRespiratoria());
        svdb.setTensionArterial(signosVitales.getTensionArterial());
        svdb.setFrecuenciaCardiaca(signosVitales.getFrecuenciaCardiaca());
        svdb.setPeso(signosVitales.getPeso());
        svdb.setTalla(signosVitales.getTalla());

        SignosVitales actualizado = signosVitalesService.update(svdb);

        HistoriaClinica hc =
                historiaClinicaService.findByCurpPaciente(actualizado.getCurp());

        hc.setSignosVitales(actualizado);

        historiaClinicaService.update(hc);

        return actualizado;
    }

    @GetMapping("/existen/{curp}")
    public ResponseEntity<Boolean> existenSignosVitalesPorCurp(@PathVariable String curp) {
        boolean existen = signosVitalesRepository.existsByCurp(curp);
        return ResponseEntity.ok(existen);
    }
}