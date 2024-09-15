package com.apps_nube_producto.facturacion_electronica.service;

import com.apps_nube_producto.facturacion_electronica.Repository.ProductoRepository;
import com.apps_nube_producto.facturacion_electronica.model.Producto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService {
    private final ProductoRepository productoRepository;

    public Producto crearProducto(String name, BigDecimal precio) {
        Producto producto = Producto.builder()
                .name(name)
                .precio(precio)
                .build();
        return productoRepository.save(producto);
    }

    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }
}