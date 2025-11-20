package com.github.isaac.gui.controllers;

import com.github.isaac.entities.Producto;
import com.github.isaac.gui.forms.FormProducto;
import com.github.isaac.gui.models.ProductoTableModel;
import com.github.isaac.gui.utils.CaptureExceptions;
import com.github.isaac.repositories.ProductoRepository;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

@Getter
public class ProductoController {
    private final ProductoRepository productoRepository = new ProductoRepository();
    private final ProductoTableModel productoTableModel = new ProductoTableModel(productoRepository.findAll());
    private final JTable table = new JTable(productoTableModel);
    private final Component parentComponent;

    public ProductoController(Component parentComponent) {
        this.parentComponent = parentComponent;
    }

    public void eliminarProducto() {
        Producto producto = getSelectedProducto();
        if (producto != null) {
            productoRepository.deleteById(producto.getId());
            productoTableModel.setProductos(productoRepository.findAll());
            JOptionPane.showMessageDialog(parentComponent, "Producto eliminado");
        }
    }

    private Producto getSelectedProducto() {
        if (table.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(parentComponent, "Seleccione un producto de la tabla");
            return null;
        }
        return productoTableModel.getProductoAt(table.getSelectedRow());
    }

    public void crearProducto() {
        FormProducto formProducto = new FormProducto();

        int result = JOptionPane.showConfirmDialog(
                parentComponent,
                formProducto,
                "Crear Producto",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            CaptureExceptions.capture(parentComponent, () -> {
                Producto nuevoProducto = formProducto.getProductoFromForm();
                // Validación mínima adicional (precio y stock no nulos)
                if (nuevoProducto.getPrecio() == null || nuevoProducto.getPrecio().compareTo(BigDecimal.ZERO) <= 0) {
                    JOptionPane.showMessageDialog(parentComponent, "Precio inválido", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (nuevoProducto.getStock() == null || nuevoProducto.getStock() < 0) {
                    JOptionPane.showMessageDialog(parentComponent, "Stock inválido", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                productoRepository.save(nuevoProducto);
                productoTableModel.setProductos(productoRepository.findAll());
                JOptionPane.showMessageDialog(parentComponent, "Producto creado");
            });
        }
    }

    public void editarProducto() {
        Producto producto = getSelectedProducto();
        if (producto != null) {
            FormProducto formProducto = new FormProducto(producto);

            int result = JOptionPane.showConfirmDialog(
                    parentComponent,
                    formProducto,
                    "Editar Producto",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );

            if (result == JOptionPane.OK_OPTION) {
                CaptureExceptions.capture(parentComponent, () -> {
                    Producto productoEditado = formProducto.getProductoFromForm();
                    productoEditado.setId(producto.getId());
                    productoRepository.update(productoEditado);
                    productoTableModel.setProductos(productoRepository.findAll());
                    JOptionPane.showMessageDialog(parentComponent, "Producto editado");
                });
            }
        }
    }
}
