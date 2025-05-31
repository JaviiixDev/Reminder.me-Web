/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javidev.notes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author music
 */
@Controller
public class LoginController {
     //metodo que devuelve el nombre de la vista html cuando el usuario esta en /login
    @GetMapping("/login")
    public String login() {
        return "login.html"; 
    }
}
