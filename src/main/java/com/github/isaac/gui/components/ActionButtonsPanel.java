package com.github.isaac.gui.components;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class ActionButtonsPanel extends JPanel {
    public enum Action {
        INSERTAR, EDITAR, ELIMINAR
    }

    public ActionButtonsPanel(Consumer<Action> callback) {
        setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        JButton btnInsertar = new JButton("Insertar");
        JButton btnEditar = new JButton("Editar");
        JButton btnEliminar = new JButton("Eliminar");

        btnInsertar.addActionListener(e -> callback.accept(Action.INSERTAR));
        btnEditar.addActionListener(e -> callback.accept(Action.EDITAR));
        btnEliminar.addActionListener(e -> callback.accept(Action.ELIMINAR));

        add(btnInsertar);
        add(btnEditar);
        add(btnEliminar);
    }
}

