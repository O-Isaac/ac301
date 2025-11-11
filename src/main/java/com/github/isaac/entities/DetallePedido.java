package com.github.isaac.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "detalles_pedido")
@ToString
public class DetallePedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull(message = "El pedido es obligatorio")
    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    @ToString.Exclude
    private Pedido pedido;

    @NotNull(message = "El producto es obligatorio")
    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @NotNull(message = "El precio unitario es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio unitario debe ser mayor que 0")
    @Digits(integer = 8, fraction = 2, message = "El precio unitario debe tener máximo 8 dígitos enteros y 2 decimales")
    @Column(name = "precio_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioUnitario;

    @DecimalMin(value = "0.01", message = "El subtotal debe ser mayor que 0")
    @Digits(integer = 10, fraction = 2, message = "El subtotal debe tener máximo 10 dígitos enteros y 2 decimales")
    @Column(name = "subtotal", nullable = false, precision = 10, scale = 2)
    private BigDecimal subtotal;

    @PrePersist
    @PreUpdate
    private void calcularSubtotal() {
        if (precioUnitario != null && cantidad != null) {
            subtotal = precioUnitario.multiply(BigDecimal.valueOf(cantidad));
        }
    }

}