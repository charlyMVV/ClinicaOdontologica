package com.Clinica.SistemaClinicaBack.controller;

import com.Clinica.SistemaClinicaBack.entity.FotosInicio;
import com.Clinica.SistemaClinicaBack.entity.HistoriaClinica;
import com.Clinica.SistemaClinicaBack.repository.FotosInicioRepository;
import com.Clinica.SistemaClinicaBack.service.FotosInicioService;
import com.Clinica.SistemaClinicaBack.service.HistoriaClinicaService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fotosinicio")
@CrossOrigin(origins = "http://localhost:4200")
public class FotosInicioController {

    private final FotosInicioService fotosInicioService;
    private final FotosInicioRepository fotosInicioRepository;
    private final HistoriaClinicaService historiaClinicaService;

    public FotosInicioController(
            FotosInicioService fotosInicioService,
            FotosInicioRepository fotosInicioRepository,
            HistoriaClinicaService historiaClinicaService) {

        this.fotosInicioService = fotosInicioService;
        this.fotosInicioRepository = fotosInicioRepository;
        this.historiaClinicaService = historiaClinicaService;
    }

    @PostMapping
    public FotosInicio save(@RequestBody FotosInicio fotosInicio) {

        HistoriaClinica hc =
                historiaClinicaService.findByCurpPaciente(
                        fotosInicio.getCurp());

        fotosInicio.setHistoriaClinica(hc);

        return fotosInicioService.save(fotosInicio);
    }

    @GetMapping
    public List<FotosInicio> findAll() {
        return fotosInicioService.findAll();
    }

    @GetMapping("/{idFotosInicio}")
    public FotosInicio FindById(@PathVariable("idFotosInicio") Integer id) {
        return fotosInicioService.findById(id);
    }

    @DeleteMapping("/{idFotosInicio}")
    public void deletById(@PathVariable("idFotosInicio") Integer id) {
        fotosInicioService.deleteById(id);
    }

    @PostMapping("/multiples")
    @Transactional
    public ResponseEntity<?> guardarMultiples(@RequestBody List<FotosInicio> fotos) {

        if (!fotos.isEmpty()) {
            String curp = fotos.get(0).getCurp();

            HistoriaClinica hc =
                    historiaClinicaService.findByCurpPaciente(curp);

            fotosInicioRepository.deleteByCurp(curp);

            for (FotosInicio f : fotos) {
                f.setHistoriaClinica(hc);
                fotosInicioService.save(f);
            }
        }

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Se guardaron " + fotos.size() + " fotos.");
        respuesta.put("cantidad", fotos.size());

        return ResponseEntity.ok(respuesta);
    }
}