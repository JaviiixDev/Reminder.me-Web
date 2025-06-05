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
// Anotación que marca esta clase como un "Service" en Spring, 
// lo cual la convierte en un componente gestionado automáticamente por el contenedor de Spring.
@Service
public class CategoriaService {

    // Dependencia del repositorio que accede a la base de datos.
    // Spring la inyectará automáticamente gracias a la inyección por constructor.
    private final CategoriaRepository categoriaRepository;

    // Constructor con inyección de dependencias: Spring lo usa para pasar una instancia de CategoriaRepository.
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }
    
    // Método que obtiene todas las categorías de la base de datos.
    // Internamente usa el método findAll() del repositorio JPA.
    public List<Categoria> buscarTodos(){
        return categoriaRepository.findAll();
    }

    // Método que busca una categoría por su ID.
    // Si no encuentra la categoría, lanza una excepción con un mensaje personalizado.
    public Categoria buscarPorId(int id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
    }
}

