package com.github.isaac.gui.forms;

import com.github.isaac.entities.Cliente;

import javax.swing.*;
import java.awt.*;

public class FormCliente extends JPanel {
    private final JTextField nombreField = new JTextField();
    private final JTextField apellidoField = new JTextField();
    private final JTextField telefonoField = new JTextField();
    private final JTextField direccionField = new JTextField();
    private final JTextField direccionEnvioField = new JTextField();
    private final JTextField nifField = new JTextField();

    public FormCliente() {
        this.setLayout(new GridLayout(3,2 , 10,10));
        this.createLayout();
    }

    public FormCliente(Cliente cliente) {
        this.setLayout(new GridLayout(3,2 , 10,10));
        this.createLayout(cliente);
    }

    public void createLayout() {
        createLayout(null);
    }

    public void createLayout(Cliente cliente) {
        // ----- Nombre -----
        JPanel nombrePanel = new JPanel(new BorderLayout());
        nombrePanel.add(nombreField);
        nombrePanel.setBorder(BorderFactory.createTitledBorder("Nombre"));

        // ----- Apellido -----
        JPanel apellidoPanel = new JPanel(new BorderLayout());
        apellidoPanel.add(apellidoField);
        apellidoPanel.setBorder(BorderFactory.createTitledBorder("Apellido"));

        // ----- NIF -----
        JPanel nifPanel = new JPanel(new BorderLayout());
        nifPanel.add(nifField);
        nifPanel.setBorder(BorderFactory.createTitledBorder("NIF"));

        // ----- Teléfono -----
        JPanel telefonoPanel = new JPanel(new BorderLayout());
        telefonoPanel.add(telefonoField);
        telefonoPanel.setBorder(BorderFactory.createTitledBorder("Telefono"));

        // ----- Dirección -----
        JPanel direccionPanel = new JPanel(new BorderLayout());
        direccionPanel.add(direccionField);
        direccionPanel.setBorder(BorderFactory.createTitledBorder("Direccion"));

        // ----- Dirección de Envío -----
        JPanel direccionEnvioPanel = new JPanel(new BorderLayout());
        direccionEnvioPanel.add(direccionEnvioField);
        direccionEnvioPanel.setBorder(BorderFactory.createTitledBorder("Direccion de Envio"));

        // ----- Campo clientes existentes -----
        if (cliente != null) {
            nombreField.setText(cliente.getNombre());
            apellidoField.setText(cliente.getApellidos());
            nifField.setText(cliente.getNif());
            telefonoField.setText(cliente.getTelefono());
            direccionField.setText(cliente.getDireccion());
            direccionEnvioField.setText(cliente.getDireccionEnvio());
        }

        // ---- Components Addition ----
        this.add(nombrePanel);
        this.add(apellidoPanel);
        this.add(nifPanel);
        this.add(telefonoPanel);
        this.add(direccionPanel);
        this.add(direccionEnvioPanel);
    }

    public Cliente getClienteFromForm() {
        Cliente cliente = new Cliente();

        if (!nombreField.getText().isBlank()) {
            cliente.setNombre(nombreField.getText());
        }

        if (!apellidoField.getText().isBlank()) {
            cliente.setApellidos(apellidoField.getText());
        }

        if (!nifField.getText().isBlank()) {
            cliente.setNif(nifField.getText());
        }

        if (!telefonoField.getText().isBlank()) {
            cliente.setTelefono(telefonoField.getText());
        }

        if (!direccionField.getText().isBlank()) {
            cliente.setDireccion(direccionField.getText());
        }

        if (!direccionEnvioField.getText().isBlank()) {
            cliente.setDireccionEnvio(direccionEnvioField.getText());
        }

        return cliente;
    }
}
