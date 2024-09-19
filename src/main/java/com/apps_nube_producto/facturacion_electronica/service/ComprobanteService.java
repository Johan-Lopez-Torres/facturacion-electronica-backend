package com.apps_nube_producto.facturacion_electronica.service;


import com.apps_nube_producto.facturacion_electronica.Repository.ComprobanteRepository;
import com.apps_nube_producto.facturacion_electronica.Repository.ProductoRepository;
import com.apps_nube_producto.facturacion_electronica.dto.request.ComprobanteRequest;
import com.apps_nube_producto.facturacion_electronica.model.Comprobante;
import com.apps_nube_producto.facturacion_electronica.model.Producto;
import com.apps_nube_producto.facturacion_electronica.model.Usuario;
import com.apps_nube_producto.facturacion_electronica.model.relations.ComprobanteProducto;
import com.apps_nube_producto.facturacion_electronica.utils.AppConstants;
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
    private final UsuarioService usuarioService;


    public Comprobante crearComprobante(ComprobanteRequest comprobanteRequest) {

        Usuario usuario = usuarioService.crearUsuarioSiDocumentoValido(comprobanteRequest.getDniORuc());

        // Crear el comprobante sin productos inicialmente
        Comprobante comprobante = Comprobante.builder()
                .usuario(usuario)
                .igv(AppConstants.IGV)
                .tipoComprobante(comprobanteRequest.getTipoComprobante())
                .build();

         Comprobante comprobanteFinal = comprobanteRepository.save(comprobante); // Guardar el comprobante para obtener el comprobante_id

        List<ComprobanteProducto> comprobanteProductos = comprobanteRequest.getProductos().stream().map(id -> {
            Producto producto = productoRepository
                    .findById(id.getProductoId()).orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            return ComprobanteProducto.builder()
                    .producto(producto)
                    .comprobante(comprobanteFinal)  // Asignar el comprobante ahora que tiene ID
                    .cantidad(id.getCantidad())
                    .build();
        }).collect(Collectors.toList());


        comprobante.setComprobanteProductos(comprobanteProductos);

        BigDecimal subtotal = calcularSubtotal(comprobanteProductos);
        BigDecimal total = calcularTotal(subtotal);

        comprobante.setSubtotal(subtotal);
        comprobante.setTotal(total);

        // Guardar el comprobante con los productos relacionados
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
        return subtotal.add(subtotal.multiply(AppConstants.IGV));
    }


}
