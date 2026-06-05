package com.Clinica.SistemaClinicaBack.controller;

import com.Clinica.SistemaClinicaBack.entity.EvolucionPaciente;
import com.Clinica.SistemaClinicaBack.entity.HistoriaClinica;
import com.Clinica.SistemaClinicaBack.repository.EvolucionPacienteRepository;
import com.Clinica.SistemaClinicaBack.service.EvolucionPacienteService;
import com.Clinica.SistemaClinicaBack.service.HistoriaClinicaService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/evolucionpaciente")
@CrossOrigin("http://localhost:4200")
public class EvolucionPacienteController {

    private final EvolucionPacienteRepository evolucionPacienteRepository;
    private final EvolucionPacienteService evolucionPacienteService;
    private final HistoriaClinicaService historiaClinicaService;

    public EvolucionPacienteController(
            EvolucionPacienteRepository evolucionPacienteRepository,
            EvolucionPacienteService evolucionPacienteService,
            HistoriaClinicaService historiaClinicaService) {

        this.evolucionPacienteRepository = evolucionPacienteRepository;
        this.evolucionPacienteService = evolucionPacienteService;
        this.historiaClinicaService = historiaClinicaService;
    }

    @GetMapping
    public List<EvolucionPaciente> findAll() {
        return evolucionPacienteRepository.findAll();
    }

    @GetMapping("/{idControlEvolucion}")
    public EvolucionPaciente findById(@PathVariable("idControlEvolucion") Integer id) {
        return evolucionPacienteService.findById(id);
    }

    @DeleteMapping("/{idControlEvolucion}")
    public void deleteById(@PathVariable("idControlEvolucion") Integer id) {
        evolucionPacienteService.deleteById(id);
    }

    @PostMapping
    public ResponseEntity<EvolucionPaciente> save(
            @RequestBody EvolucionPaciente evolucionPaciente) {

        HistoriaClinica hc =
                historiaClinicaService.findByCurpPaciente(
                        evolucionPaciente.getCurp());

        evolucionPaciente.setHistoriaClinica(hc);

        EvolucionPaciente guardado =
                evolucionPacienteService.save(evolucionPaciente);

        return ResponseEntity.ok(guardado);
    }

    @GetMapping("/existen/{curp}")
    public ResponseEntity<Boolean> existenEvolucionPacientePorCurp(
            @PathVariable String curp) {

        boolean existen = evolucionPacienteRepository.existsByCurp(curp);
        return ResponseEntity.ok(existen);
    }

    @PutMapping("/curp/{curp}")
    public EvolucionPaciente updateEvolucion(
            @PathVariable String curp,
            @RequestBody EvolucionPaciente evolucion) {

        EvolucionPaciente evoluciondb =
                evolucionPacienteService.findByCurp(curp);

        if (!evoluciondb.getCurp().equals(curp)) {
            throw new IllegalArgumentException(
                    "La CURP de la evolución no se puede modificar.");
        }

        HistoriaClinica hc = historiaClinicaService.findByCurpPaciente(curp);

        evoluciondb.setFecha(evolucion.getFecha());
        evoluciondb.setComentarioControl(evolucion.getComentarioControl());
        evoluciondb.setHistoriaClinica(hc);

        return evolucionPacienteService.update(evoluciondb);
    }
}