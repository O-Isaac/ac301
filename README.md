# AC301 - Sistema de Gestión de Pedidos

Sistema completo de gestión de pedidos con interfaz gráfica desarrollado con Java, JPA/Hibernate, Swing y MariaDB. Implementa una arquitectura en capas con patrón Repository, DTOs con MapStruct, y una interfaz de usuario moderna con FlatLaf.

## 🎯 Descripción

Este proyecto implementa un sistema completo de gestión de pedidos para empresas con interfaz gráfica moderna, incluyendo:
- **Interfaz gráfica** moderna y responsive con FlatLaf Dark Theme
- Gestión completa de clientes con validación de NIF
- Gestión de empresas proveedoras con validación de CIF
- Catálogo de productos con control de stock
- Sistema de creación y confirmación de pedidos
- Reportes de ventas exportables a JSON
- Control de stock automático
- Arquitectura MVC para la capa de presentación

## 🏗️ Arquitectura

El proyecto sigue una arquitectura multicapa completa:

```
├── Presentación (gui)          → Interfaz gráfica con Swing + FlatLaf
│   ├── Views                   → Paneles de visualización
│   ├── Controllers             → Controladores MVC
│   ├── Forms                   → Formularios de alta/edición
│   ├── Models                  → Modelos de tabla (TableModel)
│   ├── Components              → Componentes reutilizables
│   └── Utils                   → Utilidades de GUI
├── DTOs (dtos)                 → Objetos de transferencia de datos
├── Mappers (mappers)           → MapStruct para conversión DTO/Entity
├── Servicios (services)        → Lógica de negocio
├── Repositorios (repositories) → Acceso a datos con patrón Repository
├── Entidades (entities)        → Modelo de datos con JPA
└── Utilidades (utils)          → JPAUtil para gestión de persistencia
```

## 📋 Requisitos

- Java 17 o superior
- Maven 3.x
- MariaDB 10.x o superior
- Sistema operativo con soporte para GUI (Windows, macOS, Linux con X11)

## 🚀 Características Principales

### Interfaz Gráfica
- **Vista de Empresas**: Gestión completa de empresas proveedoras
- **Vista de Clientes**: Administración de base de clientes
- **Vista de Productos**: Catálogo de productos con stock
- **Vista de Pedidos**: Creación y gestión de pedidos
- **Vista de Detalles**: Visualización de líneas de pedido
- **Tema Moderno**: FlatLaf Mac Dark con fuente Roboto
- **Tablas Interactivas**: CRUD completo desde la interfaz
- **Formularios de Validación**: Validación en tiempo real

### Entidades Implementadas
- **Cliente**: Gestión de clientes con validación de NIF
- **Empresa**: Empresas proveedoras con validación de CIF
- **Producto**: Catálogo de productos con control de stock
- **Pedido**: Pedidos con cálculo automático de totales
- **DetallePedido**: Líneas de pedido con cálculo de subtotales

### DTOs (Data Transfer Objects)
- **ClienteDto**: Transferencia de datos de clientes
- **EmpresaDto**: Transferencia de datos de empresas
- **ProductoDto**: Transferencia de datos de productos
- **DetallePedidoDto**: Transferencia de líneas de pedido
- **ReporteVentasDto**: DTO especializado para reportes de ventas

### Funcionalidades
- ✅ **GUI Moderna**: Interfaz gráfica completa con FlatLaf
- ✅ **CRUD Visual**: Operaciones completas desde la interfaz
- ✅ **Mapeo DTO/Entity**: Conversión automática con MapStruct
- ✅ **Exportación JSON**: Reportes de ventas en formato JSON
- ✅ **Validación automática**: Jakarta Bean Validation en DTOs y entidades
- ✅ **Gestión de transacciones**: Rollback automático en errores
- ✅ **Relaciones bidireccionales**: Mantenimiento automático de integridad
- ✅ **Cálculo automático**: Totales y subtotales auto-calculados
- ✅ **Verificación de stock**: Control de inventario en tiempo real
- ✅ **Consultas JPQL optimizadas**: JOIN FETCH para evitar N+1
- ✅ **Patrón Repository genérico**: Reutilización de código
- ✅ **Arquitectura MVC**: Separación de responsabilidades en GUI

