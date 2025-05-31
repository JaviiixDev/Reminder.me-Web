/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javidev.notes.security;

import com.javidev.notes.controller.domain.Usuario;
import com.javidev.notes.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author music
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    //crea una instancia de usuarioRepository
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    //se sobreescribe el metodo de spring security para cargar los detalles del usuario por su correo electronico
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //busca un usuario en la base de datos por su email, si no lo encuentra lanza una excepcion
        Usuario usuario = usuarioRepository.findByUserEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        
        //Crea un objeto user, con todos los datos del usuario
        return User.builder()
                .username(usuario.getUserEmail()) //agrega el email como el username
                .password(usuario.getUserPassword()) //establece su contraseña(debe estar encryptada desde la bd)
                .roles("USER") //asigna el rol user
                .build(); //construye el objeto user details
    }
}
