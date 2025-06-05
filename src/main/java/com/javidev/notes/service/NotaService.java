/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javidev.notes.service;

import com.javidev.notes.controller.domain.Nota;
import com.javidev.notes.repository.NotaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author music
 */
@Service
public class NotaService {

    private final NotaRepository notaRepository;

    public NotaService(NotaRepository notaRepository) {
        this.notaRepository = notaRepository;
    }
    //retorna la lista de notas 
    public List<Nota> buscarNuevasNotas() {
        return notaRepository.buscarTodos();
    }

    /**
     * Retorna una lista de notas por id. de la categoria a la que pertenece la
     * nota
     *
     * @param cat_id
     * @return
     */
    public List<Nota> buscarPorCategoria(int cat_id) {
        return notaRepository.buscarPorCategoria(cat_id);
    }

    /**
     * Realiza una consulta a la bd para buscar notas por su titulo
     *
     * @param consulta
     * @return
     */
    public List<Nota> buscar(String consulta) {
        return notaRepository.buscar(consulta);
    }

    /**
     * Permite buscar una nota por su identificador
     *
     * @param id
     * @return
     */
    public Optional<Nota> buscarPorId(int id) {
        return notaRepository.findById(id);
    }

    /**
     * Guardar una nota en la base de datos
     *
     * @param nota
     * @return
     */
    public Nota guardarNota(Nota nota) {
        return notaRepository.save(nota);
    }

    /**
     * elimina una nota por su id
     *
     * @param id
     */
    public void eliminarNota(int id) {
        notaRepository.deleteById(id);
    }
    
    public List<Nota> buscarNuevasNotasPorUsuario(int UserId) {
    return notaRepository.buscarNuevasNotasPorUsuario(UserId);
    }
    
    /**
     * Retorna una lista de notas por id. de la categoria a la que pertenece la
     * nota por usuario
     *
     * @param cat_id
     * @param UserId
     * @return
     */
    public List<Nota> buscarPorCategoriaUsuario(int cat_id,int UserId) {
        return notaRepository.buscarPorCategoriaUsuario(cat_id, UserId);
    }
    
     /**
     * Realiza una consulta a la bd para buscar notas por su titulo y usuario
     *
     * @param consulta
     * @param UserId
     * @return
     */
    public List<Nota> buscarPorTituloUsuario(String consulta,int UserId) {
        return notaRepository.buscarPorTituloUsuario(consulta, UserId);
    }
}
