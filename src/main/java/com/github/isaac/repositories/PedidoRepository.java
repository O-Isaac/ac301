package com.github.isaac.repositories;

import com.github.isaac.entities.*;
import com.github.isaac.repositories.base.BaseRepositoryImpl;
import com.github.isaac.utils.JPAUtil;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class PedidoRepository extends BaseRepositoryImpl<Pedido, Long> {
    public PedidoRepository() {
        super(Pedido.class);
    }

    // JOIN FETCH evita consultas n+1
    public List<Pedido> obtenerPorClienteId(Long idCliente) {
        try (EntityManager em = JPAUtil.getEntityManager()) {
            return em.createQuery("""
                   SELECT p FROM Pedido p
                   JOIN FETCH p.cliente c
                   JOIN FETCH p.empresa e
                   WHERE c.id = :clienteId
                   """, Pedido.class)
                    .setParameter("clienteId", idCliente)
                    .getResultList();
        }
    }

    public BigDecimal calcularTotalVentasDelDia(LocalDate fecha) {
        try (EntityManager em = JPAUtil.getEntityManager()) {
            return em.createQuery("SELECT SUM(p.total) FROM Pedido p WHERE p.fecha = :fecha", BigDecimal.class)
                    .setParameter("fecha", fecha)
                    .getSingleResult();
        }
    }

    public List<Pedido> obtenerVentasLineasProductos() {
        try (EntityManager em = JPAUtil.getEntityManager()) {
            String jqpl = """
                SELECT DISTINCT p FROM Pedido p
                JOIN FETCH p.cliente
                JOIN FETCH p.empresa
                LEFT JOIN FETCH p.detalles d
                LEFT JOIN FETCH d.producto
                """;

            return em.createQuery(jqpl, Pedido.class).getResultList();
        }
    }

    public Pedido findByIdWithDetalles(Long id) {
        try (EntityManager em = JPAUtil.getEntityManager()) {
            List<Pedido> result = em.createQuery("""
                SELECT p FROM Pedido p
                JOIN FETCH p.cliente
                JOIN FETCH p.empresa
                LEFT JOIN FETCH p.detalles d
                LEFT JOIN FETCH d.producto
                WHERE p.id = :id
                """, Pedido.class)
                .setParameter("id", id)
                .getResultList();
            return result.isEmpty() ? null : result.get(0);
        }
    }
}
