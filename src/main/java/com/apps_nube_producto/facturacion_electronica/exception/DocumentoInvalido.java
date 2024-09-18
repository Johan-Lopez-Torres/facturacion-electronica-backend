package com.apps_nube_producto.facturacion_electronica.exception;

public class DocumentoInvalido extends RuntimeException{
    public DocumentoInvalido() {
        super("DNI o RUC debe ser de 8 o 11 dígitos respectivamente");
    }
}
