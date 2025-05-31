/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javidev.notes.service;

import com.javidev.notes.controller.domain.Categoria;
import com.javidev.notes.controller.domain.Usuario;
import com.javidev.notes.repository.CategoriaRepository;
import com.javidev.notes.repository.UsuarioRepository;
import java.util.List;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author music
 */
@Service
public class UsuarioService {
     private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    
    public List<Usuario> buscarTodos(){
        return usuarioRepository.findAll();
    }
    
    public Usuario buscarPorId(int id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
    //busca un usuario en la bd por su email
    public Usuario buscarPorEmail(String email){
        return usuarioRepository.findByUserEmail(email).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        
    }
}
