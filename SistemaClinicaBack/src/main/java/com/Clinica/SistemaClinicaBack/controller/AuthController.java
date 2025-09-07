package com.Clinica.SistemaClinicaBack.controller;

import com.Clinica.SistemaClinicaBack.entity.Usuario;
import com.Clinica.SistemaClinicaBack.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")

public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Usuario usuario, HttpSession session) {
        Usuario u = usuarioService.findByUsuario(usuario.getUsuario());

        if (u != null && u.getContrasena().equals(usuario.getContrasena())) {
            session.setAttribute("usuario", u);

            Map<String, String> respuesta = new HashMap<>();
            respuesta.put("mensaje", "Login correcto");
            respuesta.put("nombre", u.getNombreUsuario());
            respuesta.put("roles", u.getRoles());

            

            return ResponseEntity.ok(respuesta);
        }

        Map<String, String> error = new HashMap<>();
        error.put("mensaje", "Usuario o contraseña incorrectos");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Sesión cerrada");
    }

}
