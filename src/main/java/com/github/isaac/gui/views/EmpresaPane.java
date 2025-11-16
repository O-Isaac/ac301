package com.github.isaac.gui.views;

import com.github.isaac.entities.Empresa;
import com.github.isaac.gui.components.ActionButtonsPanel;
import com.github.isaac.gui.models.EmpresaTableModel;
import com.github.isaac.repositories.EmpresaRepository;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EmpresaPane extends JPanel {
    private final EmpresaRepository empresaRepository = new EmpresaRepository();
    private final EmpresaTableModel empresaTableModel = new EmpresaTableModel(new ArrayList<>());
    private final JTable table = new JTable(empresaTableModel);

    private void deleteEmpresa(Empresa empresa) {
        empresaRepository.delete(empresa);
        empresaTableModel.setEmpresas(empresaRepository.findAll());

        JOptionPane.showMessageDialog(this, "Empresa eliminada");
    }

    private void actionHandler(ActionButtonsPanel.Action action) {
        if (table.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una empresa de la tabla");
            return;
        }

        Empresa empresa = empresaTableModel.getEmpresaAt(table.getSelectedRow());

        switch (action) {
            case INSERTAR -> JOptionPane.showMessageDialog(this, "Insertar empresa");
            case EDITAR -> JOptionPane.showMessageDialog(this, "Editar empresa");
            case ELIMINAR -> deleteEmpresa(empresa);
        }
    }

    public EmpresaPane() {
        List<Empresa> empresas = empresaRepository.findAll();
        empresaTableModel.setEmpresas(empresas);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        ActionButtonsPanel actionButtons = new ActionButtonsPanel(this::actionHandler);
        add(actionButtons, BorderLayout.NORTH);
    }
}

