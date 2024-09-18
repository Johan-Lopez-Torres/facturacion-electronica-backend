package com.apps_nube_producto.facturacion_electronica.exception;


import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalHandlerException {

    @ExceptionHandler(DniSinFormato.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleDniSinFormato(DniSinFormato ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(DniNoEncontrado.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleDniNoEncontrado(DniNoEncontrado ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(DocumentoInvalido.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleDocumentoInvalido(DocumentoInvalido ex) {
        return ex.getMessage();
    }


}
