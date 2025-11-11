package com.github.isaac.utils;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    // Nombre de la unidad de persistencia definida en persistence.xml
    private static final String PERSISTENCE_UNIT_NAME = "Persistencia";

    // Fábrica de EntityManagers (solo una por aplicación)
    private static EntityManagerFactory emf;

    // Inicialización estática de la fábrica de EntityManagers
    static {
        try {
            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        } catch (Exception e) {
            System.err.println("Error inicializando EntityManagerFactory: " + e.getMessage());
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * Obtiene una nueva instancia de EntityManager.
     *
     * Cada llamada devuelve un EntityManager independiente.
     * El repositorio es responsable de cerrarlo (em.close()).
     */
    public static EntityManager getEntityManager() {
        if (emf == null || !emf.isOpen()) {
            throw new IllegalStateException("El EntityManagerFactory no está inicializado o ya fue cerrado.");
        }
        return emf.createEntityManager();
    }

    /**
     * Cierra la fábrica global de EntityManagers.
     * Debe llamarse solo una vez al finalizar la aplicación.
     */
    public static void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
