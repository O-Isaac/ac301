package com.github.isaac.gui.views;

import com.github.isaac.gui.components.ActionButtonsPanel;
import com.github.isaac.gui.controllers.ProductoController;

import javax.swing.*;
import java.awt.*;

public class ProductoPane extends JPanel {
    private final ProductoController controller = new ProductoController(this);

    private void actionHandler(ActionButtonsPanel.Action action) {
        switch (action) {
            case INSERTAR -> controller.crearProducto();
            case EDITAR -> controller.editarProducto();
            case ELIMINAR -> controller.eliminarProducto();
        }
    }

    public ProductoPane() {
        JScrollPane scrollPane = new JScrollPane(controller.getTable());
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        ActionButtonsPanel actionButtons = new ActionButtonsPanel(this::actionHandler);
        add(actionButtons, BorderLayout.NORTH);
    }
}
