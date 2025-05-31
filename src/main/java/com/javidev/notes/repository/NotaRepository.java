/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.javidev.notes.repository;

import com.javidev.notes.controller.domain.Nota;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author music
 */
@Repository
public interface NotaRepository extends JpaRepository <Nota, Integer>{
    @Query("Select v from Nota v order by v.nota_last_date desc")
    List <Nota> buscarTodos();
    
    @Query("Select v from Nota v where v.cat_id.cat_id = ?1 order by v.nota_last_date desc")
    List <Nota> buscarPorCategoria(int cat_id);
    
    @Query("from Nota v where v.nota_title like %?1%")
    List <Nota> buscar(String consulta);
    
    //solo se utilizan las siguientes consultas si se quiere que cada usuario tenga su propia informacion sin acceso a otro usuario
    @Query("SELECT n FROM Nota n WHERE n.user_id.userId = :userId ORDER BY n.nota_last_date DESC")
    List<Nota> buscarNuevasNotasPorUsuario(@Param("userId") int userId);
    
    @Query("SELECT n FROM Nota n WHERE n.cat_id.cat_id = :catId AND n.user_id.userId = :userId")
    List<Nota> buscarPorCategoriaUsuario(@Param("catId") int catId, @Param("userId") int userId);
    
    @Query("SELECT n FROM Nota n WHERE LOWER(n.nota_title) LIKE LOWER(CONCAT('%', :titulo, '%')) AND n.user_id.userId = :userId")
    List<Nota> buscarPorTituloUsuario(@Param("titulo") String titulo, @Param("userId") int userId);

}
