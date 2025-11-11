package com.github.isaac.repositories;

import com.github.isaac.entities.Producto;
import com.github.isaac.repositories.base.BaseRepositoryImpl;
import com.github.isaac.utils.JPAUtil;

import java.util.List;


public class ProductoRepository extends BaseRepositoryImpl<Producto, Long> {
    public ProductoRepository() {
        super(Producto.class);
    }

    public List<Producto> obtenerConStockBajo(int stockMinimo) {
        try (var em = JPAUtil.getEntityManager()) {
            return em.createQuery("SELECT p FROM Producto p WHERE p.stock < :stockMinimo", Producto.class)
                    .setParameter("stockMinimo", stockMinimo)
                    .getResultList();
        }
    }
}
