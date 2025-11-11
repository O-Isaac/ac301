package com.github.isaac.repositories;

import com.github.isaac.entities.Pedido;
import com.github.isaac.repositories.base.BaseRepositoryImpl;
import com.github.isaac.utils.JPAUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class PedidoRepository extends BaseRepositoryImpl<Pedido, Long> {
    public PedidoRepository() {
        super(Pedido.class);
    }

    // JOIN FETCH evita consultas n+1
    public List<Pedido> obtenerPorClienteId(Long idCliente) {
        try (EntityManager em = JPAUtil.getEntityManager()) {
            return em.createQuery("SELECT p FROM Pedido p JOIN FETCH p.cliente c WHERE c.id = :clienteId", Pedido.class)
                    .setParameter("clienteId", idCliente)
                    .getResultList();
        }
    }
}
