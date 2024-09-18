package com.apps_nube_producto.facturacion_electronica.exception;

public class DocumentoInvalido extends RuntimeException{
    public DocumentoInvalido() {
        super("El DNI o RUC no existe en la Base de datos");
    }
}
