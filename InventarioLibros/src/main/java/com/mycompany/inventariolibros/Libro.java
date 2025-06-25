/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventariolibros;

import java.io.Serializable;

/**
 *
 * @author HP
 */
public class Libro implements Serializable, DescuentoAplicable {

    private String titulo;
    private String autor;
    private String editorial;
    private String isbn;
    private double precio;
    private int cantidad;

    public Libro(String titulo, String autor, String editorial, String isbn, double precio, int cantidad) {
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.isbn = isbn;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public String getIsbn() {
        return isbn;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double calcularPrecioConDescuento() {
        return cantidad > 10 ? precio * 0.9 : precio;
    }

}
