package com.github.isaac.services;

import com.github.isaac.dtos.ReporteVentasDto;
import com.github.isaac.entities.*;
import com.github.isaac.mappers.PedidoMapper;
import com.github.isaac.repositories.PedidoRepository;
import com.github.isaac.utils.JPAUtil;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.List;

public class PedidoServices {
    private final PedidoRepository pedidoRepository = new PedidoRepository();

    public Pedido crearPedido(Cliente cliente, Empresa empresa, List<DetallePedido> detalles) {
        Pedido pedido = new Pedido();

        pedido.setCliente(cliente);
        pedido.setEmpresa(empresa);
        pedido.setEstado("PENDIENTE");
        pedido.setTotal(BigDecimal.ZERO);

        for (DetallePedido detalle : detalles) {
            pedido.addDetalle(detalle); // Establece la relación bidireccional y recalcula el subtotal
        }

        pedidoRepository.save(pedido);

        return pedido;
    }

    public boolean confirmarPedido(Long idPedido) {
        try (EntityManager em = JPAUtil.getEntityManager()) {
            Pedido pedido = em.find(Pedido.class, idPedido);

            if (pedido == null) {
                System.err.println("Pedido no encontrado con ID: " + idPedido);
                return false;
            }


            List<DetallePedido> detalles = pedido.getDetalles();

            for (DetallePedido detalle : detalles) {
                Producto producto = detalle.getProducto();
                Integer cantidadSolicitada = detalle.getCantidad();

                if (producto.getStock() < cantidadSolicitada) {
                    System.err.println("Stock insuficiente para el producto: " + producto.getNombre());
                    return false;
                }

                producto.setStock(producto.getStock() - cantidadSolicitada);
            }

            pedido.setEstado("CONFIRMADO");
            pedidoRepository.update(pedido);
        }

        return false;
    }


    public List<ReporteVentasDto> reportesVentas() {
        List<Pedido> pedidos = pedidoRepository.obtenerVentasLineasProductos();
        PedidoMapper mapper = PedidoMapper.INSTANCE;

        return pedidos
                .stream()
                .map(mapper::toDto)
                .toList();
    }


}
