package com.apps_nube_producto.facturacion_electronica.exception;

public class DniNoEncontrado  extends RuntimeException{
    public DniNoEncontrado() {
        super("Dni no encontrado en la base de datos de la RENIEC");
    }
}
