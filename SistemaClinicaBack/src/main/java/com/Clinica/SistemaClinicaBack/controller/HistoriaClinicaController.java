package com.Clinica.SistemaClinicaBack.controller;

import com.Clinica.SistemaClinicaBack.entity.HistoriaClinica;
import com.Clinica.SistemaClinicaBack.entity.Paciente;
import com.Clinica.SistemaClinicaBack.repository.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/historia-clinica")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class HistoriaClinicaController {

    private final PacienteRepository pacienteRepository;
    private final AntecedentesRepository antecedentesRepository;
    private final AntecedentesNoPatologicosRepository antecedentesNoPatologicosRepository;
    private final SignosVitalesRepository signosVitalesRepository;
    private final CabezaCuelloRepository cabezaCuelloRepository;
    private final ExploracionEstomatognaticoRepository exploracionEstomatognaticoRepository;
    private final TejidosBlandosRepository tejidosBlandosRepository;
    private final TutorRepository tutorRepository;
    private final DiagnosticoTratamientoRepository diagnosticoTratamientoRepository;
    private final EvolucionPacienteRepository evolucionPacienteRepository;
    private final FotosInicioRepository fotosInicioRepository;
    private final FirmaRepository firmaRepository;
    private final HistoriaClinicaRepository historiaClinicaRepository;

    @GetMapping("/{curp}")
    public ResponseEntity<Map<String, Object>> findHistoriaClinicaByCurp(@PathVariable String curp) {
        String curpNormalizada = curp.trim().toUpperCase();
        Optional<Paciente> paciente = pacienteRepository.findByCurp(curpNormalizada);

        if (paciente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Map<String, Object> historiaClinica = new LinkedHashMap<>();
        historiaClinica.put("curp", curpNormalizada);
        historiaClinica.put("paciente", paciente.get());
        historiaClinica.put("antecedentes", antecedentesRepository.findByCurp(curpNormalizada));
        historiaClinica.put("antecedentesNoPatologicos", antecedentesNoPatologicosRepository.findByCurp(curpNormalizada).orElse(null));
        historiaClinica.put("signosVitales", signosVitalesRepository.findByCurp(curpNormalizada).orElse(null));
        historiaClinica.put("cabezaCuello", cabezaCuelloRepository.findByCurp(curpNormalizada).orElse(null));
        historiaClinica.put("estomatognatico", exploracionEstomatognaticoRepository.findByCurp(curpNormalizada).orElse(null));
        historiaClinica.put("tejidosBlandos", tejidosBlandosRepository.findByCurp(curpNormalizada).orElse(null));
        historiaClinica.put("tutor", tutorRepository.findByCurp(curpNormalizada).orElse(null));
        historiaClinica.put("diagnosticoTratamiento", diagnosticoTratamientoRepository.findByCurp(curpNormalizada).orElse(null));
        historiaClinica.put("evolucionPaciente", evolucionPacienteRepository.findByCurp(curpNormalizada).orElse(null));
        historiaClinica.put("fotosInicio", fotosInicioRepository.findByCurp(curpNormalizada));
        historiaClinica.put("firma", firmaRepository.findFirstByCurp(curpNormalizada).orElse(null));

        return ResponseEntity.ok(historiaClinica);
    }

    @GetMapping("/estatus/{estatus}")
    public ResponseEntity<List<HistoriaClinica>> findByEstatus(
            @PathVariable String estatus) {

        String estatusNormalizado = estatus.trim().toUpperCase();

        List<HistoriaClinica> historias =
                historiaClinicaRepository
                        .findByEstatusHistoriaClinica_Clave(estatusNormalizado);

        return ResponseEntity.ok(historias);
    }

    @GetMapping("/todas")
    public ResponseEntity<List<HistoriaClinica>> findAllHistorias() {
        return ResponseEntity.ok(historiaClinicaRepository.findAll());
    }


}
