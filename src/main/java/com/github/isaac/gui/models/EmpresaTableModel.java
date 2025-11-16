package com.github.isaac.gui.models;

import com.github.isaac.entities.Empresa;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class EmpresaTableModel extends AbstractTableModel {
    private List<Empresa> empresas;

    private enum Columns {
        ID,
        CIF,
        NOMBRE,
        LOCALIDAD,
        DOMICILIO,
        TELEFONO,
        EMAIL
    }

    public EmpresaTableModel(List<Empresa> empresas) {
        this.empresas = empresas;
    }

    @Override
    public String getColumnName(int column) {
        return Columns.values()[column].name();
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
        fireTableDataChanged();
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public Empresa getEmpresaAt(int rowIndex) {
        return empresas.get(rowIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Empresa empresa = empresas.get(rowIndex);
        Columns column = Columns.values()[columnIndex];

        return switch (column) {
            case ID -> empresa.getId();
            case CIF -> empresa.getCif();
            case NOMBRE -> empresa.getNombre();
            case LOCALIDAD -> empresa.getLocalidad();
            case DOMICILIO -> empresa.getDomicilio();
            case TELEFONO -> empresa.getTelefono();
            case EMAIL -> empresa.getEmail();
        };
    }

    @Override
    public int getRowCount() {
        return empresas.size();
    }

    @Override
    public int getColumnCount() {
        return Columns.values().length;
    }
}
