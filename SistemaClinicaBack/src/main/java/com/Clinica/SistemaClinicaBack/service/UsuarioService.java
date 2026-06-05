package com.Clinica.SistemaClinicaBack.service;

import com.Clinica.SistemaClinicaBack.entity.Usuario;
import java.util.List;

public interface UsuarioService{

    Usuario save(Usuario usuario);

    List<Usuario> findAll();

    Usuario findById(String id);

    void deleteById(String id);

    Usuario update(Usuario usuario);

   Usuario findByUsuario(String usuario);

    Usuario findByMatricula(String matricula);

}
