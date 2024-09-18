package com.apps_nube_producto.facturacion_electronica.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DniResponse {
    private Long id;
    private String success;
    private String dni;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
}
