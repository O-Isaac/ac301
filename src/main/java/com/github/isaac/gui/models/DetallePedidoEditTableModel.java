package com.github.isaac.gui.models;

import com.github.isaac.entities.DetallePedido;
import com.github.isaac.entities.Producto;

import javax.swing.table.AbstractTableModel;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DetallePedidoEditTableModel extends AbstractTableModel {
    private final List<DetallePedido> detalles = new ArrayList<>();

    private enum Columns {
        PRODUCTO,
        CANTIDAD,
        PRECIO_UNITARIO,
        SUBTOTAL
    }

    @Override
    public String getColumnName(int column) {
        return Columns.values()[column].name();
    }

    @Override
    public int getRowCount() {
        return detalles.size();
    }

    @Override
    public int getColumnCount() {
        return Columns.values().length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DetallePedido d = detalles.get(rowIndex);
        return switch (Columns.values()[columnIndex]) {
            case PRODUCTO -> d.getProducto() != null ? d.getProducto().getNombre() : null;
            case CANTIDAD -> d.getCantidad();
            case PRECIO_UNITARIO -> d.getPrecioUnitario();
            case SUBTOTAL -> d.getSubtotal();
        };
    }

    public void addDetalle(Producto producto, int cantidad) {
        if (producto == null || cantidad <= 0) return;
        // Si ya existe el producto, sumar cantidad y conservar ID si existe
        for (DetallePedido existente : detalles) {
            if (existente.getProducto().getId().equals(producto.getId())) {
                existente.setCantidad(existente.getCantidad() + cantidad);
                existente.setPrecioUnitario(producto.getPrecio());
                existente.setSubtotal(producto.getPrecio().multiply(BigDecimal.valueOf(existente.getCantidad())));
                fireTableDataChanged();
                return;
            }
        }
        DetallePedido nuevo = new DetallePedido();
        nuevo.setProducto(producto);
        nuevo.setCantidad(cantidad);
        nuevo.setPrecioUnitario(producto.getPrecio());
        nuevo.setSubtotal(producto.getPrecio().multiply(BigDecimal.valueOf(cantidad)));
        detalles.add(nuevo);
        fireTableRowsInserted(detalles.size() - 1, detalles.size() - 1);
    }

    // Añadir detalle desde pedido existente (conserva ID para edición)
    public void addDetalleExistente(DetallePedido detalle) {
        if (detalle == null || detalle.getProducto() == null) return;

        // Si ya existe el producto en la lista, actualizar conservando el primer ID encontrado
        for (DetallePedido existente : detalles) {
            if (existente.getProducto().getId().equals(detalle.getProducto().getId())) {
                existente.setCantidad(existente.getCantidad() + detalle.getCantidad());
                existente.setPrecioUnitario(detalle.getPrecioUnitario());
                existente.setSubtotal(detalle.getPrecioUnitario().multiply(BigDecimal.valueOf(existente.getCantidad())));
                // Conservar el ID del detalle original si no tiene
                if (existente.getId() == null && detalle.getId() != null) {
                    existente.setId(detalle.getId());
                }
                fireTableDataChanged();
                return;
            }
        }

        // Agregar detalle nuevo conservando su ID si lo tiene
        DetallePedido copia = new DetallePedido();
        copia.setId(detalle.getId()); // Conserva ID para edición
        copia.setProducto(detalle.getProducto());
        copia.setCantidad(detalle.getCantidad());
        copia.setPrecioUnitario(detalle.getPrecioUnitario());
        copia.setSubtotal(detalle.getSubtotal());
        detalles.add(copia);
        fireTableRowsInserted(detalles.size() - 1, detalles.size() - 1);
    }

    public void removeAt(int rowIndex) {
        if (rowIndex < 0 || rowIndex >= detalles.size()) return;
        detalles.remove(rowIndex);
        fireTableDataChanged();
    }

    public void clear() {
        detalles.clear();
        fireTableDataChanged();
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public BigDecimal calcularTotal() {
        return detalles.stream()
                .map(DetallePedido::getSubtotal)
                .filter(s -> s != null)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

