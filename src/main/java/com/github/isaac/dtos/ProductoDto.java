package com.github.isaac.dtos;

import jakarta.validation.constraints.*;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.github.isaac.entities.Producto}
 */
@Value
public class ProductoDto implements Serializable {
    Long id;
    @Pattern(message = "El código debe tener entre 3 y 10 caracteres alfanuméricos en mayúsculas", regexp = "^[A-Z0-9]{3,10}$")
    @NotBlank(message = "El código del producto es obligatorio")
    String codigo;
    @Size(message = "El nombre debe tener entre 2 y 100 caracteres", min = 2, max = 100)
    @NotBlank(message = "El nombre del producto es obligatorio")
    String nombre;
    @Size(message = "La descripción no puede exceder 255 caracteres", max = 255)
    @NotBlank(message = "La descripción es obligatoria")
    String descripcion;
    @NotNull(message = "El precio es obligatorio")
    @Digits(message = "El precio debe tener máximo 8 dígitos enteros y 2 decimales", integer = 8, fraction = 2)
    BigDecimal precio;
    @NotNull(message = "El stock es obligatorio")
    @Min(message = "El stock no puede ser negativo", value = 0)
    Integer stock;
}