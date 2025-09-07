package com.Clinica.SistemaClinicaBack.controller;

import com.Clinica.SistemaClinicaBack.entity.FotosInicio;
import com.Clinica.SistemaClinicaBack.service.FotosInicioService;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author charly michel
 */
@RestController
@RequestMapping("/api/fotosinicio")
@CrossOrigin(origins = "http://localhost:4200")
public class FotosInicioController {

    private final FotosInicioService FotosInicioService;

    public FotosInicioController(FotosInicioService FotosInicioService) {
        this.FotosInicioService = FotosInicioService;
    }

    //localhost:8080/api/pacientes
    @PostMapping
    public FotosInicio save(@RequestBody FotosInicio fotosInicio) {
        return FotosInicioService.save(fotosInicio);
    }

    @GetMapping
    public List<FotosInicio> findAll() {
        return FotosInicioService.findAll();
    }

    @GetMapping("/{idFotosInicio}")
    public FotosInicio FindById(@PathVariable("idFotosInicio") Integer id) {
        return FotosInicioService.findById(id);
    }

    @DeleteMapping("/{idFotosInicio}")
    public void deletById(@PathVariable("idFotosInicio") Integer id) {
        FotosInicioService.deleteById(id);
    }

    @PostMapping("/multiples")
    public ResponseEntity<?> guardarMultiples(@RequestBody List<FotosInicio> fotos) {
        for (FotosInicio f : fotos) {
            FotosInicioService.save(f);
        }

        // Retornar una respuesta como JSON
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Se guardaron " + fotos.size() + " fotos.");
        respuesta.put("cantidad", fotos.size());

        return ResponseEntity.ok(respuesta);
    }

}
