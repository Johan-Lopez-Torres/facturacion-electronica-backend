package com.apps_nube_producto.facturacion_electronica.service;


import com.apps_nube_producto.facturacion_electronica.Repository.ComprobanteRepository;
import com.apps_nube_producto.facturacion_electronica.Repository.ProductoRepository;
import com.apps_nube_producto.facturacion_electronica.Repository.UsuarioRepository;
import com.apps_nube_producto.facturacion_electronica.model.*;
import com.apps_nube_producto.facturacion_electronica.model.enums.TipoComprobante;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComprobanteService {

    private final ComprobanteRepository comprobanteRepository;
    private final ProductoRepository productoRepository;
    private final UsuarioRepository usuarioRepository;

    public Comprobante crearComprobante(Long usuarioId,
                                        List<Long> productoIds,
                                        Integer cantidad,
                                        TipoComprobante tipoComprobante) {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<ComprobanteProducto> comprobanteProductos = productoIds.stream().map(id -> {
            Producto producto = productoRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            return ComprobanteProducto.builder()
                    .producto(producto)
                    .cantidad(cantidad)
                    .build();
        }).collect(Collectors.toList());

        BigDecimal subtotal = comprobanteProductos.stream()
                .map(cp -> cp.getProducto().getPrecio().multiply(BigDecimal.valueOf(cp.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal igv = subtotal.multiply(new BigDecimal("0.18"));
        BigDecimal total = subtotal.add(igv);


        Comprobante comprobante = Comprobante.builder()
                .usuario(usuario)
                .comprobanteProductos(comprobanteProductos)
                .total(total)
                .igv(igv)
                .tipoComprobante(tipoComprobante)
                .build();

        return comprobanteRepository.save(comprobante);
    }

    public List<Comprobante> obtenerComprobantesPorUsuario(Long usuarioId) {
        return comprobanteRepository.findByUsuarioId(usuarioId);
    }

}
