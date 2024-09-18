package com.apps_nube_producto.facturacion_electronica.service;


import com.apps_nube_producto.facturacion_electronica.Repository.ComprobanteRepository;
import com.apps_nube_producto.facturacion_electronica.Repository.DocumentoRepository;
import com.apps_nube_producto.facturacion_electronica.Repository.ProductoRepository;
import com.apps_nube_producto.facturacion_electronica.Repository.UsuarioRepository;
import com.apps_nube_producto.facturacion_electronica.dto.request.ComprobanteRequest;
import com.apps_nube_producto.facturacion_electronica.model.Comprobante;
import com.apps_nube_producto.facturacion_electronica.model.Producto;
import com.apps_nube_producto.facturacion_electronica.model.Usuario;
import com.apps_nube_producto.facturacion_electronica.model.enums.TipoComprobante;
import com.apps_nube_producto.facturacion_electronica.model.relations.ComprobanteProducto;
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
    private final DocumentoRepository documentoRepository;

    private final UsuarioService usuarioService;
    private final static BigDecimal IGV = new BigDecimal("0.18");


    public Comprobante crearComprobante(ComprobanteRequest comprobanteRequest) {

        String dniORuc = comprobanteRequest.getDniORuc();
        Integer cantidad = comprobanteRequest.getCantidad();
        List<Long> productoIds = comprobanteRequest.getProductoIds();
        TipoComprobante tipoComprobante = comprobanteRequest.getTipoComprobante();


        Usuario usuario = usuarioService.crearUsuarioSiDocumentoValido(dniORuc);

        List<ComprobanteProducto> comprobanteProductos = productoIds.stream().map(id -> {
            Producto producto = productoRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            return ComprobanteProducto.builder()
                    .producto(producto)
                    .cantidad(cantidad)
                    .build();
        }).collect(Collectors.toList());


        BigDecimal subtotal = calcularSubtotal(comprobanteProductos);
        BigDecimal total = calcularTotal(subtotal);

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
        return subtotal;
    }

    private BigDecimal calcularTotal(BigDecimal subtotal) {
        return subtotal.add(subtotal.multiply(IGV));
    }


}
