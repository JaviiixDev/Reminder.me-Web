/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javidev.notes.controller;

import com.javidev.notes.controller.domain.Nota;
import com.javidev.notes.controller.domain.Usuario;
import com.javidev.notes.service.CategoriaService;
import com.javidev.notes.service.NotaService;
import com.javidev.notes.service.UsuarioService;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author music
 */
@Controller
public class ListadoController {

    private final NotaService notaService;
    private final CategoriaService categoriaService;
    private final UsuarioService usuarioService;

    public ListadoController(NotaService notaService, CategoriaService categoriaService, UsuarioService usuarioService) {
        this.notaService = notaService;
        this.categoriaService = categoriaService;
        this.usuarioService = usuarioService;
    }

    /**
     * muestra en la ruta principal a la pagina principal index que es donde se
     * muestran todas las notas
     *
     * @param model
     * @param principal
     * @return
     */
    @RequestMapping("/")
    public String listarNotas(Model model, Principal principal) {
        // Obtener el email del usuario logueado
        String email = principal.getName();

        // Obtener el usuario por su email
        Usuario usuario = usuarioService.buscarPorEmail(email);

        List<Nota> notasNuevas = notaService.buscarNuevasNotasPorUsuario(usuario.getUserId());
        model.addAttribute("notas", notasNuevas);

        return "index.html";
    }

    /**
     * Retorna una lista de elementos según la categoria
     *
     * @param cat_id
     * @param model
     * @return
     */
    @RequestMapping("/categoria")
    public String listarNotasPorCategoria(Model model, int cat_id, Principal principal) {
        // Obtener el email del usuario logueado
        String email = principal.getName();

        // Obtener el usuario por su email
        Usuario usuario = usuarioService.buscarPorEmail(email);

        List<Nota> notasCat = notaService.buscarPorCategoriaUsuario(cat_id, usuario.getUserId());
        model.addAttribute("notas", notasCat);

        return "index.html";
    }

    /**
     * Retorna una lista de elementos si coinciden con el titulo
     *
     * @param consulta
     * @param model
     * @return
     */
    @RequestMapping("/buscar")
    public String buscarNotasPorTitulo(Model model, String consulta, Principal principal) {

        String email = principal.getName();
        Usuario usuario = usuarioService.buscarPorEmail(email);

        List<Nota> notasBusqueda = notaService.buscarPorTituloUsuario(consulta, usuario.getUserId());

        model.addAttribute("notas", notasBusqueda);

        return "index.html";
    }

    /**
     * Maneja la solicitud para buscar una nota por su ID y manda a error si no
     * encuentra ninguna
     *
     * @param notaId
     * @param model El modelo para agregar atributos para la vista.
     * @return
     */
    @RequestMapping("/VerNotaPorId")
    public String buscarNotaPorId(@RequestParam("nota_id") String notaId, Model model, Principal principal) {
        String email = principal.getName();
        Usuario usuario = usuarioService.buscarPorEmail(email);

        try {
            int id = Integer.parseInt(notaId);
            Optional<Nota> opcionalNota = notaService.buscarPorId(id);
            
            //compara si la nota obtenida es del usuario logeado, si no manda error
            if (opcionalNota.isPresent() && opcionalNota.get().getUser_id().getUserId() == usuario.getUserId()) {
                model.addAttribute("nota", opcionalNota.get());
                model.addAttribute("categorias", categoriaService.buscarTodos());
                return "view_note.html";
            } else {
                model.addAttribute("mensajeError", "Nota no encontrada o no autorizada.");
                return "error";
            }

        } catch (NumberFormatException e) {
            model.addAttribute("mensajeError", "ID de nota inválido");
            return "error";
        }
    }

}
