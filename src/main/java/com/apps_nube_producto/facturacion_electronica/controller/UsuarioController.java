package com.apps_nube_producto.facturacion_electronica.controller;

import com.apps_nube_producto.facturacion_electronica.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/crear/{numDoc}")
    public ResponseEntity<?> crearUsuarioSiDniValido(@PathVariable String numDoc) {
        return ResponseEntity.ok(usuarioService.crearUsuarioSiDocumentoValido(numDoc));
    }


}
