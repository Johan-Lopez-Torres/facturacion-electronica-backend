package com.apps_nube_producto.facturacion_electronica.dto.request;

import com.apps_nube_producto.facturacion_electronica.model.enums.TipoComprobante;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ComprobanteRequest {

    private final static String TOTAL_POSITIVO = "El total no puede ser negativo";
    private final static String CANTIDAD_NECESARIA = "La cantidad de productos no puede ser nula o vacía";
    private final static String CANTIDAD_MINIMA = "La cantidad de productos no puede ser menor a 1";
    private final static String DNI_RUC = "El DNI debe tener 8 dígitos o el RUC debe tener 11 dígitos";


    private TipoComprobante tipoComprobante;

    @Positive(message = TOTAL_POSITIVO)
    @NotBlank(message = CANTIDAD_NECESARIA)
    @Min(value = 1, message = CANTIDAD_MINIMA)
    private Integer cantidad;

    @Pattern(regexp = "^\\d{8}|\\d{11}$", message = DNI_RUC)
    private String dniORuc;

}