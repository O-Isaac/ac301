package com.github.isaac.gui.views;

import com.github.isaac.entities.Pedido;
import com.github.isaac.gui.components.ActionButtonsPanel;
import com.github.isaac.gui.models.PedidoTableModel;
import com.github.isaac.repositories.PedidoRepository;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoPane extends JPanel {
    private final PedidoRepository pedidoRepository = new PedidoRepository();
    private final PedidoTableModel pedidoTableModel = new PedidoTableModel(new ArrayList<>());
    private final JTable table = new JTable(pedidoTableModel);

    private void deletePedido(Pedido pedido) {
        pedidoRepository.delete(pedido);
        pedidoTableModel.setPedidos(pedidoRepository.findAll());

        JOptionPane.showMessageDialog(this, "Pedido eliminado");
    }

    private void actionHandler(ActionButtonsPanel.Action action) {
        if (table.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un pedido de la tabla");
            return;
        }

        Pedido pedido = pedidoTableModel.getPedidoAt(table.getSelectedRow());

        switch (action) {
            case INSERTAR -> JOptionPane.showMessageDialog(this, "Insertar pedido");
            case EDITAR -> JOptionPane.showMessageDialog(this, "Editar pedido");
            case ELIMINAR -> deletePedido(pedido);
        }
    }

    public PedidoPane() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        pedidoTableModel.setPedidos(pedidos);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        ActionButtonsPanel actionButtons = new ActionButtonsPanel(this::actionHandler);
        add(actionButtons, BorderLayout.NORTH);
    }
}

