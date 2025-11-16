package com.github.isaac.gui.models;

import com.github.isaac.entities.Cliente;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ClienteTableModel extends AbstractTableModel {
    private List<Cliente> clientes;

    private enum Columns {
        ID,
        NIF,
        NOMBRE,
        APELLIDOS,
        TELEFONO,
        DIRECCION,
        DIRECCION_ENVIO
    }

    public ClienteTableModel(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    @Override
    public String getColumnName(int column) {
        return Columns.values()[column].name();
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
        fireTableDataChanged();
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public Cliente getClienteAt(int rowIndex) {
        return clientes.get(rowIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Cliente cliente = clientes.get(rowIndex);
        Columns column = Columns.values()[columnIndex];

        return switch (column) {
            case ID -> cliente.getId();
            case NIF -> cliente.getNif();
            case NOMBRE -> cliente.getNombre();
            case APELLIDOS -> cliente.getApellidos();
            case TELEFONO -> cliente.getTelefono();
            case DIRECCION -> cliente.getDireccion();
            case DIRECCION_ENVIO -> cliente.getDireccionEnvio();
        };
    }

    @Override
    public int getRowCount() {
        return clientes.size();
    }

    @Override
    public int getColumnCount() {
        return Columns.values().length;
    }
}
