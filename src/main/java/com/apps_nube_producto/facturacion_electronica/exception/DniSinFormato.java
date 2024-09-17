package com.apps_nube_producto.facturacion_electronica.exception;

public class DniSinFormato extends RuntimeException{
    public DniSinFormato() {
        super("El DNI debe tener 8 dígitos");
    }
}
