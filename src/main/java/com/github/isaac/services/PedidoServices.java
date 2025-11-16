package com.github.isaac.services;

import com.github.isaac.entities.*;
import com.github.isaac.repositories.PedidoRepository;
import com.github.isaac.utils.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class PedidoServices {
    private final PedidoRepository pedidoRepository = new PedidoRepository();


    public DetallePedido crearDetallePedido(Producto producto, Integer cantidad) {
        DetallePedido detallePedido = new DetallePedido();

        detallePedido.setProducto(producto);
        detallePedido.setCantidad(cantidad);
        detallePedido.setPrecioUnitario(producto.getPrecio());
        detallePedido.setSubtotal(producto.getPrecio().multiply(BigDecimal.valueOf(cantidad)));

        return detallePedido;
    }

    public Optional<Pedido> crearPedido(Cliente cliente, Empresa empresa, List<DetallePedido> detalles) {
        Pedido pedido = null;

        try (EntityManager em = JPAUtil.getEntityManager()) {
            var trasaction = em.getTransaction();

            try {
                trasaction.begin();

                pedido = new Pedido();
                pedido.setCliente(cliente);
                pedido.setEmpresa(empresa);
                pedido.setEstado("PENDIENTE");
                pedido.setTotal(BigDecimal.ZERO);

                for (DetallePedido detalle : detalles) {
                    pedido.addDetalle(detalle); // Establece la relación bidireccional y recalcula el subtotal
                }

                em.persist(pedido);
                em.flush();

                trasaction.commit();
            } catch (Exception ex) {
                if (trasaction.isActive()) {
                    trasaction.rollback();
                }

                System.out.println("Error al crear el pedido: " + ex.getMessage());
            }
        }

        return Optional.ofNullable(pedido);
    }

    public boolean confirmarPedido(Long idPedido) {
        boolean confirmar = false;

        try (EntityManager em = JPAUtil.getEntityManager()) {
            EntityTransaction transaction = em.getTransaction();

            try {
                transaction.begin();

                Pedido pedido = em.find(Pedido.class, idPedido);

                if (pedido == null) {
                    throw new Exception("Pedido no encontrado con ID: " + idPedido);
                }

                BigDecimal total = BigDecimal.ZERO;

                for (DetallePedido detalle : pedido.getDetalles()) {
                    Producto producto = detalle.getProducto();

                    if (producto.getStock() < detalle.getCantidad()) {
                        throw new Exception("Stock insuficiente para el producto: " + producto.getNombre());
                    }

                    producto.setStock(producto.getStock() - detalle.getCantidad());

                    BigDecimal precio = producto.getPrecio();
                    Integer cantidad = detalle.getCantidad();

                    BigDecimal subtotal = precio.multiply(BigDecimal.valueOf(cantidad));
                    detalle.setSubtotal(subtotal);

                    total = total.add(subtotal);
                }

                pedido.setTotal(total);
                pedido.setEstado("CONFIRMADO");

                em.merge(pedido);
                em.flush();

                transaction.commit();
                confirmar = true;
            } catch (Exception ex) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }

                System.out.println("Error al confirmar el pedido: " + ex.getMessage());
            }
        }

        return confirmar;
    }
}
