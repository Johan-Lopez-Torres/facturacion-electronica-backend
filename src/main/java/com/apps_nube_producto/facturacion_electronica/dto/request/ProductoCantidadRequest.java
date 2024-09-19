package com.apps_nube_producto.facturacion_electronica.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductoCantidadRequest {

    @NotNull(message = "El ID del producto es necesario")
    private Long productoId;

    @NotNull(message = "La cantidad es necesaria")
    @Positive(message = "La cantidad debe ser un número positivo")
    private Integer cantidad;
}
