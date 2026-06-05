package com.Clinica.SistemaClinicaBack.service;

import com.Clinica.SistemaClinicaBack.entity.Usuario;
import com.Clinica.SistemaClinicaBack.exception.ResourceNotFoundException;
import com.Clinica.SistemaClinicaBack.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario findById(String id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(String id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Usuario update(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

@Override
public Usuario findByUsuario(String usuario) {
    return usuarioRepository.findByUsuario(usuario)
            .orElse(null); // O lanza una excepción si prefieres
}

    @Override
    public Usuario findByMatricula(String matricula) {
        return usuarioRepository.findById(matricula)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Usuario no encontrado con matrícula: " + matricula
                ));
    }

    
}
