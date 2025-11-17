package com.github.isaac.gui.forms;

import com.github.isaac.entities.Producto;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

public class FormProducto extends JPanel {
    private JTextField codigoField = new JTextField();
    private JTextField nombreField = new JTextField();
    private JTextField descripcionField = new JTextField();
    private JTextField precioField = new JTextField();
    private JTextField stockField = new JTextField();

    public FormProducto() {
        this.setLayout(new GridLayout(3, 2, 10, 10));
        this.createLayout();
    }

    public void createLayout() {
        // ----- Código -----
        JPanel codigoPanel = new JPanel(new BorderLayout());
        codigoPanel.add(codigoField);
        codigoPanel.setBorder(BorderFactory.createTitledBorder("Codigo"));

        // ----- Nombre -----
        JPanel nombrePanel = new JPanel(new BorderLayout());
        nombrePanel.add(nombreField);
        nombrePanel.setBorder(BorderFactory.createTitledBorder("Nombre"));

        // ----- Descripción -----
        JPanel descripcionPanel = new JPanel(new BorderLayout());
        descripcionPanel.add(descripcionField);
        descripcionPanel.setBorder(BorderFactory.createTitledBorder("Descripcion"));

        // ----- Precio -----
        JPanel precioPanel = new JPanel(new BorderLayout());
        precioPanel.add(precioField);
        precioPanel.setBorder(BorderFactory.createTitledBorder("Precio"));

        // ----- Stock -----
        JPanel stockPanel = new JPanel(new BorderLayout());
        stockPanel.add(stockField);
        stockPanel.setBorder(BorderFactory.createTitledBorder("Stock"));

        // ---- Components Addition ----
        this.add(codigoPanel);
        this.add(nombrePanel);
        this.add(descripcionPanel);
        this.add(precioPanel);
        this.add(stockPanel);
    }

    public Producto getProductoFromForm() {
        Producto producto = new Producto();

        if (!codigoField.getText().isBlank()) {
            producto.setCodigo(codigoField.getText());
        }

        if (!nombreField.getText().isBlank()) {
            producto.setNombre(nombreField.getText());
        }

        if (!descripcionField.getText().isBlank()) {
            producto.setDescripcion(descripcionField.getText());
        }

        if (!precioField.getText().isBlank()) {
            try {
                producto.setPrecio(new BigDecimal(precioField.getText()));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Precio no valido");
            }
        }

        if (!stockField.getText().isBlank()) {
            try {
                producto.setStock(Integer.parseInt(stockField.getText()));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Stock no valido");
            }
        }

        return producto;
    }
}

