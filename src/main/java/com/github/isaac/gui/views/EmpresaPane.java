package com.github.isaac.gui.views;

import com.github.isaac.gui.components.ActionButtonsPanel;
import com.github.isaac.gui.controllers.EmpresaController;

import javax.swing.*;
import java.awt.*;

public class EmpresaPane extends JPanel {
    private final EmpresaController controller = new EmpresaController(this);

    private void actionHandler(ActionButtonsPanel.Action action) {
        switch (action) {
            case INSERTAR -> controller.crearEmpresa();
            case EDITAR -> controller.editarEmpresa();
            case ELIMINAR -> controller.eliminarEmpresa();
        }
    }

    public EmpresaPane() {
        JScrollPane scrollPane = new JScrollPane(controller.getTable());
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        ActionButtonsPanel actionButtons = new ActionButtonsPanel(this::actionHandler);
        add(actionButtons, BorderLayout.NORTH);
    }
}
