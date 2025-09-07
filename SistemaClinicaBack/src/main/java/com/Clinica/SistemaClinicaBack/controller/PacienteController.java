package com.Clinica.SistemaClinicaBack.controller;

import com.Clinica.SistemaClinicaBack.entity.Paciente;
import com.Clinica.SistemaClinicaBack.service.PacienteService;
import java.util.List;
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
@RequestMapping("/api/pacientes")
@CrossOrigin(origins = "http://localhost:4200")
public class PacienteController {
    
    private final PacienteService pacienteservice;

    public PacienteController(PacienteService pacienteservice) {
        this.pacienteservice = pacienteservice;
    }
    
    //localhost:8080/api/pacientes
    @PostMapping
    public Paciente save(@RequestBody Paciente paciente){
        return pacienteservice.save(paciente);
        
    }
        
    //localhost:8080/api/pacientes
    @GetMapping
    public List<Paciente> findAll(){
        return pacienteservice.findAll();
    }
    
    //localhost:8080/api/periodo/"id"
    @GetMapping("/{idPaciente}") 
    public Paciente FindById(@PathVariable("idPaciente")Integer id){
        return pacienteservice.findById(id);
    }
    
    //localhost:8080/api/periodo/"id"
    @DeleteMapping("/{idPaciente}")
    public void deletById(@PathVariable("idPaciente")Integer id){
        pacienteservice.deleteById(id);
    }
    
    //localhost:8080/api/pacientes
    @PutMapping
    public Paciente UpdatePaciente(@RequestBody Paciente paciente){
        
        Paciente pacientedb = pacienteservice.findById(paciente.getIdPaciente());
        
        pacientedb.setNombrePaciente(paciente.getNombrePaciente());
        pacientedb.setCURP(paciente.getCURP());
        pacientedb.setSexo(paciente.getSexo());
        pacientedb.setEdad(paciente.getEdad());
        pacientedb.setfechaNacimiento(paciente.getfechaNacimiento());
        pacientedb.setDomicilio(paciente.getDomicilio());
        pacientedb.setTelefonoCasa(paciente.getTelefonoCasa());
        pacientedb.setTelefonoCelular(paciente.getTelefonoCelular());
        pacientedb.setReligion(paciente.getReligion());
        pacientedb.setOcupacion(paciente.getOcupacion());
        pacientedb.setEscolaridad(paciente.getEscolaridad());
        pacientedb.setEstadoCivil(paciente.getEstadoCivil());
        pacientedb.setDerechohabiente(paciente.getDerechohabiente());
        pacientedb.setMedicoFamiliar(paciente.getMedicoFamiliar());
        pacientedb.setNumero_medico(paciente.getNumero_medico());
        pacientedb.setUltimaConsulta(paciente.getUltimaConsulta());
        
        
        
        
        
        return pacienteservice.update(pacientedb);
    }
   
}
