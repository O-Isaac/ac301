package com.github.isaac.gui.models;

import com.github.isaac.entities.Producto;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ProductoTableModel extends AbstractTableModel {
    private List<Producto> productos;

    private enum Columns {
        ID,
        CODIGO,
        NOMBRE,
        DESCRIPCION,
        PRECIO,
        STOCK
    }

    public ProductoTableModel(List<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public String getColumnName(int column) {
        return Columns.values()[column].name();
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
        fireTableDataChanged();
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public Producto getProductoAt(int rowIndex) {
        return productos.get(rowIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Producto producto = productos.get(rowIndex);
        Columns column = Columns.values()[columnIndex];

        return switch (column) {
            case ID -> producto.getId();
            case CODIGO -> producto.getCodigo();
            case NOMBRE -> producto.getNombre();
            case DESCRIPCION -> producto.getDescripcion();
            case PRECIO -> producto.getPrecio();
            case STOCK -> producto.getStock();
        };
    }

    @Override
    public int getRowCount() {
        return productos.size();
    }

    @Override
    public int getColumnCount() {
        return Columns.values().length;
    }
}
