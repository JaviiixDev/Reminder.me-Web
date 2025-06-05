/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javidev.notes.controller.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;

/**
 *Clase que actua como puente de la base de datos, representa la tabla de la base de datos nota como objetos
 * @author music
 */
@Entity
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int nota_id;
    private String nota_title;
    private String nota_content;
    private LocalDate nota_create_date;
    private LocalDate nota_last_date;

    @ManyToOne
    @JoinColumn(name = "cat_id")
    private Categoria cat_id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usuario user_id;

    public int getNota_id() {
        return nota_id;
    }

    public void setNota_id(int nota_id) {
        this.nota_id = nota_id;
    }

    public String getNota_title() {
        return nota_title;
    }

    public void setNota_title(String nota_title) {
        this.nota_title = nota_title;
    }

    public String getNota_content() {
        return nota_content;
    }

    public void setNota_content(String nota_content) {
        this.nota_content = nota_content;
    }

    public LocalDate getNota_create_date() {
        return nota_create_date;
    }

    public void setNota_create_date(LocalDate nota_create_date) {
        this.nota_create_date = nota_create_date;
    }

    public LocalDate getNota_last_date() {
        return nota_last_date;
    }

    public void setNota_last_date(LocalDate nota_last_date) {
        this.nota_last_date = nota_last_date;
    }

    public Categoria getCat_id() {
        return cat_id;
    }

    public void setCat_id(Categoria cat_id) {
        this.cat_id = cat_id;
    }

    public Usuario getUser_id() {
        return user_id;
    }

    public void setUser_id(Usuario user_id) {
        this.user_id = user_id;
    }
}
