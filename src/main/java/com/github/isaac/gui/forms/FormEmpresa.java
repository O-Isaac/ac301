package com.github.isaac.gui.forms;

import com.github.isaac.entities.Empresa;

import javax.swing.*;
import java.awt.*;

public class FormEmpresa extends JPanel {
    private JTextField cifField = new JTextField();
    private JTextField telefonoField = new JTextField();
    private JTextField nombreField = new JTextField();
    private JTextField localidadField = new JTextField();
    private JTextField domicilioField = new JTextField();
    private JTextField emailField = new JTextField();

    public FormEmpresa() {
        this.setLayout(new GridLayout(3, 2, 10, 10));
        this.createLayout();
    }

    public void createLayout() {
        // ----- CIF -----
        JPanel cifPanel = new JPanel(new BorderLayout());
        cifPanel.add(cifField);
        cifPanel.setBorder(BorderFactory.createTitledBorder("CIF"));

        // ----- Teléfono -----
        JPanel telefonoPanel = new JPanel(new BorderLayout());
        telefonoPanel.add(telefonoField);
        telefonoPanel.setBorder(BorderFactory.createTitledBorder("Telefono"));

        // ----- Nombre -----
        JPanel nombrePanel = new JPanel(new BorderLayout());
        nombrePanel.add(nombreField);
        nombrePanel.setBorder(BorderFactory.createTitledBorder("Nombre"));

        // ----- Localidad -----
        JPanel localidadPanel = new JPanel(new BorderLayout());
        localidadPanel.add(localidadField);
        localidadPanel.setBorder(BorderFactory.createTitledBorder("Localidad"));

        // ----- Domicilio -----
        JPanel domicilioPanel = new JPanel(new BorderLayout());
        domicilioPanel.add(domicilioField);
        domicilioPanel.setBorder(BorderFactory.createTitledBorder("Domicilio"));

        // ----- Email -----
        JPanel emailPanel = new JPanel(new BorderLayout());
        emailPanel.add(emailField);
        emailPanel.setBorder(BorderFactory.createTitledBorder("Email"));

        // ---- Components Addition ----
        this.add(cifPanel);
        this.add(telefonoPanel);
        this.add(nombrePanel);
        this.add(localidadPanel);
        this.add(domicilioPanel);
        this.add(emailPanel);
    }

    public Empresa getEmpresaFromForm() {
        Empresa empresa = new Empresa();

        if (!cifField.getText().isBlank()) {
            empresa.setCif(cifField.getText());
        }

        if (!telefonoField.getText().isBlank()) {
            empresa.setTelefono(telefonoField.getText());
        }

        if (!nombreField.getText().isBlank()) {
            empresa.setNombre(nombreField.getText());
        }

        if (!localidadField.getText().isBlank()) {
            empresa.setLocalidad(localidadField.getText());
        }

        if (!domicilioField.getText().isBlank()) {
            empresa.setDomicilio(domicilioField.getText());
        }

        if (!emailField.getText().isBlank()) {
            empresa.setEmail(emailField.getText());
        }

        return empresa;
    }
}

