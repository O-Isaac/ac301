package com.github.isaac.repositories;

import com.github.isaac.entities.Cliente;
import com.github.isaac.repositories.base.BaseRepositoryImpl;
import com.github.isaac.utils.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class ClienteRepository extends BaseRepositoryImpl<Cliente, Long> {
    public ClienteRepository() {
        super(Cliente.class);
    }

    public Optional<Cliente> obtenerPorDni(String dni) {
        try (EntityManager em = JPAUtil.getEntityManager()) {
            return em.createQuery("SELECT c FROM Cliente c WHERE c.nif = :nif", Cliente.class)
                    .setParameter("nif", dni)
                    .getResultStream()
                    .findFirst();
        }
    }

    public List<Cliente> buscarPorNombre(String nombre) {
        try (EntityManager em = JPAUtil.getEntityManager()) {
            return em.createQuery("SELECT c FROM Cliente c WHERE LOWER(c.nombre) LIKE LOWER(:nombre) ", Cliente.class)
                    .setParameter("nombre", "%" + nombre + "%")
                    .getResultList();
        }
    }
}
