-- Insertar clientes (15 registros)
INSERT INTO clientes (nombre, apellidos, nif, direccion, direccion_envio, telefono) VALUES
                                                                                        ('Juan', 'García López', '12345678A', 'Calle Mayor 10, Madrid', 'Calle Mayor 10, Madrid', '911234567'),
                                                                                        ('María', 'Rodríguez Pérez', '23456789B', 'Avenida Libertad 25, Barcelona', 'Avenida Libertad 25, Barcelona', '932345678'),
                                                                                        ('Carlos', 'Martínez Sánchez', '34567890C', 'Plaza España 5, Valencia', 'Plaza España 5, Valencia', '963456789'),
                                                                                        ('Ana', 'López Fernández', '45678901D', 'Calle Sol 15, Sevilla', 'Calle Sol 15, Sevilla', '954567890'),
                                                                                        ('Pedro', 'González Ruiz', '56789012E', 'Avenida Constitución 30, Bilbao', 'Avenida Constitución 30, Bilbao', '944678901'),
                                                                                        ('Laura', 'Díaz Moreno', '67890123F', 'Calle Luna 8, Zaragoza', 'Calle Luna 8, Zaragoza', '976789012'),
                                                                                        ('Miguel', 'Hernández Castro', '78901234G', 'Plaza Mayor 12, Málaga', 'Plaza Mayor 12, Málaga', '952890123'),
                                                                                        ('Carmen', 'Jiménez Ortiz', '89012345H', 'Calle Paz 20, Murcia', 'Calle Paz 20, Murcia', '968901234'),
                                                                                        ('José', 'Ruiz Navarro', '90123456I', 'Avenida Europa 18, Alicante', 'Avenida Europa 18, Alicante', '965012345'),
                                                                                        ('Isabel', 'Moreno Gil', '01234567J', 'Calle Real 7, Valladolid', 'Calle Real 7, Valladolid', '983123456'),
                                                                                        ('Francisco', 'Álvarez Romero', '11234567K', 'Plaza Castilla 3, Córdoba', 'Plaza Castilla 3, Córdoba', '957234567'),
                                                                                        ('Rosa', 'Torres Molina', '21234567L', 'Calle Olmo 22, Granada', 'Calle Olmo 22, Granada', '958345678'),
                                                                                        ('Antonio', 'Ramírez Vega', '31234567M', 'Avenida Andalucía 16, Cádiz', 'Avenida Andalucía 16, Cádiz', '956456789'),
                                                                                        ('Lucía', 'Flores Serrano', '41234567N', 'Calle Norte 9, Santander', 'Calle Norte 9, Santander', '942567890'),
                                                                                        ('David', 'Iglesias Méndez', '51234567O', 'Plaza Central 14, Pamplona', 'Plaza Central 14, Pamplona', '948678901');

-- Insertar empresas (5 registros)
INSERT INTO empresas (nombre, cif, domicilio, localidad, telefono, email) VALUES
                                                                              ('TechStore SL', 'A12345678', 'Polígono Industrial Norte, Nave 5', 'Madrid', '917001000', 'info@techstore.es'),
                                                                              ('Distribuciones Gamma SA', 'B23456789', 'Calle Comercio 45', 'Barcelona', '934002000', 'contacto@gamma.es'),
                                                                              ('Suministros Beta SL', 'C34567890', 'Avenida Industrial 78', 'Valencia', '963003000', 'ventas@beta.es'),
                                                                              ('Mayorista Delta SA', 'D45678901', 'Polígono Sur, Calle 12', 'Sevilla', '954004000', 'info@delta.es'),
                                                                              ('Comercial Omega SL', 'E56789012', 'Zona Empresarial Este, Parcela 9', 'Bilbao', '944005000', 'pedidos@omega.es');

