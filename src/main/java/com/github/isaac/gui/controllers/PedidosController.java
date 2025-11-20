package com.github.isaac.gui.controllers;

import com.github.isaac.entities.Cliente;
import com.github.isaac.entities.DetallePedido;
import com.github.isaac.entities.Empresa;
import com.github.isaac.entities.Pedido;
import com.github.isaac.gui.forms.FormPedido;
import com.github.isaac.gui.models.PedidoTableModel;
import com.github.isaac.gui.utils.CaptureExceptions;
import com.github.isaac.repositories.PedidoRepository;
import com.github.isaac.services.PedidoServices;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
public class PedidosController {
    private final PedidoRepository pedidoRepository = new PedidoRepository();
    private final PedidoTableModel pedidoTableModel = new PedidoTableModel(pedidoRepository.findAll());
    private final JTable table = new JTable(pedidoTableModel);
    private final PedidoServices pedidoServices = new PedidoServices();
    private final Component parentComponent;

    public PedidosController(Component parentComponent) {
        this.parentComponent = parentComponent;
    }

    private Pedido getSelectedPedido() {
        if (table.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(parentComponent, "Seleccione un pedido de la tabla");
            return null;
        }
        return pedidoTableModel.getPedidoAt(table.getSelectedRow());
    }

    public void eliminarPedido() {
        Pedido pedido = getSelectedPedido();
        if (pedido != null) {
            pedidoRepository.deleteById(pedido.getId());
            pedidoTableModel.setPedidos(pedidoRepository.findAll());
            JOptionPane.showMessageDialog(parentComponent, "Pedido eliminado");
        }
    }

    public void crearPedido() {
        FormPedido formPedido = new FormPedido();

        int result = JOptionPane.showConfirmDialog(
                parentComponent,
                formPedido,
                "Crear Pedido",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            CaptureExceptions.capture(parentComponent, () -> {
                Cliente cliente = formPedido.getSelectedCliente();
                Empresa empresa = formPedido.getSelectedEmpresa();
                List<DetallePedido> detalles = formPedido.getDetalles();

                if (cliente == null || empresa == null) {
                    JOptionPane.showMessageDialog(parentComponent, "Cliente y Empresa son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (detalles.isEmpty()) {
                    JOptionPane.showMessageDialog(parentComponent, "Debe agregar al menos un detalle", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Pedido nuevo = pedidoServices.crearPedido(cliente, empresa, detalles);
                pedidoTableModel.setPedidos(pedidoRepository.findAll());
                JOptionPane.showMessageDialog(parentComponent, "Pedido creado con ID: " + nuevo.getId());
            });
        }
    }

    public void editarPedido() {
        Pedido seleccionado = getSelectedPedido();
        if (seleccionado == null) return;

        // Recargar con detalles y productos para evitar LazyInitializationException
        Pedido pedido = pedidoRepository.findByIdWithDetalles(seleccionado.getId());
        if (pedido == null) {
            JOptionPane.showMessageDialog(parentComponent, "Pedido no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        FormPedido formPedido = new FormPedido(pedido);

        int result = JOptionPane.showConfirmDialog(
                parentComponent,
                formPedido,
                "Editar Pedido",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            CaptureExceptions.capture(parentComponent, () -> {
                Cliente cliente = formPedido.getSelectedCliente();
                Empresa empresa = formPedido.getSelectedEmpresa();
                List<DetallePedido> nuevosDetalles = formPedido.getDetalles();

                if (cliente == null || empresa == null) {
                    JOptionPane.showMessageDialog(parentComponent, "Cliente y Empresa son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (nuevosDetalles.isEmpty()) {
                    JOptionPane.showMessageDialog(parentComponent, "Debe mantener al menos un detalle", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Actualizar pedido existente conservando detalles con IDs
                pedido.setCliente(cliente);
                pedido.setEmpresa(empresa);

                // Limpiar detalles antiguos y agregar nuevos (conservando IDs si existen)
                pedido.getDetalles().clear();

                for (DetallePedido d : nuevosDetalles) {
                    d.setPedido(pedido); // Establecer relación bidireccional
                    pedido.getDetalles().add(d);
                }

                // Actualizar el total del pedido (Big Decimal)
                BigDecimal total = nuevosDetalles.stream()
                        .map(d -> d.getProducto().getPrecio().multiply(BigDecimal.valueOf(d.getCantidad())))
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

                pedido.setTotal(total);

                pedidoRepository.update(pedido);
                pedidoTableModel.setPedidos(pedidoRepository.findAll());
                JOptionPane.showMessageDialog(parentComponent, "Pedido editado");
            });
        }
    }

    public void confirmarPedido() {
        Pedido pedido = getSelectedPedido();
        if (pedido != null) {
            boolean ok = pedidoServices.confirmarPedido(pedido.getId());
            pedidoTableModel.setPedidos(pedidoRepository.findAll());
            JOptionPane.showMessageDialog(parentComponent, ok ? "Pedido confirmado" : "No se pudo confirmar el pedido");
        }
    }
}
