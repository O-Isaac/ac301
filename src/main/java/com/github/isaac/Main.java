package com.github.isaac;

import com.github.isaac.entities.Cliente;
import com.github.isaac.repositories.ClienteRepository;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        ClienteRepository clienteRepository = new ClienteRepository();

        clienteRepository
                .buscarPorNombre("J")
                .forEach(System.out::println);

        clienteRepository
                .obtenerPorDni("23456789")
                .ifPresent(System.out::println);
    }
}
