/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inventariolibros;

import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class VentanaPrincipal extends JFrame {

    private Inventario inventario = new Inventario();
    private JTable tabla;
    private DefaultTableModel modelo;
    private JTextField tfTitulo, tfAutor, tfEditorial, tfISBN, tfPrecio, tfCantidad;
    private final String ARCHIVO = "Libros.dat";

    public VentanaPrincipal() {
        setTitle("Inventario de Libros");
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        //Panel del formulario
        JPanel panelFormulario = new JPanel(new GridLayout(7, 2));
        tfTitulo = new JTextField();
        tfAutor = new JTextField();
        tfEditorial = new JTextField();
        tfISBN = new JTextField();
        tfPrecio = new JTextField();
        tfCantidad = new JTextField();

        panelFormulario.add(new JLabel("Titulo:"));
        panelFormulario.add(tfTitulo);
        panelFormulario.add(new JLabel("Autor:"));
        panelFormulario.add(tfAutor);
        panelFormulario.add(new JLabel("Editorial:"));
        panelFormulario.add(tfEditorial);
        panelFormulario.add(new JLabel("Precio:"));
        panelFormulario.add(tfPrecio);
        panelFormulario.add(new JLabel("Cantidad:"));
        panelFormulario.add(tfCantidad);

        JButton btnAgregar = new JButton("Agregar Libro");
        JButton btnGuardar = new JButton("Guardar Libro");
        JButton btnCargar = new JButton("Cargar Libro");

        panelFormulario.add(btnAgregar);
        panelFormulario.add(btnGuardar);
        panelFormulario.add(btnCargar);

        add(panelFormulario, BorderLayout.NORTH);

        //Tabla
        modelo = new DefaultTableModel(new String[]{"Titulo", "Autor", "Editorial", "ISBN", "Precio", "Cantidad", "Precio Con Descuento"}, 0);
        tabla = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabla);
        add(scrollPane, BorderLayout.CENTER);

        //Acciones
        btnAgregar.addActionListener(e -> agregarLibro());
        btnGuardar.addActionListener(e -> guardarDatos());
        btnCargar.addActionListener(e -> cargarDatos());
    }

    public void agregarLibro() {
        try {
            String titulo = tfTitulo.getText().trim();
            String autor = tfAutor.getText().trim();
            String editorial = tfEditorial.getText().trim();
            String isbn = tfISBN.getText().trim();

            //Limpiar precio y cantidad: eliminar $, comas, espacios
            String precioTexto = tfPrecio.getText().replaceAll("[^0-9]", "");
            String cantidadTexto = tfCantidad.getText().replaceAll("[^0-9]", "");

            double precio = Double.parseDouble(precioTexto);
            int cantidad = Integer.parseInt(cantidadTexto);

            Libro libro = new Libro(titulo, autor, editorial, isbn, precio, cantidad);
            inventario.agregarLibro(libro);

            modelo.addRow(new Object[]{
                titulo, autor, editorial, isbn,
                precio, cantidad, libro.calcularPrecioConDescuento()
            });

            limpiarCampos();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Formato numerico invalido en Precio o Cantidad", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en los datos ingresados", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void guardarDatos() {
        try {
            ArchivoLibro.guardar(inventario.getLibros(), ARCHIVO);
            JOptionPane.showMessageDialog(this, "Datos guardados exitosamente");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cargarDatos() {
        try {
            ArrayList<Libro> libros = ArchivoLibro.cargar(ARCHIVO);
            inventario = new Inventario();
            modelo.setRowCount(0);
            for (Libro libro : libros) {
                inventario.agregarLibro(libro);
                modelo.addRow(new Object[]{
                    libro.getTitulo(), libro.getAutor(), libro.getEditorial(),
                    libro.getIsbn(), libro.getPrecio(),
                    libro.getCantidad(), libro.calcularPrecioConDescuento()
                });
            }
            JOptionPane.showMessageDialog(this, "Datos cargados exitosamente");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void limpiarCampos() {
        tfTitulo.setText("");
        tfAutor.setText("");
        tfEditorial.setText("");
        tfISBN.setText("");
        tfPrecio.setText("");
        tfCantidad.setText("");
    }
}
