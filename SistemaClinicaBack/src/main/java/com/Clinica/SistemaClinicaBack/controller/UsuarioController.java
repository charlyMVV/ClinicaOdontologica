package com.Clinica.SistemaClinicaBack.controller;

import com.Clinica.SistemaClinicaBack.entity.Usuario;
import com.Clinica.SistemaClinicaBack.repository.UsuarioRepository;
import com.Clinica.SistemaClinicaBack.service.UsuarioService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.http.ResponseEntity;



@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    private final UsuarioService usuarioservice;
    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioService usuarioservice, UsuarioRepository usuarioRepository) {
        this.usuarioservice = usuarioservice;
        this.usuarioRepository = usuarioRepository;
    }
    

     

    @PostMapping
    public Usuario save(@RequestBody Usuario usuario) {
        return usuarioservice.save(usuario);
    }

    @GetMapping
    public List<Usuario> findAll() {
        return usuarioservice.findAll();
    }

    @GetMapping("/{matricula}")
    public Usuario findById(@PathVariable("matricula") String id) {
        return usuarioservice.findById(id);
    }
    
    @GetMapping("/findByRol/{roles}")
    public  ResponseEntity<Boolean> findByRol(@PathVariable("roles") String roles){
            boolean existen = usuarioRepository.existsByRoles(roles);
            return ResponseEntity.ok(existen);
    }
    
    @DeleteMapping("/{matricula}")
    public void deleteById(@PathVariable("matricula") String id) {
        usuarioservice.deleteById(id);
    }

    @PutMapping
    public Usuario updateUsuario(@RequestBody Usuario usuario) {
        Usuario usuariodb = usuarioservice.findById(usuario.getMatricula());

        usuariodb.setNombreUsuario(usuario.getNombreUsuario());
        usuariodb.setUsuario(usuario.getUsuario());
        usuariodb.setContrasena(usuario.getContrasena());
        usuariodb.setPeriodo(usuario.getPeriodo());

        return usuarioservice.update(usuariodb);
    }
}
   