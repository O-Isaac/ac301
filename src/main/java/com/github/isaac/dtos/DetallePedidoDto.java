package com.github.isaac.dtos;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.github.isaac.entities.DetallePedido}
 */
@Value
public class DetallePedidoDto implements Serializable {
    Long id;
    @NotNull(message = "El producto es obligatorio")
    ProductoDto producto;
    @NotNull(message = "La cantidad es obligatoria")
    @Min(message = "La cantidad debe ser al menos 1", value = 1)
    Integer cantidad;
    @NotNull(message = "El precio unitario es obligatorio")
    @Digits(message = "El precio unitario debe tener máximo 8 dígitos enteros y 2 decimales", integer = 8, fraction = 2)
    BigDecimal precioUnitario;
    @Digits(message = "El subtotal debe tener máximo 10 dígitos enteros y 2 decimales", integer = 10, fraction = 2)
    BigDecimal subtotal;
}