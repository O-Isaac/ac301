package com.github.isaac.gui.components;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class ActionButtonsPanel extends JPanel {
    public enum Action {
        INSERTAR, EDITAR, ELIMINAR, CONFIRMAR // Agregada acción CONFIRMAR
    }

    public ActionButtonsPanel(Consumer<Action> callback) {
        setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        JButton btnInsertar = new JButton("Insertar");
        JButton btnEditar = new JButton("Editar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnConfirmar = new JButton("Confirmar"); // Nuevo botón

        btnInsertar.addActionListener(e -> callback.accept(Action.INSERTAR));
        btnEditar.addActionListener(e -> callback.accept(Action.EDITAR));
        btnEliminar.addActionListener(e -> callback.accept(Action.ELIMINAR));
        btnConfirmar.addActionListener(e -> callback.accept(Action.CONFIRMAR));

        add(btnInsertar);
        add(btnEditar);
        add(btnEliminar);
        add(btnConfirmar); // Se añade al panel
    }
}
