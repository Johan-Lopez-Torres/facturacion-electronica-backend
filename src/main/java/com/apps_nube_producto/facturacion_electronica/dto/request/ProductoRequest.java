package com.apps_nube_producto.facturacion_electronica.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ProductoRequest {

    private final static String PRECIO_POSITIVO = "El precio tiene que ser un número positivo";
    private final static String PRECIO_NECESARIO = "El precio del producto es necesario";
    private final static String NOMBRE_NECESARIO = "El nombre no puede estar vacío";


    @NotBlank(message = NOMBRE_NECESARIO)
    private String name;

    @NotBlank(message = PRECIO_POSITIVO)
    @Positive(message = PRECIO_NECESARIO)
    private BigDecimal precio;
}
