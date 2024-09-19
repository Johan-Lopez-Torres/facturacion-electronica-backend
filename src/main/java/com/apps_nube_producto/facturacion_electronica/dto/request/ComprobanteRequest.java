package com.apps_nube_producto.facturacion_electronica.dto.request;

import com.apps_nube_producto.facturacion_electronica.model.enums.TipoComprobante;
import com.apps_nube_producto.facturacion_electronica.utils.AppConstants;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ComprobanteRequest {


    private TipoComprobante tipoComprobante;

    @NotBlank(message = AppConstants.CANTIDAD_NECESARIA)
    @Positive(message = AppConstants.TOTAL_POSITIVO)
    @Min(value = 1, message = AppConstants.CANTIDAD_MINIMA)
    private Integer cantidad;

    @Pattern(regexp = "^\\d{8}|\\d{11}$",  message = AppConstants.DOCUMENTO_INVALIDO)
    private String dniORuc;

    private List<ProductoCantidadRequest> productos;


}