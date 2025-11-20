package com.github.isaac.gui.views;

import com.github.isaac.gui.components.ActionButtonsPanel;
import com.github.isaac.gui.controllers.ClientesController;

import javax.swing.*;
import java.awt.*;

public class ClientesPane extends JPanel {
    private final ClientesController controller = new ClientesController(this);

    private void actionHandler(ActionButtonsPanel.Action action) {
        switch (action) {
            case INSERTAR -> controller.crearCliente();
            case EDITAR -> controller.editarCliente();
            case ELIMINAR -> controller.eliminarCliente();
        }
    }

    public ClientesPane() {
        JScrollPane scrollPane = new JScrollPane(controller.getTable());
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        ActionButtonsPanel actionButtons = new ActionButtonsPanel(this::actionHandler);
        add(actionButtons, BorderLayout.NORTH);
    }
}
