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
// Marca esta clase como un componente de servicio de Spring.
// Spring la detecta automáticamente y la gestiona como un "bean" para inyección de dependencias.
@Service
public class UsuarioService {

    // Dependencia del repositorio que se encarga del acceso a la base de datos para la entidad Usuario.
    private final UsuarioRepository usuarioRepository;

    // Constructor con inyección de dependencias.
    // Spring pasa automáticamente una instancia del repositorio cuando se crea el servicio.
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    
    // Método para obtener todos los usuarios desde la base de datos.
    // Utiliza el método findAll() proporcionado por JpaRepository.
    public List<Usuario> buscarTodos(){
        return usuarioRepository.findAll();
    }
    
    // Método para buscar un usuario por su ID.
    // Si no lo encuentra, lanza una excepción genérica con un mensaje personalizado.
    public Usuario buscarPorId(int id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    // Método para buscar un usuario por su correo electrónico.
    // Si no lo encuentra, lanza una excepción específica de Spring Security (UsernameNotFoundException),
    // útil si esta lógica se integra con un sistema de autenticación.
    public Usuario buscarPorEmail(String email){
        return usuarioRepository.findByUserEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }
}

