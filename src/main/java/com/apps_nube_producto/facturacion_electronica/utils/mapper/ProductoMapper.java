package com.apps_nube_producto.facturacion_electronica.utils.mapper;

import com.apps_nube_producto.facturacion_electronica.dto.request.ProductoRequest;
import com.apps_nube_producto.facturacion_electronica.model.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    public  Producto productoRequestToProducto(ProductoRequest productoRequest) {
        return Producto.builder()
                .name(productoRequest.getName())
                .precio(productoRequest.getPrecio())
                .build();
    }

}
