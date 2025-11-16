package com.github.isaac;

import com.github.isaac.repositories.ClienteRepository;
import com.github.isaac.repositories.EmpresaRepository;
import com.github.isaac.repositories.PedidoRepository;
import com.github.isaac.repositories.ProductoRepository;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main(String[] args) {
        ClienteRepository clienteRepository = new ClienteRepository();
        EmpresaRepository empresaRepository = new EmpresaRepository();
        PedidoRepository pedidoRepository = new PedidoRepository();
        ProductoRepository productoRepository = new ProductoRepository();


    }
}
