package com.github.isaac.repositories;

import com.github.isaac.entities.Producto;
import com.github.isaac.repositories.base.BaseRepositoryImpl;

public class ProductoRepository extends BaseRepositoryImpl<Producto, Long> {
    public ProductoRepository() {
        super(Producto.class);
    }
}
