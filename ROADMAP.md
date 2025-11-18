# Roadmap - Proyecto AC301 (RA3)

Este documento verifica los requisitos implementados en el proyecto AC301, indicando qué criterios se han cumplido y proporcionando enlaces al código correspondiente.

## 📋 Índice

1. [Modelo de Datos y Entidades](#1-modelo-de-datos-y-entidades)
2. [Capa de Persistencia (JPA/Hibernate)](#2-capa-de-persistencia-jpahibernate)
3. [Patrón Repositorio](#3-patrón-repositorio)
4. [Validación de Datos](#4-validación-de-datos)
5. [Capa de Servicios](#5-capa-de-servicios)
6. [Gestión de Transacciones](#6-gestión-de-transacciones)
7. [Consultas y Operaciones CRUD](#7-consultas-y-operaciones-crud)
8. [Configuración del Proyecto](#8-configuración-del-proyecto)

---

## 1. Modelo de Datos y Entidades

### ✅ Requisito: Definición de entidades del dominio

**Estado**: ✅ COMPLETADO

**Descripción**: Se han definido todas las entidades principales del dominio de negocio con sus relaciones.

**Entidades Implementadas**:

#### 1.1 Cliente
- **Ubicación**: [`src/main/java/com/github/isaac/entities/Cliente.java`](./src/main/java/com/github/isaac/entities/Cliente.java)
- **Atributos**:
  - `id` (Long, PK auto-generada)
  - `nif` (String, único, validado con patrón)
  - `telefono` (String, validado con patrón 9 dígitos)
  - `nombre` (String, entre 2-50 caracteres)
  - `apellidos` (String, entre 2-100 caracteres)
  - `direccion` (String, hasta 150 caracteres)
  - `direccionEnvio` (String, hasta 150 caracteres)
- **Relaciones**: OneToMany con Pedido

#### 1.2 Empresa
- **Ubicación**: [`src/main/java/com/github/isaac/entities/Empresa.java`](./src/main/java/com/github/isaac/entities/Empresa.java)
- **Atributos**:
  - `id` (Long, PK auto-generada)
  - `cif` (String, único, validado con patrón)
  - `telefono` (String, validado con patrón 9 dígitos)
  - `nombre` (String, entre 2-100 caracteres)
  - `localidad` (String, entre 2-100 caracteres)
  - `domicilio` (String, hasta 150 caracteres)
  - `email` (String, validado como email, hasta 100 caracteres)
- **Relaciones**: OneToMany con Pedido

#### 1.3 Producto
- **Ubicación**: [`src/main/java/com/github/isaac/entities/Producto.java`](./src/main/java/com/github/isaac/entities/Producto.java)
- **Atributos**:
  - `id` (Long, PK auto-generada)
  - `codigo` (String, único, 3-10 caracteres alfanuméricos mayúsculas)
  - `nombre` (String, entre 2-100 caracteres)
  - `descripcion` (String, hasta 255 caracteres)
  - `precio` (BigDecimal, precision 10,2, mínimo 0.01)
  - `stock` (Integer, mínimo 0)
- **Relaciones**: OneToMany con DetallePedido

#### 1.4 Pedido
- **Ubicación**: [`src/main/java/com/github/isaac/entities/Pedido.java`](./src/main/java/com/github/isaac/entities/Pedido.java)
- **Atributos**:
  - `id` (Long, PK auto-generada)
  - `total` (BigDecimal, precision 10,2, auto-calculado)
  - `fecha` (LocalDate, auto-generado con @CreationTimestamp)
  - `estado` (String, valor por defecto "NO CONFIRMADO")
- **Relaciones**: 
  - ManyToOne con Cliente
  - ManyToOne con Empresa
  - OneToMany con DetallePedido
- **Métodos especiales**:
  - `addDetalle()`: Establece relación bidireccional
  - `removeDetalle()`: Elimina relación bidireccional
  - `recalcularTotal()`: Calcula automáticamente el total del pedido

#### 1.5 DetallePedido
- **Ubicación**: [`src/main/java/com/github/isaac/entities/DetallePedido.java`](./src/main/java/com/github/isaac/entities/DetallePedido.java)
- **Atributos**:
  - `id` (Long, PK auto-generada)
  - `cantidad` (Integer, mínimo 1)
  - `precioUnitario` (BigDecimal, precision 10,2, mínimo 0.01)
  - `subtotal` (BigDecimal, precision 10,2, auto-calculado)
- **Relaciones**: 
  - ManyToOne con Pedido
  - ManyToOne con Producto
- **Métodos especiales**:
  - `calcularSubtotal()`: Calcula automáticamente el subtotal (@PrePersist/@PreUpdate)

---

## 2. Capa de Persistencia (JPA/Hibernate)

### ✅ Requisito: Configuración de JPA/Hibernate

**Estado**: ✅ COMPLETADO

**Descripción**: Configuración completa de JPA con Hibernate como proveedor de persistencia.

#### 2.1 Configuración de Persistencia
- **Ubicación**: [`src/main/resources/META-INF/persistence.xml`](./src/main/resources/META-INF/persistence.xml)
- **Unidad de Persistencia**: `Persistencia`
- **Base de Datos**: MariaDB (localhost:3306/ac301)
- **Dialecto**: MariaDBDialect
- **Características**:
  - Auto-actualización del esquema (`hibernate.hbm2ddl.auto=update`)
  - Visualización de SQL (`hibernate.show_sql=true`)
  - Formato de SQL legible (`hibernate.format_sql=true`)

#### 2.2 Utilidad de JPA
- **Ubicación**: [`src/main/java/com/github/isaac/utils/JPAUtil.java`](./src/main/java/com/github/isaac/utils/JPAUtil.java)
- **Funcionalidad**:
  - Singleton para EntityManagerFactory
  - Gestión centralizada de EntityManagers
  - Método `getEntityManager()`: Proporciona nuevas instancias de EntityManager
  - Método `close()`: Cierra la fábrica de EntityManagers
  - Manejo de errores en inicialización

---

## 3. Patrón Repositorio

### ✅ Requisito: Implementación del patrón Repository

**Estado**: ✅ COMPLETADO

**Descripción**: Implementación completa del patrón Repository con una jerarquía base y repositorios específicos.

#### 3.1 BaseRepository (Interfaz)
- **Ubicación**: [`src/main/java/com/github/isaac/repositories/base/BaseRepository.java`](./src/main/java/com/github/isaac/repositories/base/BaseRepository.java)
- **Operaciones CRUD**:
  - `save(T entity)`: Guardar nueva entidad
  - `update(T entity)`: Actualizar entidad existente
  - `delete(T entity)`: Eliminar entidad
  - `deleteById(ID id)`: Eliminar por ID
  - `findById(ID id)`: Buscar por ID
  - `findAll()`: Obtener todas las entidades

#### 3.2 BaseRepositoryImpl (Implementación base)
- **Ubicación**: [`src/main/java/com/github/isaac/repositories/base/BaseRepositoryImpl.java`](./src/main/java/com/github/isaac/repositories/base/BaseRepositoryImpl.java)
- **Características**:
  - Implementación genérica de operaciones CRUD
  - Validación automática antes de persistir/actualizar
  - Gestión de transacciones con rollback automático
  - Manejo de errores con mensajes informativos
  - Uso de `flush()` para garantizar ID antes del commit

#### 3.3 Repositorios Específicos

##### ClienteRepository
- **Ubicación**: [`src/main/java/com/github/isaac/repositories/ClienteRepository.java`](./src/main/java/com/github/isaac/repositories/ClienteRepository.java)
- **Consultas especializadas**:
  - `obtenerPorDni(String dni)`: Buscar cliente por NIF
  - `buscarPorNombre(String nombre)`: Búsqueda parcial por nombre (LIKE)

##### PedidoRepository
- **Ubicación**: [`src/main/java/com/github/isaac/repositories/PedidoRepository.java`](./src/main/java/com/github/isaac/repositories/PedidoRepository.java)
- **Consultas especializadas**:
  - `obtenerPorClienteId(Long idCliente)`: Obtener pedidos de un cliente con JOIN FETCH
  - `calcularTotalVentasDelDia(LocalDate fecha)`: Sumar ventas del día

##### ProductoRepository
- **Ubicación**: [`src/main/java/com/github/isaac/repositories/ProductoRepository.java`](./src/main/java/com/github/isaac/repositories/ProductoRepository.java)
- **Hereda**: Operaciones CRUD de BaseRepository

##### EmpresaRepository
- **Ubicación**: [`src/main/java/com/github/isaac/repositories/EmpresaRepository.java`](./src/main/java/com/github/isaac/repositories/EmpresaRepository.java)
- **Hereda**: Operaciones CRUD de BaseRepository

##### DetallePedidoRepository
- **Ubicación**: [`src/main/java/com/github/isaac/repositories/DetallePedidoRepository.java`](./src/main/java/com/github/isaac/repositories/DetallePedidoRepository.java)
- **Hereda**: Operaciones CRUD de BaseRepository

---

## 4. Validación de Datos

### ✅ Requisito: Validación con Jakarta Bean Validation

**Estado**: ✅ COMPLETADO

**Descripción**: Implementación completa de validaciones usando anotaciones de Jakarta Validation.

#### 4.1 Validaciones Implementadas por Entidad

**Cliente**:
- `@NotBlank` en nif, telefono, nombre, apellidos, direccion, direccionEnvio
- `@Pattern` para NIF (formato 12345678A)
- `@Pattern` para telefono (9 dígitos)
- `@Size` para longitudes de campos

**Empresa**:
- `@NotBlank` en cif, telefono, nombre, localidad, domicilio, email
- `@Pattern` para CIF (formato A12345678)
- `@Pattern` para telefono (9 dígitos)
- `@Email` para validación de email
- `@Size` para longitudes de campos

**Producto**:
- `@NotBlank` en codigo, nombre, descripcion
- `@Pattern` para codigo (3-10 caracteres alfanuméricos mayúsculas)
- `@NotNull` en precio y stock
- `@DecimalMin` para precio (mínimo 0.01)
- `@Min` para stock (mínimo 0)
- `@Digits` para precisión de precio
- `@Size` para longitudes de campos

**Pedido**:
- `@NotNull` en cliente, empresa, total
- `@Valid` para validación en cascada de relaciones
- `@NotEmpty` para lista de detalles (mínimo 1 detalle)
- `@DecimalMin` para total (no negativo)
- `@Digits` para precisión de total

**DetallePedido**:
- `@NotNull` en pedido, producto, cantidad, precioUnitario
- `@Min` para cantidad (mínimo 1)
- `@DecimalMin` para precios (mínimo 0.01)
- `@Digits` para precisión de importes

#### 4.2 Validación Automática en Repositorio
- **Ubicación**: [`BaseRepositoryImpl.validar()`](./src/main/java/com/github/isaac/repositories/base/BaseRepositoryImpl.java#L23-L39)
- Se ejecuta automáticamente antes de `save()` y `update()`
- Lanza `ConstraintViolationException` si hay errores
- Muestra mensajes de error detallados por consola

---

## 5. Capa de Servicios

### ✅ Requisito: Lógica de negocio en capa de servicios

**Estado**: ✅ COMPLETADO

**Descripción**: Implementación de servicios con lógica de negocio compleja.

#### 5.1 PedidoServices
- **Ubicación**: [`src/main/java/com/github/isaac/services/PedidoServices.java`](./src/main/java/com/github/isaac/services/PedidoServices.java)

**Operaciones**:

##### `crearDetallePedido(Producto producto, Integer cantidad)`
- Crea un detalle de pedido a partir de un producto
- Calcula automáticamente precio unitario y subtotal
- Retorna `DetallePedido` configurado

##### `crearPedido(Cliente cliente, Empresa empresa, List<DetallePedido> detalles)`
- Crea un pedido completo con sus detalles
- Establece estado inicial "PENDIENTE"
- Establece relaciones bidireccionales con `addDetalle()`
- Gestión de transacciones con rollback automático
- Retorna `Optional<Pedido>` (null si hay error)

##### `confirmarPedido(Long idPedido)`
- Confirma un pedido y actualiza el stock
- **Validaciones**:
  - Verifica existencia del pedido
  - Verifica stock suficiente de cada producto
- **Operaciones**:
  - Reduce stock de productos
  - Recalcula precios y subtotales
  - Calcula total del pedido
  - Cambia estado a "CONFIRMADO"
- Gestión de transacciones con rollback automático
- Retorna `boolean` indicando éxito/fracaso

---

## 6. Gestión de Transacciones

### ✅ Requisito: Manejo correcto de transacciones

**Estado**: ✅ COMPLETADO

**Descripción**: Todas las operaciones de modificación están protegidas con transacciones.

#### 6.1 Patrón de Transacción Utilizado

**Código base** en BaseRepositoryImpl:
```java
EntityTransaction transaction = em.getTransaction();
try {
    transaction.begin();
    // Operación de persistencia
    transaction.commit();
} catch (Exception e) {
    if (transaction.isActive()) {
        transaction.rollback();
    }
    // Manejo de error
}
```

**Operaciones transaccionales**:
- `save()`: Con flush antes del commit
- `update()`: Con merge
- `delete()` y `deleteById()`: Con verificación de entidad
- `crearPedido()`: En PedidoServices
- `confirmarPedido()`: En PedidoServices

---

## 7. Consultas y Operaciones CRUD

### ✅ Requisito: Implementación de consultas JPQL

**Estado**: ✅ COMPLETADO

**Descripción**: Consultas JPQL para operaciones avanzadas.

#### 7.1 Consultas Implementadas

**ClienteRepository**:
- Búsqueda por NIF (exacta)
- Búsqueda por nombre (parcial con LIKE e insensible a mayúsculas)

**PedidoRepository**:
- Obtener pedidos por cliente con JOIN FETCH (evita N+1)
- Calcular total de ventas por fecha con SUM

**BaseRepository**:
- `findAll()`: Consulta genérica para todas las entidades

#### 7.2 Operaciones CRUD Completas
Todas las entidades soportan:
- ✅ Create (save)
- ✅ Read (findById, findAll)
- ✅ Update (update)
- ✅ Delete (delete, deleteById)

---

## 8. Configuración del Proyecto

### ✅ Requisito: Configuración correcta de dependencias

**Estado**: ✅ COMPLETADO

**Descripción**: Proyecto Maven configurado con todas las dependencias necesarias.

#### 8.1 Archivo POM
- **Ubicación**: [`pom.xml`](./pom.xml)

**Dependencias principales**:
- Hibernate ORM 6.6.29.Final
- Jakarta Persistence API 3.1.0
- MariaDB JDBC Driver 3.5.6
- Jakarta Validation API 3.0.2
- Hibernate Validator 8.0.3.Final
- Expressly 6.0.0 (EL implementation)
- Lombok 1.18.42

**Configuración**:
- Java 17 (source y target)
- Encoding UTF-8
- GroupId: com.github.isaac
- ArtifactId: ac301

#### 8.2 Base de Datos
- **Script SQL**: [`bbdd.sql`](./bbdd.sql)
- **Contenido**:
  - 15 registros de clientes
  - 5 registros de empresas
  - 20 registros de productos
  - 10 registros de pedidos
  - Múltiples detalles de pedidos

---

## 📊 Resumen de Cumplimiento

| Categoría | Estado | Completitud |
|-----------|--------|-------------|
| Modelo de Datos | ✅ | 100% (5/5 entidades) |
| Persistencia JPA | ✅ | 100% |
| Patrón Repository | ✅ | 100% (5/5 repositorios) |
| Validación | ✅ | 100% |
| Capa de Servicios | ✅ | 100% |
| Transacciones | ✅ | 100% |
| Consultas JPQL | ✅ | 100% |
| Configuración | ✅ | 100% |

**Total: 8/8 requisitos completados (100%)**

---

## 🔍 Características Destacadas

1. **Validación en Cascada**: Las entidades Pedido y DetallePedido usan `@Valid` para validar relaciones
2. **Cálculos Automáticos**: 
   - Subtotales en DetallePedido (@PrePersist/@PreUpdate)
   - Total en Pedido (antes de persist/update)
3. **Relaciones Bidireccionales**: Métodos helper en Pedido para mantener consistencia
4. **Prevención N+1**: Uso de JOIN FETCH en consultas de pedidos
5. **Gestión de Stock**: Verificación y actualización automática en confirmación de pedidos
6. **Uso de Lombok**: Reduce código boilerplate significativamente
7. **Manejo de Errores**: Rollback automático y mensajes informativos
8. **Separación de Responsabilidades**: Arquitectura en capas bien definida

---

## 📝 Punto de Entrada

**Clase Main**: [`src/main/java/com/github/isaac/Main.java`](./src/main/java/com/github/isaac/Main.java)

Ejemplo de uso:
- Búsqueda de clientes por nombre
- Búsqueda de cliente por DNI

---

## 🎯 Conclusión

El proyecto AC301 cumple con **todos los requisitos** del proyecto RA3, implementando una arquitectura completa de persistencia con JPA/Hibernate, incluyendo:
- Modelo de datos robusto con validaciones
- Patrón Repository con operaciones CRUD completas
- Capa de servicios con lógica de negocio
- Gestión correcta de transacciones
- Consultas JPQL avanzadas
- Configuración profesional de proyecto Maven

El código está bien estructurado, sigue buenas prácticas y es mantenible.
