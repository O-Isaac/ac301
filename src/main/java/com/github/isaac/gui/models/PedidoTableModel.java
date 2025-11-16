package com.github.isaac.gui.models;

import com.github.isaac.entities.Pedido;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PedidoTableModel extends AbstractTableModel {
    private List<Pedido> pedidos;

    private enum Columns {
        ID,
        CLIENTE_ID,
        EMPRESA_ID,
        TOTAL,
        FECHA,
        ESTADO
    }

    public PedidoTableModel(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public String getColumnName(int column) {
        return Columns.values()[column].name();
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
        fireTableDataChanged();
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public Pedido getPedidoAt(int rowIndex) {
        return pedidos.get(rowIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pedido pedido = pedidos.get(rowIndex);
        Columns column = Columns.values()[columnIndex];

        return switch (column) {
            case ID -> pedido.getId();
            case CLIENTE_ID -> pedido.getCliente() != null ? pedido.getCliente().getId() : null;
            case EMPRESA_ID -> pedido.getEmpresa() != null ? pedido.getEmpresa().getId() : null;
            case TOTAL -> pedido.getTotal();
            case FECHA -> pedido.getFecha();
            case ESTADO -> pedido.getEstado();
        };
    }

    @Override
    public int getRowCount() {
        return pedidos.size();
    }

    @Override
    public int getColumnCount() {
        return Columns.values().length;
    }
}
