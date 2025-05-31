/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javidev.notes.controller;

import com.javidev.notes.controller.domain.Categoria;
import com.javidev.notes.controller.domain.Nota;
import com.javidev.notes.controller.domain.Usuario;
import com.javidev.notes.service.CategoriaService;
import com.javidev.notes.service.NotaService;
import com.javidev.notes.service.UsuarioService;
import java.security.Principal;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author music
 */
@Controller
public class AddDeleteSaveController {

    private final NotaService notaService;
    private final CategoriaService categoriaService;
    private final UsuarioService usuarioService;

    public AddDeleteSaveController(NotaService notaService,
            CategoriaService categoriaService, UsuarioService usuarioService) {
        this.categoriaService = categoriaService;
        this.notaService = notaService;
        this.usuarioService = usuarioService;
    }

    /**
     * metodo que muestra el formulario de alta de notas
     *
     * @param model
     * @return
     */
    @RequestMapping("/notas/crear")
    public String mostrarCrearNota(Model model) {
        model.addAttribute("categorias", categoriaService.buscarTodos());
        model.addAttribute("nota", new Nota());
        return "create_note.html";
    }

    /**
     * Inserta una nueva nota a la bd
     *
     * @param nota
     * @param principal
     * @return
     */
    @PostMapping("/notas/guardar")
    public String guardar(Nota nota, Principal principal) {
        // Asignar las fechas mas actuales a la nota para guardarla en la base de datos.
        nota.setNota_create_date(LocalDate.now());
        nota.setNota_last_date(LocalDate.now());

        //asignar el usuario desde spring security, utilizando el usuario logeado
        String email = principal.getName();
        Usuario usuario = usuarioService.buscarPorEmail(email);
        nota.setUser_id(usuario);

        // Convertir el cat_id recibido (sólo contiene el ID) a una categoría real
        Categoria categoria = categoriaService.buscarPorId(nota.getCat_id().getCat_id());
        nota.setCat_id(categoria);

        notaService.guardarNota(nota);
        return "redirect:/";
    }

    /**
     * Elimina una nota por el id
     *
     * @param nota_id
     * @return
     */
    @RequestMapping("/notas/eliminar")
    public String eliminarNota(@RequestParam("nota_id") String nota_id, Principal principal) {
        int convertedId = Integer.parseInt(nota_id);

        // Obtener el usuario logueado
        String email = principal.getName();
        Usuario usuario = usuarioService.buscarPorEmail(email);

        // Buscar la nota y validar si pertenece al usuario
        Optional<Nota> notaOpt = notaService.buscarPorId(convertedId);
        if (notaOpt.isPresent() && notaOpt.get().getUser_id().getUserId() == usuario.getUserId()) {
            notaService.eliminarNota(convertedId);
        }

        return "redirect:/";
    }

    /**
     * actualiza los nuevos datos de una nota al editarla
     *
     * @param nota
     * @param principal
     * @return
     */
    @PostMapping("/notas/actualizar")
    public String actualizarNota(Nota nota, Principal principal) {
        // Reasignar ultima fecha de edicion
        nota.setNota_last_date(LocalDate.now());

        // Reasignar el usuario que esta logeado
        String email = principal.getName();
        Usuario usuario = usuarioService.buscarPorEmail(email);
        nota.setUser_id(usuario);

        // Reasignar la categoría 
        Categoria categoria = categoriaService.buscarPorId(nota.getCat_id().getCat_id());
        nota.setCat_id(categoria);

        notaService.guardarNota(nota);
        return "redirect:/";
    }
}
