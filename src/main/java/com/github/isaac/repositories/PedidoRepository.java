package com.github.isaac.repositories;

import com.github.isaac.entities.Pedido;
import com.github.isaac.repositories.base.BaseRepositoryImpl;

public class PedidoRepository extends BaseRepositoryImpl<Pedido, Long> {
    public PedidoRepository() {
        super(Pedido.class);
    }
}
