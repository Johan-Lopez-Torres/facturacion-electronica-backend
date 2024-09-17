package com.apps_nube_producto.facturacion_electronica.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DniResponse {
    private String dni;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
}
