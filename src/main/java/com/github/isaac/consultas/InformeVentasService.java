package com.github.isaac.consultas;

import com.github.isaac.entities.Cliente;
import com.github.isaac.entities.Producto;
import com.github.isaac.utils.JPAUtil;
import jakarta.persistence.EntityTransaction;

import java.math.BigDecimal;
import java.util.List;

/**
 * Clase de servicio para resolver las consultas solicitadas en la actividad.
 *
 * Actividades:
 *
 * 1. Informe de Ventas Dinámico:
 *    - Obtener listado de ventas por cliente.
 *    - Incluir clasificación del pedido usando CASE WHEN:
 *        < 50€  → "Pedido Pequeño"
 *        50–500€ → "Pedido Estándar"
 *        > 500€ → "Pedido Grande"
 *    - Esto debe resolverse dentro de la consulta (JPQL/SQL), no en Java.
 *
 * 2. Obtener clientes que han comprado productos de al menos 3 categorías distintas.
 *    - No importa el monto gastado, solo la variedad de categorías.
 *
 * 3. Obtener productos que nunca se han vendido.
 *    - Usar NOT EXISTS o LEFT JOIN ... WHERE d.id IS NULL
 *    - Esta consulta se usará para insertar esos productos en una tabla de histórico
 *      y eliminarlos de la tabla principal.
 */
public class InformeVentasService {

    /**
     * Punto 1:
     * Consulta de ventas por cliente con clasificación CASE WHEN.
     *
     * Debe devolver:
     *  - Nombre del cliente
     *  - Total vendido
     *  - Cadena: "Pedido Pequeño", "Pedido Estándar" o "Pedido Grande"
     */
    public void generarInformeVentas() {
        try (var em = JPAUtil.getEntityManager()) {
            String jqpl = """
                SELECT
                    c.nombre,
                    p.total,
                    CASE
                      WHEN p.total < 50 THEN 'Pedido Pequeño'
                      WHEN p.total BETWEEN 50 AND 500 THEN 'Pedido Estandar'
                      ELSE 'Pedido Mayor'
                    END
                FROM Pedido p INNER JOIN p.cliente c
            """;

            var query = em.createQuery(jqpl, Object[].class);
            var resultados = query.getResultList();

            for (var resultado : resultados) {
                String nombre = (String) resultado[0];
                BigDecimal total = (BigDecimal) resultado[1];
                String tipoPedido = (String) resultado[2];
                System.out.printf("Nombre cliente: %s, Total pedido: %s, Tipo pedido: %s%n", nombre, total, tipoPedido);
            }
        }
    }

    /**
     * Punto 2:
     * Clientes que han comprado productos de al menos 3 categorías distintas.
     */
    public void obtenerClientesDiversos() {
        try (var em = JPAUtil.getEntityManager()) {
            String  jqpl = """
                SELECT c.nombre
                FROM DetallePedido dp
                INNER JOIN dp.pedido pe
                INNER JOIN pe.cliente c
                INNER JOIN dp.producto pr
                GROUP BY c.nombre
                HAVING COUNT(DISTINCT pr.categoria) >= 3
            """;

            var query = em.createQuery(jqpl, Object[].class);
            var resultados = query.getResultList();

            for (var resultado : resultados) {
                String nombre = (String) resultado[0];
                System.out.printf("Nombre cliente: %s%n", nombre);
            }
        }
    }

    /**
     * Punto 3:
     * Productos que nunca se han vendido.
     *
     * Opción 1 (si NO existe relación bidireccional):
     *   SELECT p FROM Producto p
     *   WHERE NOT EXISTS (
     *       SELECT d FROM DetalleVenta d WHERE d.producto = p
     *   )
     *
     * Opción 2 (si Producto tiene @OneToMany detallesVenta):
     *   SELECT p FROM Producto p
     *   LEFT JOIN p.detallesVenta d
     *   WHERE d.id IS NULL
     */
    public void obtenerProductosNuncaVendidos() {
        // TODO: Implementar NOT EXISTS o LEFT JOIN según tu modelo
        try (var em = JPAUtil.getEntityManager()) {
            String  jqpl = """
                SELECT p
                  FROM Producto p
                  LEFT JOIN p.detallePedidos d
                  WHERE d.id IS NULL
            """;

            var query = em.createQuery(jqpl, Object[].class);
            var resultados = query.getResultList();

            for (var resultado : resultados) {
                Producto producto =  (Producto) resultado[0];
                String nombre = producto.getNombre();

                System.out.printf("Nombre producto: %s%n", nombre);
            }
        }
    }

    /**
     * Inserta los productos no vendidos en la tabla de histórico.
     */
    public void moverProductosANuevoHistorico() {
        try (var em = JPAUtil.getEntityManager()) {
            EntityTransaction transaction =  em.getTransaction();

            transaction.begin();

            // Obtener productos nunca vendidos
            String jpqlProductoNoVendidos = """
              SELECT p
                  FROM Producto p
                  LEFT JOIN p.detallePedidos d
                  WHERE d.id IS NULL
            """;

            List<Producto> productosNoVendidos = em.createQuery(jpqlProductoNoVendidos, Producto.class)
                    .getResultList();

            if (productosNoVendidos.isEmpty()) {
                System.out.println("No hay productos no vendidos para mover.");
                transaction.rollback();
                return;
            }

            // Por cada producto insertamos un log individual
            for (Producto p : productosNoVendidos) {
                String log = "Producto eliminado: ID=" + p.getId() + ", Nombre=" + p.getNombre();
                String consulta = """
                    INSERT INTO consultas.historico (created_at, log)
                        VALUES (NOW(), ?)
                   """;

                em.createNativeQuery(consulta)
                        .setParameter(1, log)
                        .executeUpdate();
            }

            // Eliminar productos no vendidos
            String jpqlDeleteProductos = """
                DELETE FROM Producto p
                WHERE p IN :productos
            """;

            em.createQuery(jpqlDeleteProductos)
                    .setParameter("productos", productosNoVendidos)
                    .executeUpdate();

            transaction.commit();

            System.out.println("Productos movidos a histórico y eliminados correctamente.");
        }

    }
}