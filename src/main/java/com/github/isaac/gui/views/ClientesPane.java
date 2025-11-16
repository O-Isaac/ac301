package com.github.isaac.gui.views;

import com.github.isaac.entities.Cliente;
import com.github.isaac.gui.components.ActionButtonsPanel;
import com.github.isaac.gui.models.ClienteTableModel;
import com.github.isaac.repositories.ClienteRepository;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ClientesPane extends JPanel {
    private final ClienteRepository clienteRepository = new ClienteRepository();
    private final ClienteTableModel clienteTableModel = new ClienteTableModel(new ArrayList<>());
    private final JTable table = new JTable(clienteTableModel);

    private void deleteCliente(Cliente cliente) {
        clienteRepository.delete(cliente);
        clienteTableModel.setClientes(clienteRepository.findAll());

        JOptionPane.showMessageDialog(this, "Cliente eliminado");
    }

    private void actionHandler(ActionButtonsPanel.Action action) {
        if (table.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un cliente de la tabla");
            return;
        }

        Cliente cliente = clienteTableModel.getClienteAt(table.getSelectedRow());

        switch (action) {
            case INSERTAR -> JOptionPane.showMessageDialog(this, "Insertar cliente");
            case EDITAR -> JOptionPane.showMessageDialog(this, "Editar cliente");
            case ELIMINAR -> deleteCliente(cliente);
        }
    }

    public ClientesPane() {
        List<Cliente> clientes = clienteRepository.findAll();
        clienteTableModel.setClientes(clientes);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        ActionButtonsPanel actionButtons = new ActionButtonsPanel(this::actionHandler);
        add(actionButtons, BorderLayout.NORTH);
    }
}
