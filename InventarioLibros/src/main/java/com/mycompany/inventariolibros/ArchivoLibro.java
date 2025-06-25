/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventariolibros;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class ArchivoLibro {

    public static void guardar(ArrayList<Libro> libros, String ruta) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta));
        oos.writeObject(libros);
        oos.close();
    }

    public static ArrayList<Libro> cargar(String ruta) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta));
        ArrayList<Libro> libros = (ArrayList<Libro>) ois.readObject();
        ois.close();
        return libros;
    }

}
