package com.github.isaac.gui.controllers;

import com.github.isaac.entities.Empresa;
import com.github.isaac.gui.forms.FormEmpresa;
import com.github.isaac.gui.models.EmpresaTableModel;
import com.github.isaac.gui.utils.CaptureExceptions;
import com.github.isaac.repositories.EmpresaRepository;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class EmpresaController {
    private final EmpresaRepository empresaRepository = new EmpresaRepository();
    private final EmpresaTableModel empresaTableModel = new EmpresaTableModel(empresaRepository.findAll());
    private final JTable table = new JTable(empresaTableModel);
    private final Component parentComponent;

    public EmpresaController(Component parentComponent) {
        this.parentComponent = parentComponent;
    }

    public void eliminarEmpresa() {
        Empresa empresa = getSelectedEmpresa();
        if (empresa != null) {
            empresaRepository.delete(empresa);
            empresaTableModel.setEmpresas(empresaRepository.findAll());
            JOptionPane.showMessageDialog(parentComponent, "Empresa eliminada");
        }
    }

    private Empresa getSelectedEmpresa() {
        if (table.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(parentComponent, "Seleccione una empresa de la tabla");
            return null;
        }
        return empresaTableModel.getEmpresaAt(table.getSelectedRow());
    }

    public void crearEmpresa() {
        FormEmpresa formEmpresa = new FormEmpresa();

        int result = JOptionPane.showConfirmDialog(
                parentComponent,
                formEmpresa,
                "Crear Empresa",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            CaptureExceptions.capture(parentComponent, () -> {
                Empresa nuevaEmpresa = formEmpresa.getEmpresaFromForm();
                empresaRepository.save(nuevaEmpresa);
                empresaTableModel.setEmpresas(empresaRepository.findAll());
                JOptionPane.showMessageDialog(parentComponent, "Empresa creada");
            });
        }
    }

    public void editarEmpresa() {
        Empresa empresa = getSelectedEmpresa();
        if (empresa != null) {
            FormEmpresa formEmpresa = new FormEmpresa(empresa);

            int result = JOptionPane.showConfirmDialog(
                    parentComponent,
                    formEmpresa,
                    "Editar Empresa",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );

            if (result == JOptionPane.OK_OPTION) {
                CaptureExceptions.capture(parentComponent, () -> {
                    Empresa empresaEditada = formEmpresa.getEmpresaFromForm();
                    empresaEditada.setId(empresa.getId());
                    empresaRepository.update(empresaEditada);
                    empresaTableModel.setEmpresas(empresaRepository.findAll());
                    JOptionPane.showMessageDialog(parentComponent, "Empresa editada");
                });
            }
        }
    }
}

