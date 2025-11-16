package com.github.isaac.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.github.isaac.entities.Cliente}
 */
@Value
public class ClienteDto implements Serializable {
    Long id;
    @Pattern(message = "El NIF debe tener formato 12345678A", regexp = "^[0-9]{8}[A-Z]$")
    @NotBlank(message = "El NIF/DNI es obligatorio")
    String nif;
    @Pattern(message = "El teléfono debe tener 9 dígitos", regexp = "^[0-9]{9}$")
    @NotBlank(message = "El teléfono es obligatorio")
    String telefono;
    @Size(message = "El nombre debe tener entre 2 y 50 caracteres", min = 2, max = 50)
    @NotBlank(message = "El nombre es obligatorio")
    String nombre;
    @Size(message = "Los apellidos deben tener entre 2 y 100 caracteres", min = 2, max = 100)
    @NotBlank(message = "Los apellidos son obligatorios")
    String apellidos;
    @Size(message = "La dirección no puede exceder 150 caracteres", max = 150)
    @NotBlank(message = "La dirección es obligatoria")
    String direccion;
    @Size(message = "La dirección de envío no puede exceder 150 caracteres", max = 150)
    @NotBlank(message = "La dirección de envío es obligatoria")
    String direccionEnvio;
}