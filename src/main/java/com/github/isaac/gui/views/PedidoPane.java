package com.github.isaac.gui.views;

import com.github.isaac.gui.components.ActionButtonsPanel;
import com.github.isaac.gui.controllers.PedidosController;

import javax.swing.*;
import java.awt.*;

public class PedidoPane extends JPanel {
    private final PedidosController controller = new PedidosController(this);

    private void actionHandler(ActionButtonsPanel.Action action) {
        switch (action) {
            case INSERTAR -> controller.crearPedido();
            case EDITAR -> controller.editarPedido();
            case ELIMINAR -> controller.eliminarPedido();
            case CONFIRMAR -> controller.confirmarPedido();
        }
    }

    public PedidoPane() {
        JScrollPane scrollPane = new JScrollPane(controller.getTable());
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        ActionButtonsPanel actionButtonsPanel = new ActionButtonsPanel(this::actionHandler);
        add(actionButtonsPanel, BorderLayout.NORTH);
    }
}
