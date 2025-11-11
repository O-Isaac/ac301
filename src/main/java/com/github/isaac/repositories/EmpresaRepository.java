package com.github.isaac.repositories;

import com.github.isaac.entities.Empresa;
import com.github.isaac.repositories.base.BaseRepositoryImpl;

public class EmpresaRepository extends BaseRepositoryImpl<Empresa, Long> {
    public EmpresaRepository() {
        super(Empresa.class);
    }
}
