package com.github.isaac.repositories;

import com.github.isaac.entities.DetallePedido;
import com.github.isaac.repositories.base.BaseRepositoryImpl;

public class DetallePedidoRepository extends BaseRepositoryImpl<DetallePedido, Long> {
    public DetallePedidoRepository() {
        super(DetallePedido.class);
    }
}
