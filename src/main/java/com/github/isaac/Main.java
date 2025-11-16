package com.github.isaac;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.isaac.services.PedidoServices;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Main {
    static void main(String[] args) throws IOException {
        PedidoServices pedidoServices = new PedidoServices();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.registerModule(new JavaTimeModule());

        String json = objectMapper.writeValueAsString(Map.of("pedidos", pedidoServices.reportesVentas()));
        Files.writeString(Path.of("reportes_ventas.json"), json);
    }
}
