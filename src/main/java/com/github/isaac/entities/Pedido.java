package com.github.isaac.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jakarta.validation.Valid;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull(message = "El cliente es obligatorio")
    @Valid
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @NotNull(message = "La empresa es obligatoria")
    @Valid
    @ManyToOne
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @NotNull(message = "El total es obligatorio")
    @DecimalMin(value = "0.00", message = "El total no puede ser negativo")
    @Digits(integer = 10, fraction = 2, message = "El total debe tener máximo 10 dígitos enteros y 2 decimales")
    @Column(name = "total", nullable = false, precision = 10, scale = 2)
    private BigDecimal total = BigDecimal.ZERO;

    @Valid
    @NotEmpty(message = "Un pedido debe tener al menos un detalle")
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<DetallePedido> detalles = new ArrayList<>();

    // Estos metodos permite crear la relacion bidireccional
    public void addDetalle(DetallePedido detalle) {
        if (detalle == null) return;

        detalles.add(detalle);
        detalle.setPedido(this);
        recalcularTotal();
    }

    public void removeDetalle(DetallePedido detalle) {
        if (detalle == null) return;

        detalles.remove(detalle);
        detalle.setPedido(null);
        recalcularTotal();
    }

    // Permite recalcular el total del pedido
    private void recalcularTotal() {
        total = detalles.stream()
                .map(DetallePedido::getSubtotal)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @PrePersist
    @PreUpdate
    private void beforeSave() {
        recalcularTotal();
    }

}