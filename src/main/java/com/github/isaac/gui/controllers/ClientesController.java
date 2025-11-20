package com.github.isaac.gui.controllers;

import com.github.isaac.entities.Cliente;
import com.github.isaac.gui.forms.FormCliente;
import com.github.isaac.gui.models.ClienteTableModel;
import com.github.isaac.gui.utils.CaptureExceptions;
import com.github.isaac.repositories.ClienteRepository;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class ClientesController {
    private final ClienteRepository clienteRepository = new ClienteRepository();
    private final ClienteTableModel clienteTableModel = new ClienteTableModel(clienteRepository.findAll());
    private final JTable table = new JTable(clienteTableModel);
    private final Component parentComponent;

    public ClientesController(Component parentComponent) {
        this.parentComponent = parentComponent;
    }

    // Metodos crud
    public void eliminarCliente() {
        Cliente cliente = getSelectedCliente();

        if (cliente != null) {
            clienteRepository.delete(cliente);
            clienteTableModel.setClientes(clienteRepository.findAll());
            JOptionPane.showMessageDialog(parentComponent, "Cliente eliminado");
        }
    }

    private Cliente getSelectedCliente() {
        if (table.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(parentComponent, "Seleccione un cliente de la tabla");
            return null;
        }

        return clienteTableModel.getClienteAt(table.getSelectedRow());
    }

    public void crearCliente() {
        FormCliente formCliente = new FormCliente();

        int result = JOptionPane.showConfirmDialog(
                parentComponent,
                formCliente,
                "Crear Cliente",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            CaptureExceptions.capture(parentComponent, () -> {
                Cliente nuevoCliente = formCliente.getClienteFromForm();
                clienteRepository.save(nuevoCliente);
                clienteTableModel.setClientes(clienteRepository.findAll());
                JOptionPane.showMessageDialog(parentComponent, "Cliente creado");
            });
        }
    }

    public void editarCliente() {
        Cliente cliente = getSelectedCliente();

        if (cliente != null) {
            FormCliente formCliente = new FormCliente(cliente);

            int result = JOptionPane.showConfirmDialog(
                    parentComponent,
                    formCliente,
                    "Editar Cliente",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );

            if (result == JOptionPane.OK_OPTION) {
                CaptureExceptions.capture(parentComponent, () -> {
                    Cliente clienteEditado = formCliente.getClienteFromForm();
                    clienteEditado.setId(cliente.getId()); // Mantener el mismo ID
                    clienteRepository.update(clienteEditado);
                    clienteTableModel.setClientes(clienteRepository.findAll());
                    JOptionPane.showMessageDialog(parentComponent, "Cliente editado");
                });
            }
        }
    }

}
