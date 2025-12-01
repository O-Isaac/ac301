-- =========================
-- CLIENTES
-- =========================
INSERT INTO clientes (nombre, apellidos, email, ciudad, nif, telefono, direccion, direccion_envio) VALUES
                                                                                                       ('Ana', 'García López', 'ana.garcia@gmail.com', 'Madrid', '12345678A', '600123456', 'Calle Falsa 1', 'Calle Falsa 1'),
                                                                                                       ('Carlos', 'López Pérez', 'carlos.lopez@yahoo.com', 'Barcelona', '23456789B', '600234567', 'Calle Verdadera 2', 'Calle Verdadera 2'),
                                                                                                       ('Lucía', 'Fernandez Ruiz', 'lucia.fer@hotmail.com', 'Sevilla', '34567890C', '600345678', 'Av. Sevilla 3', 'Av. Sevilla 3'),
                                                                                                       ('Marcos', 'Ruiz Martínez', 'marcos.gamer@gmail.com', 'Valencia', '45678901D', '600456789', 'Calle Valencia 4', 'Calle Valencia 4'),
                                                                                                       ('Elena', 'Tech Sánchez', 'elena.dev@empresa.com', 'Madrid', '56789012E', '600567890', 'Calle Madrid 5', 'Calle Madrid 5'),
                                                                                                       ('Pedro', 'BigSpender García', 'pedro.vip@gmail.com', 'Bilbao', '67890123F', '600678901', 'Calle Bilbao 6', 'Calle Bilbao 6'),
                                                                                                       ('Sofia', 'Martínez López', 'sofia.m@outlook.com', 'Madrid', '78901234G', '600789012', 'Calle Madrid 7', 'Calle Madrid 7'),
                                                                                                       ('Jorge', 'Ramirez Torres', 'jorge.r@gmail.com', 'Zaragoza', '89012345H', '600890123', 'Calle Zaragoza 8', 'Calle Zaragoza 8'),
                                                                                                       ('Marta', 'Díaz Fernández', 'marta.d@yahoo.com', 'Barcelona', '90123456J', '600901234', 'Calle Barcelona 9', 'Calle Barcelona 9'),
                                                                                                       ('Luis', 'Torres Díaz', 'luis.torres@gmail.com', 'Vigo', '01234567K', '600012345', 'Calle Vigo 10', 'Calle Vigo 10');

-- =========================
-- PRODUCTOS
-- =========================
INSERT INTO productos (nombre, precio, categoria, codigo, descripcion, stock) VALUES
                                                                                  ('Portátil Gaming MSI', 1200.50, 'Ordenadores', 'PCMSI001', 'Portátil potente para gaming', 10),
                                                                                  ('Ratón Óptico Logitech', 25.00, 'Periféricos', 'LOGI002', 'Ratón ergonómico', 50),
                                                                                  ('Monitor 27 Pulgadas 4K', 350.00, 'Periféricos', 'MONI003', 'Monitor 4K de alta resolución', 20),
                                                                                  ('Teclado Mecánico RGB', 89.99, 'Periféricos', 'TECL004', 'Teclado mecánico con iluminación RGB', 30),
                                                                                  ('Silla Gaming Razer', 299.99, 'Mobiliario', 'SILL005', 'Silla ergonómica para gaming', 15),
                                                                                  ('Auriculares Bluetooth', 59.90, 'Audio', 'AURI006', 'Auriculares inalámbricos', 40),
                                                                                  ('Smartphone Alta Gama', 950.00, 'Telefonía', 'PHON007', 'Smartphone de última generación', 25),
                                                                                  ('Cable HDMI 2.0', 10.00, 'Cables', 'CAB008', 'Cable HDMI de alta velocidad', 100),
                                                                                  ('Disco SSD 1TB', 110.00, 'Almacenamiento', 'SSD009', 'Disco SSD rápido de 1TB', 35),
                                                                                  ('Tarjeta Gráfica RTX 4060', 450.00, 'Componentes', 'GPU010', 'Tarjeta gráfica de última generación', 12);

-- =========================
-- EMPRESAS
-- =========================
INSERT INTO empresas (nombre, cif, telefono, email, localidad, domicilio) VALUES
                                                                              ('Tech Solutions S.A.', 'A12345678', '912345678', 'contact@techsolutions.com', 'Madrid', 'Calle Empresa 1'),
                                                                              ('Gaming World S.L.', 'B23456789', '934567890', 'info@gamingworld.com', 'Barcelona', 'Calle Empresa 2');

-- =========================
-- PEDIDOS
-- =========================
INSERT INTO pedidos (cliente_id, empresa_id, total, estado, fecha) VALUES
                                                                       (1, 1, 114.99, 'NO CONFIRMADO', '2023-10-01'),
                                                                       (2, 1, 119.80, 'NO CONFIRMADO', '2023-10-02'),
                                                                       (6, 2, 1200.50, 'NO CONFIRMADO', '2023-10-05'),
                                                                       (6, 2, 700.00, 'NO CONFIRMADO', '2023-10-06'),
                                                                       (4, 2, 749.99, 'NO CONFIRMADO', '2023-10-07'),
                                                                       (1, 1, 50.00, 'NO CONFIRMADO', '2023-10-08'),
                                                                       (3, 1, 110.00, 'NO CONFIRMADO', '2023-10-09'),
                                                                       (5, 1, 25.00, 'NO CONFIRMADO', '2023-10-10'),
                                                                       (7, 1, 950.00, 'NO CONFIRMADO', '2023-10-11'),
                                                                       (8, 2, 89.99, 'NO CONFIRMADO', '2023-10-12'),
                                                                       (9, 2, 59.90, 'NO CONFIRMADO', '2023-10-13'),
                                                                       (10, 2, 20.00, 'NO CONFIRMADO', '2023-10-14');

-- =========================
-- DETALLES DE PEDIDOS (con subtotal calculado)
-- =========================
INSERT INTO detalles_pedido (pedido_id, producto_id, cantidad, precio_unitario, subtotal) VALUES
                                                                                              (1, 2, 1, 25.00, 25.00),
                                                                                              (1, 4, 1, 89.99, 89.99),
                                                                                              (2, 6, 2, 59.90, 119.80),
                                                                                              (3, 1, 1, 1200.50, 1200.50),
                                                                                              (4, 3, 2, 350.00, 700.00),
                                                                                              (5, 5, 1, 299.99, 299.99),
                                                                                              (5, 10, 1, 450.00, 450.00),
                                                                                              (6, 8, 5, 10.00, 50.00),
                                                                                              (7, 9, 1, 110.00, 110.00),
                                                                                              (8, 2, 1, 25.00, 25.00),
                                                                                              (9, 7, 1, 950.00, 950.00),
                                                                                              (10, 4, 1, 89.99, 89.99),
                                                                                              (11, 6, 1, 59.90, 59.90),
                                                                                              (12, 8, 2, 10.00, 20.00);