-- Insertar productos (20 registros)
INSERT INTO productos (codigo, nombre, descripcion, precio, stock) VALUES
                                                                       ('PROD001', 'Ratón inalámbrico', 'Ratón óptico inalámbrico con sensor de alta precisión', 25.99, 150),
                                                                       ('PROD002', 'Teclado mecánico', 'Teclado gaming con switches mecánicos RGB', 89.99, 75),
                                                                       ('PROD003', 'Monitor 24 pulgadas', 'Monitor Full HD IPS con tecnología antiparpadeo', 179.99, 45),
                                                                       ('PROD004', 'Webcam HD', 'Cámara web 1080p con micrófono incorporado', 45.50, 120),
                                                                       ('PROD005', 'Auriculares Bluetooth', 'Auriculares inalámbricos con cancelación de ruido', 129.99, 90),
                                                                       ('PROD006', 'Alfombrilla gaming', 'Alfombrilla XXL de tela con base antideslizante', 19.99, 200),
                                                                       ('PROD007', 'Hub USB 3.0', 'Hub de 7 puertos USB 3.0 con alimentación', 32.99, 110),
                                                                       ('PROD008', 'Disco duro externo 1TB', 'Disco duro portátil USB 3.0 de 1TB', 65.99, 80),
                                                                       ('PROD009', 'Memoria USB 64GB', 'Pendrive USB 3.1 de alta velocidad', 15.99, 250),
                                                                       ('PROD010', 'Cable HDMI 2m', 'Cable HDMI 2.0 certificado 4K', 12.50, 180),
                                                                       ('PROD011', 'Soporte portátil', 'Soporte ergonómico ajustable para laptop', 28.99, 95),
                                                                       ('PROD012', 'Micrófono condensador', 'Micrófono USB profesional con trípode', 75.00, 60),
                                                                       ('PROD013', 'Lámpara LED escritorio', 'Lámpara regulable con carga inalámbrica', 42.99, 85),
                                                                       ('PROD014', 'Router WiFi 6', 'Router de doble banda con tecnología WiFi 6', 149.99, 50),
                                                                       ('PROD015', 'Cargador USB-C 65W', 'Cargador rápido con tecnología GaN', 38.50, 140),
                                                                       ('PROD016', 'Funda portátil 15"', 'Funda acolchada resistente al agua', 24.99, 160),
                                                                       ('PROD017', 'Adaptador multipuerto', 'Adaptador USB-C a HDMI/USB/Ethernet', 55.99, 70),
                                                                       ('PROD018', 'Ventilador portátil', 'Ventilador USB silencioso con LED', 18.99, 125),
                                                                       ('PROD019', 'Organizador cables', 'Kit de gestión de cables de escritorio', 14.50, 190),
                                                                       ('PROD020', 'Limpiador pantallas', 'Kit de limpieza con spray y paño microfibra', 9.99, 220);

-- Insertar pedidos (10 registros)
INSERT INTO pedidos (cliente_id, empresa_id, total, fecha) VALUES
                                                               (1, 1, 295.97, '2024-03-15'),
                                                               (2, 2, 179.99, '2024-05-22'),
                                                               (3, 1, 485.95, '2024-01-08'),
                                                               (4, 3, 155.48, '2024-07-14'),
                                                               (5, 2, 324.97, '2024-02-28'),
                                                               (6, 4, 89.99, '2024-06-03'),
                                                               (7, 1, 567.94, '2024-04-19'),
                                                               (8, 5, 215.98, '2024-09-11'),
                                                               (9, 3, 142.49, '2024-08-25'),
                                                               (10, 1, 399.96, '2024-10-07');

-- Insertar detalles de pedidos
INSERT INTO detalles_pedido (pedido_id, producto_id, cantidad, precio_unitario, subtotal) VALUES
-- Pedido 1
(1, 1, 2, 25.99, 51.98),
(1, 2, 1, 89.99, 89.99),
(1, 6, 3, 19.99, 59.97),
(1, 10, 3, 12.50, 37.50),
-- Pedido 2
(2, 3, 1, 179.99, 179.99),
-- Pedido 3
(3, 5, 2, 129.99, 259.98),
(3, 7, 1, 32.99, 32.99),
(3, 8, 1, 65.99, 65.99),
-- Pedido 4
(4, 9, 4, 15.99, 63.96),
(4, 19, 2, 14.50, 29.00),
(4, 20, 3, 9.99, 29.97),
-- Pedido 5
(5, 4, 2, 45.50, 91.00),
(5, 11, 3, 28.99, 86.97),
(5, 13, 1, 42.99, 42.99),
-- Pedido 6
(6, 2, 1, 89.99, 89.99),
-- Pedido 7
(7, 14, 2, 149.99, 299.98),
(7, 17, 1, 55.99, 55.99),
(7, 12, 1, 75.00, 75.00),
-- Pedido 8
(8, 15, 3, 38.50, 115.50),
(8, 16, 2, 24.99, 49.98),
(8, 18, 1, 18.99, 18.99),
-- Pedido 9
(9, 1, 3, 25.99, 77.97),
(9, 10, 2, 12.50, 25.00),
(9, 19, 1, 14.50, 14.50),
-- Pedido 10
(10, 3, 2, 179.99, 359.98),
(10, 7, 1, 32.99, 32.99);