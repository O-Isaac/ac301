# AC301 - Sistema de Gestión de Pedidos

Sistema de gestión de pedidos desarrollado con Java, JPA/Hibernate y MariaDB, que implementa una arquitectura de persistencia completa siguiendo el patrón Repository.

## 🎯 Descripción

Este proyecto implementa un sistema completo de gestión de pedidos para empresas, incluyendo:
- Gestión de clientes
- Gestión de empresas proveedoras
- Catálogo de productos
- Creación y confirmación de pedidos
- Control de stock automático

## 🏗️ Arquitectura

El proyecto sigue una arquitectura en capas:

```
├── Entidades (entities)      → Modelo de datos con JPA
├── Repositorios (repositories) → Acceso a datos con patrón Repository
├── Servicios (services)       → Lógica de negocio
└── Utilidades (utils)         → JPAUtil para gestión de persistencia
```

## 📋 Requisitos

- Java 17
- Maven 3.x
- MariaDB 10.x o superior

## 🚀 Características Principales

### Entidades Implementadas
- **Cliente**: Gestión de clientes con validación de NIF
- **Empresa**: Empresas proveedoras con validación de CIF
- **Producto**: Catálogo de productos con control de stock
- **Pedido**: Pedidos con cálculo automático de totales
- **DetallePedido**: Líneas de pedido con cálculo de subtotales

### Funcionalidades
- ✅ CRUD completo para todas las entidades
- ✅ Validación automática con Jakarta Bean Validation
- ✅ Gestión de transacciones con rollback automático
- ✅ Relaciones bidireccionales entre entidades
- ✅ Cálculo automático de totales y subtotales
- ✅ Verificación y actualización de stock
- ✅ Consultas JPQL optimizadas (JOIN FETCH)
- ✅ Patrón Repository genérico

## 📦 Dependencias

- **Hibernate ORM** 6.6.29.Final - Proveedor JPA
- **Jakarta Persistence API** 3.1.0 - Especificación JPA
- **MariaDB JDBC Driver** 3.5.6 - Conector de base de datos
- **Jakarta Validation** 3.0.2 - Validación de datos
- **Hibernate Validator** 8.0.3.Final - Implementación de validación
- **Lombok** 1.18.42 - Reducción de código boilerplate

## 🗃️ Base de Datos

El script SQL incluido (`bbdd.sql`) proporciona:
- Esquema de base de datos completo
- 15 clientes de ejemplo
- 5 empresas proveedoras
- 20 productos
- 10 pedidos con sus detalles

**Configuración**:
```properties
URL: jdbc:mariadb://localhost:3306/ac301
Usuario: root
Contraseña: (vacía)
```

## 📚 Documentación

📖 **[Ver Roadmap Completo](ROADMAP.md)** - Documentación detallada de todos los requisitos implementados

El roadmap incluye:
- Descripción completa de cada entidad
- Enlaces directos al código
- Explicación de patrones implementados
- Verificación de requisitos del Proyecto RA3

## 🔧 Compilación y Ejecución

### Compilar el proyecto
```bash
mvn clean compile
```

### Ejecutar la aplicación
```bash
mvn exec:java -Dexec.mainClass="com.github.isaac.Main"
```

## 💡 Ejemplos de Uso

### Buscar clientes
```java
ClienteRepository clienteRepository = new ClienteRepository();

// Buscar por nombre (parcial)
clienteRepository.buscarPorNombre("Juan")
    .forEach(System.out::println);

// Buscar por DNI (exacto)
clienteRepository.obtenerPorDni("12345678A")
    .ifPresent(System.out::println);
```

### Crear un pedido
```java
PedidoServices pedidoService = new PedidoServices();

// Crear detalles
DetallePedido detalle = pedidoService.crearDetallePedido(producto, 5);

// Crear pedido
Optional<Pedido> pedido = pedidoService.crearPedido(
    cliente, 
    empresa, 
    List.of(detalle)
);

// Confirmar pedido (actualiza stock)
pedidoService.confirmarPedido(pedido.get().getId());
```

## 📊 Verificación de Requisitos

Este proyecto cumple con **todos los requisitos (100%)** del Proyecto RA3:

| Requisito | Estado |
|-----------|--------|
| Modelo de Datos | ✅ 100% |
| Persistencia JPA | ✅ 100% |
| Patrón Repository | ✅ 100% |
| Validación | ✅ 100% |
| Capa de Servicios | ✅ 100% |
| Transacciones | ✅ 100% |
| Consultas JPQL | ✅ 100% |
| Configuración | ✅ 100% |

Ver detalles completos en [ROADMAP.md](ROADMAP.md)

## 📁 Estructura del Proyecto

```
ac301/
├── src/main/java/com/github/isaac/
│   ├── entities/           # Entidades JPA
│   │   ├── Cliente.java
│   │   ├── Empresa.java
│   │   ├── Producto.java
│   │   ├── Pedido.java
│   │   └── DetallePedido.java
│   ├── repositories/       # Capa de acceso a datos
│   │   ├── base/
│   │   │   ├── BaseRepository.java
│   │   │   └── BaseRepositoryImpl.java
│   │   ├── ClienteRepository.java
│   │   ├── EmpresaRepository.java
│   │   ├── ProductoRepository.java
│   │   ├── PedidoRepository.java
│   │   └── DetallePedidoRepository.java
│   ├── services/           # Lógica de negocio
│   │   └── PedidoServices.java
│   ├── utils/              # Utilidades
│   │   └── JPAUtil.java
│   └── Main.java           # Punto de entrada
├── src/main/resources/
│   └── META-INF/
│       └── persistence.xml # Configuración JPA
├── bbdd.sql                # Script de base de datos
├── pom.xml                 # Configuración Maven
└── ROADMAP.md              # Documentación completa
```

## 🔍 Patrones y Buenas Prácticas

- **Patrón Repository**: Abstracción de acceso a datos
- **Separación de capas**: Entidades, Repositorios, Servicios
- **Validación declarativa**: Uso de anotaciones Jakarta Validation
- **Gestión automática de relaciones**: Métodos helper bidireccionales
- **Prevención de consultas N+1**: JOIN FETCH en consultas
- **Gestión de transacciones**: Try-with-resources y rollback automático
- **Singleton de EntityManagerFactory**: Optimización de recursos
- **Lombok**: Reducción de código repetitivo

## 👥 Autor

- **Isaac** - [@O-Isaac](https://github.com/O-Isaac)

## 📄 Licencia

Este proyecto es parte del curso AC301.

---

Para más información detallada, consulta el [ROADMAP.md](ROADMAP.md) que incluye enlaces directos a cada componente del código.
