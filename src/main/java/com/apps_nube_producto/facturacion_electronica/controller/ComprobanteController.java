package com.apps_nube_producto.facturacion_electronica.controller;

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

    @PostMapping
    public ResponseEntity<Comprobante> crearComprobante(
            @RequestParam String dni,
            @RequestParam List<Long> productoIds,
            @RequestParam Integer cantidad,
            @RequestParam TipoComprobante tipoComprobante
    ) {
        Comprobante comprobante = comprobanteService.crearComprobante(dni, productoIds, cantidad, tipoComprobante);
        return new ResponseEntity<>(comprobante, HttpStatus.CREATED);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Comprobante>> obtenerComprobantesPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(comprobanteService.obtenerComprobantesPorUsuario(usuarioId));
    }
}
