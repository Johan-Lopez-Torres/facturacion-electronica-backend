package com.apps_nube_producto.facturacion_electronica.controller;

import com.apps_nube_producto.facturacion_electronica.dto.request.ProductoRequest;
import com.apps_nube_producto.facturacion_electronica.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/producto")
@RestController
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;


    @PostMapping("/crear")
    private ResponseEntity<?> crearProducto(@RequestBody ProductoRequest productoRequest) {
        productoService.crearProducto(productoRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/obtener")
    private ResponseEntity<?> obtenerTodosLosProductos() {
        return ResponseEntity.ok(productoService.obtenerTodosLosProductos());
    }


}
