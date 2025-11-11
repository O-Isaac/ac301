package com.github.isaac.repositories.base;

import com.github.isaac.utils.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.validation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class BaseRepositoryImpl<T, ID> implements BaseRepository<T, ID> {
    private final Class<T> entityClass;

    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private Validator validator = factory.getValidator();

    protected BaseRepositoryImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    // Metodo para validar una entidad antes de guardarla o actualizarla
    public void validar(T entity) {
        Set<ConstraintViolation<T>> violations = validator.validate(entity);

        if (!violations.isEmpty()) {
            for (ConstraintViolation<T> violation : violations) {
                String err = String.format("Error validando la entidad %s: propiedad '%s' %s",
                        entityClass.getSimpleName(),
                        violation.getPropertyPath(),
                        violation.getMessage()
                );

                System.err.println(err);
            }

            throw new ConstraintViolationException("Entidad no válida", violations);
        }
   }

    @Override
    public void save(T entity) {
        validar(entity);

        try (EntityManager em = JPAUtil.getEntityManager()) {
            EntityTransaction transaction = em.getTransaction();

            try {
                transaction.begin();
                em.persist(entity);
                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }

                System.err.println("Error guardar la entidad: " + e.getMessage());
            }
        }
    }

    @Override
    public Optional<T> findById(ID id) {
        try (EntityManager em = JPAUtil.getEntityManager()) {
            return Optional.ofNullable(em.find(entityClass, id));
        }
    }

    @Override
    public void update(T entity) {
        validar(entity);

        try (EntityManager em = JPAUtil.getEntityManager()) {
            EntityTransaction transaction = em.getTransaction();

            try {
                transaction.begin();
                em.merge(entity);
                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }

                System.err.println("Error actualizando la entidad: " + e.getMessage());
            }
        }
    }

    @Override
    public void deleteById(ID id) {
        try (EntityManager em = JPAUtil.getEntityManager()) {
            EntityTransaction transaction = em.getTransaction();

            try {
                transaction.begin();

                T entity = em.find(entityClass, id);

                if (entity != null) {
                    em.remove(entity);
                }

                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }

                System.err.println("Error eliminando la entidad por ID: " + e.getMessage());
            }
        }
    }

    // Se debe usar con un findById previo para asegurar que la entidad está gestionada
    @Override
    public void delete(T entity) {
        try (EntityManager em = JPAUtil.getEntityManager()) {
            EntityTransaction transaction = em.getTransaction();

            try {
                transaction.begin();
                em.remove(em.contains(entity) ? entity : em.merge(entity));
                transaction.commit();
            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }

                System.err.println("Error eliminando la entidad: " + e.getMessage());
            }
        }
    }

    @Override
    public List<T> findAll() {
        try (EntityManager em = JPAUtil.getEntityManager()) {
            return em.createQuery("FROM " + entityClass.getSimpleName(), entityClass)
                    .getResultList();
        }
    }
}
