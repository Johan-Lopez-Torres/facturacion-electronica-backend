package com.apps_nube_producto.facturacion_electronica.dto.response;

import com.apps_nube_producto.facturacion_electronica.model.enums.TipoDocumento;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UsuarioResponse {

    private Long id;
    private TipoDocumento tipoDocumento;
    private String numero_dni;


}
