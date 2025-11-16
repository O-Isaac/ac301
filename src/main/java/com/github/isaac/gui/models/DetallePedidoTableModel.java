package com.github.isaac.gui.models;

import com.github.isaac.entities.DetallePedido;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class DetallePedidoTableModel extends AbstractTableModel {
    private List<DetallePedido> detalles;

    private enum Columns {
        ID,
        PEDIDO_ID,
        PRODUCTO_ID,
        CANTIDAD,
        PRECIO_UNITARIO,
        SUBTOTAL
    }

    public DetallePedidoTableModel(List<DetallePedido> detalles) {
        this.detalles = detalles;
    }

    @Override
    public String getColumnName(int column) {
        return Columns.values()[column].name();
    }

    public void setDetalles(List<DetallePedido> detalles) {
        this.detalles = detalles;
        fireTableDataChanged();
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public DetallePedido getDetalleAt(int rowIndex) {
        return detalles.get(rowIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        DetallePedido detalle = detalles.get(rowIndex);
        Columns column = Columns.values()[columnIndex];

        return switch (column) {
            case ID -> detalle.getId();
            case PEDIDO_ID -> detalle.getPedido() != null ? detalle.getPedido().getId() : null;
            case PRODUCTO_ID -> detalle.getProducto() != null ? detalle.getProducto().getId() : null;
            case CANTIDAD -> detalle.getCantidad();
            case PRECIO_UNITARIO -> detalle.getPrecioUnitario();
            case SUBTOTAL -> detalle.getSubtotal();
        };
    }

    @Override
    public int getRowCount() {
        return detalles.size();
    }

    @Override
    public int getColumnCount() {
        return Columns.values().length;
    }
}
