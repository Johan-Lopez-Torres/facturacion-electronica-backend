package com.apps_nube_producto.facturacion_electronica.dto.request;

import com.apps_nube_producto.facturacion_electronica.model.enums.TipoComprobante;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ComprobanteRequest {

    private final static String mensajePositivo = "El total no puede ser negativo";
    private final static String mensajeCantidad = "La cantidad de productos no puede ser nula o vacía";


    private TipoComprobante tipoComprobante;

    @Positive(message = mensajePositivo)
    @NotBlank(message = mensajeCantidad)
    private Integer cantidad;

}