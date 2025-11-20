package com.github.isaac.gui.forms;

import com.github.isaac.entities.Cliente;
import com.github.isaac.entities.DetallePedido;
import com.github.isaac.entities.Empresa;
import com.github.isaac.entities.Producto;
import com.github.isaac.entities.Pedido;
import com.github.isaac.gui.models.DetallePedidoEditTableModel;
import com.github.isaac.repositories.ClienteRepository;
import com.github.isaac.repositories.EmpresaRepository;
import com.github.isaac.repositories.ProductoRepository;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

public class FormPedido extends JPanel {
    private final JComboBox<Cliente> clienteCombo = new JComboBox<>();
    private final JComboBox<Empresa> empresaCombo = new JComboBox<>();
    private final JComboBox<Producto> productoCombo = new JComboBox<>();
    private final JSpinner cantidadSpinner = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
    private final JButton addDetalleBtn = new JButton("Añadir detalle");
    private final JButton removeDetalleBtn = new JButton("Quitar seleccionado");
    private final JLabel totalLabel = new JLabel("Total: 0.00");
    private final JTable detallesTable = new JTable();
    private final DetallePedidoEditTableModel detallesModel = new DetallePedidoEditTableModel();

    private final ClienteRepository clienteRepository = new ClienteRepository();
    private final EmpresaRepository empresaRepository = new EmpresaRepository();
    private final ProductoRepository productoRepository = new ProductoRepository();

    public FormPedido() {
        setLayout(new BorderLayout(10,10));
        detallesTable.setModel(detallesModel);

        cargarCombos();
        construirLayout();
        wireEvents();
    }

    // Nuevo constructor para edición
    public FormPedido(Pedido pedido) {
        setLayout(new BorderLayout(10,10));
        detallesTable.setModel(detallesModel);
        cargarCombos();
        construirLayout();
        wireEvents();
        if (pedido != null) {
            prefill(pedido);
        }
    }

    private void prefill(Pedido pedido) {
        // Preseleccionar cliente y empresa
        clienteCombo.setSelectedItem(pedido.getCliente());
        empresaCombo.setSelectedItem(pedido.getEmpresa());

        // Cargar detalles existentes conservando sus IDs
        for (DetallePedido d : pedido.getDetalles()) {
            if (d.getProducto() != null && d.getCantidad() != null) {
                detallesModel.addDetalleExistente(d);
            }
        }
        actualizarTotalLabel();
    }

    private void cargarCombos() {
        List<Cliente> clientes = clienteRepository.findAll();
        List<Empresa> empresas = empresaRepository.findAll();
        List<Producto> productos = productoRepository.findAll();

        clientes.forEach(clienteCombo::addItem);
        empresas.forEach(empresaCombo::addItem);
        productos.forEach(productoCombo::addItem);

        clienteCombo.setRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JLabel lbl = (JLabel) new DefaultListCellRenderer().getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value != null) lbl.setText(value.getNombre() + " " + value.getApellidos());
            return lbl;
        });

        empresaCombo.setRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JLabel lbl = (JLabel) new DefaultListCellRenderer().getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value != null) lbl.setText(value.getNombre() + " (" + value.getCif() + ")");
            return lbl;
        });

        productoCombo.setRenderer((list, value, index, isSelected, cellHasFocus) -> {
            JLabel lbl = (JLabel) new DefaultListCellRenderer().getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value != null) lbl.setText(value.getCodigo() + " - " + value.getNombre());
            return lbl;
        });
    }

    private void construirLayout() {
        JPanel topPanel = new JPanel(new GridLayout(1,2,10,10));
        JPanel clientePanel = new JPanel(new BorderLayout());
        clientePanel.setBorder(BorderFactory.createTitledBorder("Cliente"));
        clientePanel.add(clienteCombo, BorderLayout.CENTER);

        JPanel empresaPanel = new JPanel(new BorderLayout());
        empresaPanel.setBorder(BorderFactory.createTitledBorder("Empresa"));
        empresaPanel.add(empresaCombo, BorderLayout.CENTER);

        topPanel.add(clientePanel);
        topPanel.add(empresaPanel);

        JPanel detalleFormPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,5,5));
        detalleFormPanel.setBorder(BorderFactory.createTitledBorder("Agregar Detalle"));
        detalleFormPanel.add(productoCombo);
        detalleFormPanel.add(new JLabel("Cantidad:"));
        detalleFormPanel.add(cantidadSpinner);
        detalleFormPanel.add(addDetalleBtn);
        detalleFormPanel.add(removeDetalleBtn);

        JPanel centerPanel = new JPanel(new BorderLayout(5,5));
        centerPanel.add(detalleFormPanel, BorderLayout.NORTH);
        JScrollPane scroll = new JScrollPane(detallesTable);
        scroll.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        centerPanel.add(scroll, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(totalLabel);

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void wireEvents() {
        addDetalleBtn.addActionListener(e -> {
            Producto producto = (Producto) productoCombo.getSelectedItem();
            int cantidad = (int) cantidadSpinner.getValue();
            if (producto == null) return;
            detallesModel.addDetalle(producto, cantidad);
            actualizarTotalLabel();
        });

        removeDetalleBtn.addActionListener(e -> {
            int row = detallesTable.getSelectedRow();
            if (row == -1) return;
            detallesModel.removeAt(row);
            actualizarTotalLabel();
        });
    }

    private void actualizarTotalLabel() {
        BigDecimal total = detallesModel.calcularTotal();
        totalLabel.setText("Total: " + total);
    }

    public Cliente getSelectedCliente() {
        return (Cliente) clienteCombo.getSelectedItem();
    }

    public Empresa getSelectedEmpresa() {
        return (Empresa) empresaCombo.getSelectedItem();
    }

    public List<DetallePedido> getDetalles() {
        return detallesModel.getDetalles();
    }
}
