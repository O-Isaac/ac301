package com.github.isaac.gui.views;

import com.github.isaac.entities.Producto;
import com.github.isaac.gui.components.ActionButtonsPanel;
import com.github.isaac.gui.models.ProductoTableModel;
import com.github.isaac.repositories.ProductoRepository;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoPane extends JPanel {
    private final ProductoRepository productoRepository = new ProductoRepository();
    private final ProductoTableModel productoTableModel = new ProductoTableModel(new ArrayList<>());
    private final JTable table = new JTable(productoTableModel);

    private void deleteProducto(Producto producto) {
        productoRepository.delete(producto);
        productoTableModel.setProductos(productoRepository.findAll());

        JOptionPane.showMessageDialog(this, "Producto eliminado");
    }

    private void actionHandler(ActionButtonsPanel.Action action) {
        if (table.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto de la tabla");
            return;
        }

        Producto producto = productoTableModel.getProductoAt(table.getSelectedRow());

        switch (action) {
            case INSERTAR -> JOptionPane.showMessageDialog(this, "Insertar producto");
            case EDITAR -> JOptionPane.showMessageDialog(this, "Editar producto");
            case ELIMINAR -> deleteProducto(producto);
        }
    }

    public ProductoPane() {
        List<Producto> productos = productoRepository.findAll();
        productoTableModel.setProductos(productos);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        ActionButtonsPanel actionButtons = new ActionButtonsPanel(this::actionHandler);
        add(actionButtons, BorderLayout.NORTH);
    }
}
