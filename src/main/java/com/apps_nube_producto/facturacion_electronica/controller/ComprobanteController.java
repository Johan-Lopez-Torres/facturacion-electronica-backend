package com.apps_nube_producto.facturacion_electronica.controller;

import com.apps_nube_producto.facturacion_electronica.dto.request.ComprobanteRequest;
import com.apps_nube_producto.facturacion_electronica.model.Comprobante;
import com.apps_nube_producto.facturacion_electronica.model.enums.TipoComprobante;
import com.apps_nube_producto.facturacion_electronica.service.ComprobanteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comprobantes")
@RequiredArgsConstructor
public class ComprobanteController {
    private final ComprobanteService comprobanteService;

    @PostMapping("/crear")
    public ResponseEntity<Comprobante> crearComprobante(
            @RequestBody ComprobanteRequest comprobanteRequest) {
        Comprobante comprobante = comprobanteService.crearComprobante(comprobanteRequest);
        return new ResponseEntity<>(comprobante, HttpStatus.CREATED);
    }



    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Comprobante>> obtenerComprobantesPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(comprobanteService.obtenerComprobantesPorUsuario(usuarioId));
    }
}
