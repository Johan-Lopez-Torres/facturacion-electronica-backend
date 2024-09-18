package com.apps_nube_producto.facturacion_electronica.dto.request;

import com.apps_nube_producto.facturacion_electronica.utils.AppConstants;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ProductoRequest {

    @NotBlank(message = AppConstants.NOMBRE_NECESARIO)
    @Size(min = 3, max = 25, message = AppConstants.NOMBRE_TAMAÑO)
    private String name;

    @NotBlank(message = AppConstants.PRECIO_POSITIVO)
    @Positive(message = AppConstants.PRECIO_NECESARIO)
    private BigDecimal precio;
}
