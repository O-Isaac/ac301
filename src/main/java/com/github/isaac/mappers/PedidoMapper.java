package com.github.isaac.mappers;

import com.github.isaac.dtos.ReporteVentasDto;
import com.github.isaac.entities.Pedido;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
public interface PedidoMapper {
    @Mapping(source = "lineas", target = "detalles")
    @Mapping(source = "cabeceraEmpresa", target = "empresa")
    @Mapping(source = "cabeceraCliente", target = "cliente")
    Pedido toEntity(ReporteVentasDto reporteVentasDto);

    @AfterMapping
    default void linkDetalles(@MappingTarget Pedido pedido) {
        pedido.getDetalles().forEach(detalle -> detalle.setPedido(pedido));
    }

    @InheritInverseConfiguration(name = "toEntity")
    ReporteVentasDto toDto(Pedido pedido);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Pedido partialUpdate(ReporteVentasDto reporteVentasDto, @MappingTarget Pedido pedido);
}