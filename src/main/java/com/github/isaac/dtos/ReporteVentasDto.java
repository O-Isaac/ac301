package com.github.isaac.dtos;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link com.github.isaac.entities.Pedido}
 */
@Value
public class ReporteVentasDto implements Serializable {
    Long id;
    @NotNull(message = "El cliente es obligatorio")
    ClienteDto cabeceraCliente;
    @NotNull(message = "La empresa es obligatoria")
    EmpresaDto cabeceraEmpresa;
    @NotNull(message = "El total es obligatorio")
    @Digits(message = "El total debe tener máximo 10 dígitos enteros y 2 decimales", integer = 10, fraction = 2)
    BigDecimal total;
    LocalDate fecha;
    String estado;
    List<DetallePedidoDto> lineas;
}