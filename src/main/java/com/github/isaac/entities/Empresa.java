package com.github.isaac.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "empresas")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "El CIF es obligatorio")
    @Pattern(regexp = "^[A-Z][0-9]{7}[A-Z0-9]$", message = "El CIF debe tener formato A12345678 o A1234567B")
    @Column(name = "cif", nullable = false, length = 9, unique = true)
    private String cif;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "^[0-9]{9}$", message = "El teléfono debe tener 9 dígitos")
    @Column(name = "telefono", nullable = false, length = 15)
    private String telefono;

    @NotBlank(message = "El nombre de la empresa es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @NotBlank(message = "La localidad es obligatoria")
    @Size(min = 2, max = 100, message = "La localidad debe tener entre 2 y 100 caracteres")
    @Column(name = "localidad", nullable = false, length = 100)
    private String localidad;

    @NotBlank(message = "El domicilio es obligatorio")
    @Size(max = 150, message = "El domicilio no puede exceder 150 caracteres")
    @Column(name = "domicilio", nullable = false, length = 150)
    private String domicilio;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe tener un formato válido")
    @Size(max = 100, message = "El email no puede exceder 100 caracteres")
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<Pedido> pedidos = new HashSet<>();

}