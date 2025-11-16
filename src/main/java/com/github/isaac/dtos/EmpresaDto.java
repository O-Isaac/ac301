package com.github.isaac.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.github.isaac.entities.Empresa}
 */
@Value
public class EmpresaDto implements Serializable {
    Long id;
    @Pattern(message = "El CIF debe tener formato A12345678 o A1234567B", regexp = "^[A-Z][0-9]{7}[A-Z0-9]$")
    @NotBlank(message = "El CIF es obligatorio")
    String cif;
    @Pattern(message = "El teléfono debe tener 9 dígitos", regexp = "^[0-9]{9}$")
    @NotBlank(message = "El teléfono es obligatorio")
    String telefono;
    @Size(message = "El nombre debe tener entre 2 y 100 caracteres", min = 2, max = 100)
    @NotBlank(message = "El nombre de la empresa es obligatorio")
    String nombre;
    @Size(message = "La localidad debe tener entre 2 y 100 caracteres", min = 2, max = 100)
    @NotBlank(message = "La localidad es obligatoria")
    String localidad;
    @Size(message = "El domicilio no puede exceder 150 caracteres", max = 150)
    @NotBlank(message = "El domicilio es obligatorio")
    String domicilio;
    @Size(message = "El email no puede exceder 100 caracteres", max = 100)
    @Email(message = "El email debe tener un formato válido")
    @NotBlank(message = "El email es obligatorio")
    String email;
}