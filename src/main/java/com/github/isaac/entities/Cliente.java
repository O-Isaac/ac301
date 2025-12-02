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
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "El NIF/DNI es obligatorio")
    @Pattern(regexp = "^[0-9]{8}[A-Z]$", message = "El NIF debe tener formato 12345678A")
    @Column(name = "nif", nullable = false, length = 9, unique = true)
    private String nif;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "^[0-9]{9}$", message = "El teléfono debe tener 9 dígitos")
    @Column(name = "telefono", nullable = false, length = 15)
    private String telefono;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @NotBlank(message = "Los apellidos son obligatorios")
    @Size(min = 2, max = 100, message = "Los apellidos deben tener entre 2 y 100 caracteres")
    @Column(name = "apellidos", nullable = false, length = 100)
    private String apellidos;

    private String email;
    private String ciudad;

    @NotBlank(message = "La dirección es obligatoria")
    @Size(max = 150, message = "La dirección no puede exceder 150 caracteres")
    @Column(name = "direccion", nullable = false, length = 150)
    private String direccion;

    @NotBlank(message = "La dirección de envío es obligatoria")
    @Size(max = 150, message = "La dirección de envío no puede exceder 150 caracteres")
    @Column(name = "direccion_envio", nullable = false, length = 150)
    private String direccionEnvio;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private Set<Pedido> pedidos = new HashSet<>();

    @PreRemove
    public void preRemove() {
        for (Pedido pedido : pedidos) {
            pedido.setCliente(null);
        }
    }
}