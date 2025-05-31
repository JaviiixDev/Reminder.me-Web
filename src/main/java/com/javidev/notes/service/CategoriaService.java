/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javidev.notes.service;

import com.javidev.notes.controller.domain.Categoria;
import com.javidev.notes.repository.CategoriaRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author music
 */
@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }
    
    public List<Categoria> buscarTodos(){
        return categoriaRepository.findAll();
    }
    public Categoria buscarPorId(int id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
    }
}
