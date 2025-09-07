/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Clinica.SistemaClinicaBack.controller;

import com.Clinica.SistemaClinicaBack.entity.Firma;
import com.Clinica.SistemaClinicaBack.entity.Paciente;
import com.Clinica.SistemaClinicaBack.service.FirmaService;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author charly michel
 */
@RestController
@RequestMapping("/api/firmas")
@CrossOrigin(origins = "http://localhost:4200")
public class FirmaController {
    
    private final FirmaService firmaService;

    public FirmaController(FirmaService firmaService) {
        this.firmaService = firmaService;
    }
    
    //localhost:8080/api/pacientes
    @PostMapping
    public Firma save(@RequestBody Firma firma){
        return firmaService.save(firma);
    }
    
    @GetMapping
    public List<Firma> findAll(){
        return firmaService.findAll();
    }
    
    @GetMapping("/{idFirma}") 
    public Firma FindById(@PathVariable("idFirma")Integer id){
        return firmaService.findById(id);
    }
    
    @DeleteMapping("/{idFirma}")
    public void deletById(@PathVariable("idFirma")Integer id){
        firmaService.deleteById(id);
    }
    
    
}
