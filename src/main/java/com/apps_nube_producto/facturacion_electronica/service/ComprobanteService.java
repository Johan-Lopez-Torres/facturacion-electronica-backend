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
    private final static BigDecimal IGV = new BigDecimal("0.18");


    public Comprobante crearComprobante(String dni,
                                        List<Long> productoIds,
                                        Integer cantidad,
                                        TipoComprobante tipoComprobante) {

        if (usuarioRepository.findByTipoDocumento_Valor(dni) == null) {
            throw new RuntimeException("Usuario no encontrado");
        }
//        if(tipoComprobante.equals(TipoComprobante.FACTURA)){
//            if(usuarioRepository.findByTipoDocumento_Valor(dni).get().getTipoDocumento().getDescripcion().equals("DNI")){
//                throw new RuntimeException("No se puede emitir factura con DNI");
//            }
//        }


        Usuario usuario = usuarioRepository.findByTipoDocumento_Valor(dni)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<ComprobanteProducto> comprobanteProductos = productoIds.stream().map(id -> {
            Producto producto = productoRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            return ComprobanteProducto.builder()
                    .producto(producto)
                    .cantidad(cantidad)
                    .build();
        }).collect(Collectors.toList());

        BigDecimal total = calcularSubtotal(comprobanteProductos);


        Comprobante comprobante = Comprobante.builder()
                .usuario(usuario)
                .comprobanteProductos(comprobanteProductos)
                .total(total)
                .igv(IGV)
                .tipoComprobante(tipoComprobante)
                .build();

        return comprobanteRepository.save(comprobante);
    }

    public List<Comprobante> obtenerComprobantesPorUsuario(Long usuarioId) {
        return comprobanteRepository.findByUsuarioId(usuarioId);
    }


    public BigDecimal calcularSubtotal(List<ComprobanteProducto> comprobanteProductos) {
        BigDecimal subtotal = comprobanteProductos.stream()
                .map(cp -> cp.getProducto().getPrecio().multiply(BigDecimal.valueOf(cp.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal igv = subtotal.multiply(IGV);
        subtotal.add(igv);
        return subtotal;
    }

}
