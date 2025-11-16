package com.github.isaac.gui;

import com.github.isaac.gui.views.*;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    public MainView() {
        setTitle("AC301 GUI");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initialize() {
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Empresas", new EmpresaPane());
        tabbedPane.addTab("Clientes", new ClientesPane());
        tabbedPane.addTab("Productos", new ProductoPane());
        tabbedPane.addTab("Pedidos", new PedidoPane());
        tabbedPane.addTab("Detalle Pedidos", new DetallePedidoPane());

        setLayout(new BorderLayout(5,5));
        add(tabbedPane, BorderLayout.CENTER);
    }

    static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainView mainView = new MainView();
            mainView.initialize();
            mainView.setVisible(true);
        });
    }
}