## 📦 Dependencias

### Persistencia y Base de Datos
- **Hibernate ORM** 6.6.29.Final - Proveedor JPA
- **Jakarta Persistence API** 3.1.0 - Especificación JPA
- **MariaDB JDBC Driver** 3.5.6 - Conector de base de datos

### Validación
- **Jakarta Validation** 3.0.2 - Validación de datos
- **Hibernate Validator** 8.0.3.Final - Implementación de validación

### Mapeo y Serialización
- **MapStruct** 1.6.3 - Mapeo automático DTO/Entity
- **Jackson Core** 2.15.2 - Serialización JSON
- **Jackson Datatype JSR310** 2.15.2 - Soporte para Java 8 Date/Time

### Interfaz Gráfica
- **FlatLaf** 3.6.2 - Look and Feel moderno
- **FlatLaf IntelliJ Themes** 3.6.2 - Temas adicionales
- **FlatLaf Extras** 3.6.2 - Componentes adicionales
- **FlatLaf Roboto Font** 2.137 - Fuente Roboto

### Utilidades
- **Lombok** 1.18.42 - Reducción de código boilerplate
- **Expressly** 6.0.0 - Implementación de Expression Language

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

### Ejecutar la aplicación GUI
```bash
mvn exec:java -Dexec.mainClass="com.github.isaac.gui.MainView"
```

### Generar reporte de ventas en JSON
```bash
mvn exec:java -Dexec.mainClass="com.github.isaac.Main"
```

Este comando genera un archivo `reportes_ventas.json` con todos los pedidos y sus detalles.

## 💡 Ejemplos de Uso

### Interfaz Gráfica
La aplicación cuenta con 5 pestañas principales:
- **Empresas**: Ver, crear, editar y eliminar empresas proveedoras
- **Clientes**: Gestionar base de datos de clientes
- **Productos**: Administrar catálogo de productos
- **Pedidos**: Crear y confirmar pedidos
- **Detalle Pedidos**: Consultar líneas de pedido

### Uso Programático

#### Buscar clientes
```java
ClienteRepository clienteRepository = new ClienteRepository();

// Buscar por nombre (parcial)
clienteRepository.buscarPorNombre("Juan")
    .forEach(System.out::println);

// Buscar por DNI (exacto)
clienteRepository.obtenerPorDni("12345678A")
    .ifPresent(System.out::println);
```

#### Crear un pedido
```java
PedidoServices pedidoService = new PedidoServices();

// Crear detalles
DetallePedido detalle = pedidoService.crearDetallePedido(producto, 5);

// Crear pedido
Pedido pedido = pedidoService.crearPedido(
    cliente, 
    empresa, 
    List.of(detalle)
);

// Confirmar pedido (actualiza stock)
pedidoService.confirmarPedido(pedido.getId());
```

#### Generar reporte de ventas
```java
PedidoServices pedidoService = new PedidoServices();
List<ReporteVentasDto> reportes = pedidoService.reportesVentas();

// Convertir a JSON con Jackson
ObjectMapper mapper = new ObjectMapper();
mapper.registerModule(new JavaTimeModule());
String json = mapper.writeValueAsString(reportes);
```

## 📊 Verificación de Requisitos

Este proyecto cumple con **todos los requisitos (100%)** del Proyecto RA3 y los extiende:

