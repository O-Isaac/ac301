package com.github.isaac.gui.views;

import com.github.isaac.entities.DetallePedido;
import com.github.isaac.gui.components.ActionButtonsPanel;
import com.github.isaac.gui.models.DetallePedidoTableModel;
import com.github.isaac.repositories.DetallePedidoRepository;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DetallePedidoPane extends JPanel {
    private final DetallePedidoRepository detallePedidoRepository = new DetallePedidoRepository();
    private final DetallePedidoTableModel detallePedidoTableModel = new DetallePedidoTableModel(new ArrayList<>());
    private final JTable table = new JTable(detallePedidoTableModel);

    private void deleteDetalle(DetallePedido detalle) {
        detallePedidoRepository.delete(detalle);
        detallePedidoTableModel.setDetalles(detallePedidoRepository.findAll());

        JOptionPane.showMessageDialog(this, "Detalle de pedido eliminado");
    }

    private void actionHandler(ActionButtonsPanel.Action action) {
        if (table.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un detalle de la tabla");
            return;
        }

        DetallePedido detalle = detallePedidoTableModel.getDetalleAt(table.getSelectedRow());

        switch (action) {
            case INSERTAR -> JOptionPane.showMessageDialog(this, "Insertar detalle de pedido");
            case EDITAR -> JOptionPane.showMessageDialog(this, "Editar detalle de pedido");
            case ELIMINAR -> deleteDetalle(detalle);
        }
    }

    public DetallePedidoPane() {
        List<DetallePedido> detalles = detallePedidoRepository.findAll();
        detallePedidoTableModel.setDetalles(detalles);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        ActionButtonsPanel actionButtons = new ActionButtonsPanel(this::actionHandler);
        add(actionButtons, BorderLayout.NORTH);
    }
}

