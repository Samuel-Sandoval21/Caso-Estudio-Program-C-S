/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventariolibros;

import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class Inventario {

    private ArrayList<Libro> libros;

    public Inventario() {
        libros = new ArrayList<>();
    }

    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    public ArrayList<Libro> getLibros() {
        return libros;
    }

}