| Requisito | Estado | Extras |
|-----------|--------|--------|
| Modelo de Datos | ✅ 100% | + DTOs |
| Persistencia JPA | ✅ 100% | + Consultas avanzadas |
| Patrón Repository | ✅ 100% | + Genérico reutilizable |
| Validación | ✅ 100% | + En DTOs y entidades |
| Capa de Servicios | ✅ 100% | + Reportes JSON |
| Transacciones | ✅ 100% | + Rollback automático |
| Consultas JPQL | ✅ 100% | + JOIN FETCH optimizado |
| Configuración | ✅ 100% | + Maven completo |
| **Interfaz Gráfica** | ✅ 100% | GUI completa con Swing |
| **Arquitectura MVC** | ✅ 100% | Patrón MVC en presentación |
| **Mapeo DTO/Entity** | ✅ 100% | MapStruct integrado |
| **Exportación JSON** | ✅ 100% | Jackson con módulo JSR310 |

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
│   ├── dtos/               # Data Transfer Objects
│   │   ├── ClienteDto.java
│   │   ├── EmpresaDto.java
│   │   ├── ProductoDto.java
│   │   ├── DetallePedidoDto.java
│   │   └── ReporteVentasDto.java
│   ├── mappers/            # MapStruct mappers
│   │   └── PedidoMapper.java
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
│   ├── gui/                # Interfaz gráfica (MVC)
│   │   ├── MainView.java
│   │   ├── views/          # Vistas (paneles)
│   │   │   ├── ClientesPane.java
│   │   │   ├── EmpresaPane.java
│   │   │   ├── ProductoPane.java
│   │   │   ├── PedidoPane.java
│   │   │   └── DetallePedidoPane.java
│   │   ├── controllers/    # Controladores MVC
│   │   │   ├── ClientesController.java
│   │   │   ├── EmpresaController.java
│   │   │   ├── ProductoController.java
│   │   │   └── PedidosController.java
│   │   ├── forms/          # Formularios de alta/edición
│   │   │   ├── FormCliente.java
│   │   │   ├── FormEmpresa.java
│   │   │   ├── FormProducto.java
│   │   │   └── FormPedido.java
│   │   ├── models/         # Modelos de tabla
│   │   │   ├── ClienteTableModel.java
│   │   │   ├── EmpresaTableModel.java
│   │   │   ├── ProductoTableModel.java
│   │   │   ├── PedidoTableModel.java
│   │   │   ├── DetallePedidoTableModel.java
│   │   │   └── DetallePedidoEditTableModel.java
│   │   ├── components/     # Componentes reutilizables
│   │   │   └── ActionButtonsPanel.java
│   │   └── utils/          # Utilidades de GUI
│   │       └── CaptureExceptions.java
│   ├── utils/              # Utilidades generales
│   │   └── JPAUtil.java
│   └── Main.java           # Generador de reportes JSON
├── src/main/resources/
│   ├── isaac/themes/       # Temas personalizados FlatLaf
│   │   ├── FlatLaf.properties
│   │   └── FlatDarkLaf.properties
│   └── META-INF/
│       └── persistence.xml # Configuración JPA
├── bbdd.sql                # Script de base de datos
├── pom.xml                 # Configuración Maven
└── ROADMAP.md              # Documentación completa
```

## 🔍 Patrones y Buenas Prácticas

- **Patrón Repository**: Abstracción de acceso a datos con implementación genérica
- **Patrón MVC**: Separación clara en la capa de presentación (GUI)
- **DTOs**: Objetos de transferencia para desacoplar presentación de persistencia
- **MapStruct**: Mapeo automático y eficiente entre DTOs y entidades
- **Separación de capas**: Presentación, Servicios, Repositorios, Entidades
- **Validación declarativa**: Anotaciones Jakarta Validation en DTOs y entidades
- **Gestión automática de relaciones**: Métodos helper bidireccionales
- **Prevención de consultas N+1**: JOIN FETCH en consultas JPQL
- **Gestión de transacciones**: Try-with-resources y rollback automático
- **Singleton de EntityManagerFactory**: Optimización de recursos JPA
- **Inmutabilidad en DTOs**: Uso de `@Value` de Lombok
- **UI/UX moderno**: FlatLaf con tema dark y fuente Roboto
- **Componentes reutilizables**: Panel de acciones común para todas las vistas
- **Manejo de excepciones**: Captura centralizada en la capa de presentación

## 👥 Autor

- **Isaac** - [@O-Isaac](https://github.com/O-Isaac)

## 📄 Licencia

Este proyecto es parte del curso AC301.

---

Para más información detallada, consulta el [ROADMAP.md](ROADMAP.md) que incluye enlaces directos a cada componente del código.
