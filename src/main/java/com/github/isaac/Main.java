package com.github.isaac;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.isaac.consultas.InformeVentasService;
import com.github.isaac.entities.Cliente;
import com.github.isaac.entities.Pedido;
import com.github.isaac.entities.Producto;
import com.github.isaac.repositories.ClienteRepository;
import com.github.isaac.services.PedidoServices;
import com.github.isaac.utils.JPAUtil;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Main {
    /**
     * Situación: El usuario busca productos en la web. Escribe "gam" en el buscador.
     * Requerimiento: Busca todos los productos que contengan el texto "gam" (o el que
     * pase el usuario) en su nombre.
     * • Condición: Debe funcionar si el producto se llama "Gaming" y el usuario
     * busca "gaming" (ignorar mayúsculas).
     */
    private static void retoOne(String articuloBuscar) {
        try (var em = JPAUtil.getEntityManager()) {
            String hql = "SELECT p FROM Producto p WHERE lower(p.nombre) LIKE lower(CONCAT('%', :articuloBuscar, '%') )";
            TypedQuery<Producto> query = em.createQuery(hql, Producto.class);

            List<Producto> productos = query.setParameter("articuloBuscar", articuloBuscar)
                    .getResultList();

            productos.forEach(System.out::println);
        }
    }

    /**
     * Situación: Queremos generar un listado de todas las Ventas realizadas para
     * enviarlas a contabilidad cuyo importe sea mayor a 500€.
     * Requerimiento: Trae todas las ventas y sus clientes asociados en una sola
     * consulta.
     * • Condición: El importe total de la venta debe superar los 500€.
     * • Optimización: Usar JOIN para traer cliente y venta en una única consulta.
     */
    private static void retoTwo() {
        try (var em = JPAUtil.getEntityManager()) {
            String hql = """
                SELECT p FROM Pedido p JOIN FETCH p.cliente c
                    WHERE p.total > 500
            """;

            var query = em.createQuery(hql, Pedido.class);

            query.getResultList().forEach(pedido -> {
                System.out.println("Total: " + pedido.getTotal() + " Cliente: " + pedido.getCliente().getNombre());
            });
        }
    }

    /**
     * Situación: Queremos saber cuánto dinero exacto hemos ingresado vendiendo
     * productos de la categoría "Periféricos".
     * Requerimiento: Suma el importe (precioUnitario * cantidad) de la tabla
     * DetalleVenta, pero filtrando solo los productos de esa categoría.
     * • Condición: Solo contar productos cuya categoría sea exactamente "Periféricos".
     * • Cálculo: SUM(precioUnitario * cantidad) agrupando por categoría.
     */
    private static void retoThree(String categoria) {
        try (var em = JPAUtil.getEntityManager()) {
            String hql = """
                   SELECT d, p.categoria, SUM(d.precioUnitario * d.cantidad) from DetallePedido d
                       JOIN FETCH d.producto p
                       WHERE p.categoria = :categoria
                   """;

            var query =  em.createQuery(hql, Object[].class);

            query.setParameter("categoria", categoria).getResultList().forEach(rs -> {
                BigDecimal subtotal = (BigDecimal) rs[2];
                String categoriaNombre = (String) rs[1];
                System.out.println("Categoria: " + categoriaNombre + " Subtotal: " + subtotal);
            });
        }
    }

    /**
     * Situación: Necesitamos conocer el precio medio de nuestro catálogo para
     * análisis de mercado.
     * Requerimiento: Calcula el precio medio de todos los productos actuales en la
     * base de datos.
     * • Operación: AVG(precio) sobre todos los productos activos.
     */
    private static void retoFour() {
        try (var em = JPAUtil.getEntityManager()) {
            String hql = """
                   SELECT AVG(p.precio) FROM Producto p
            """;

            var query = em.createQuery(hql, Object[].class);

            query.getResultList().forEach(rs -> {
                Double precioMedio = (Double) rs[0];
                System.out.println("precioMedio: " + precioMedio);
            });
        }
    }

    /**
     * Situación: Queremos identificar productos premium dentro de cada categoría
     * para destacarlos en promociones.
     * Requerimiento: Listar los productos que son más caros que la media de su
     * propia categoría.
     * • Condición: precio > AVG(precio) WHERE categoria = producto.categoria
     * • Técnica: Subconsulta o HAVING para comparar con la media por categoría.
     */
    private static void retoFive() {
        try (var em = JPAUtil.getEntityManager()) {
            String hql = """
                    SELECT p, SUM(p.precio) AS total FROM Producto p
                    GROUP BY p.categoria
                    HAVING p.precio > avg(total)
            """;

            var query = em.createQuery(hql, Object[].class);

            query.getResultList().forEach(rs -> {
                Producto p = (Producto) rs[0];

                System.out.printf("Nombre: %s, Precio: %s, Categoria: %s%n",
                        p.getNombre(),
                        p.getPrecio(),
                        p.getCategoria()
                );
            });
        }
    }

    /**
     * Situación: Queremos listar los productos que son más caros que la media de su
     * propia categoría (versión alternativa o ampliada).
     * Requerimiento: Mostrar producto, precio, categoría y precio medio de la categoría.
     * • Condición: precio > precio_medio_categoria
     * • Extra: Incluir en el resultado el precio medio calculado para referencia.
     */
    private static void retoSix() {

    }

    /**
     * Situación: Queremos recuperar clientes que compraron en 2023 pero que NO han
     * comprado nada en 2024 y que sean de Madrid, para campaña de reactivación.
     * Requerimiento: Clientes con ventas en 2023 AND sin ventas en 2024 AND ciudad = "Madrid".
     * • Condición 1: EXISTS(ventas WHERE YEAR = 2023)
     * • Condición 2: NOT EXISTS(ventas WHERE YEAR = 2024)
     * • Condición 3: cliente.ciudad = "Madrid"
     */
    private static void retoSeven() {

    }

    public static void main(String[] args) throws IOException {
        new InformeVentasService()
                .moverProductosANuevoHistorico();
    }
}
