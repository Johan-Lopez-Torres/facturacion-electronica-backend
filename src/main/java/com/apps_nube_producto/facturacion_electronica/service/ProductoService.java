package com.apps_nube_producto.facturacion_electronica.service;

import com.apps_nube_producto.facturacion_electronica.Repository.ProductoRepository;
import com.apps_nube_producto.facturacion_electronica.dto.request.ProductoRequest;
import com.apps_nube_producto.facturacion_electronica.model.Producto;
import com.apps_nube_producto.facturacion_electronica.utils.mapper.ProductoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper ProductoMapper;

    public void crearProducto(ProductoRequest productoRequest) {
        Producto producto = ProductoMapper.productoRequestToProducto(productoRequest);
        productoRepository.save(producto);
    }

    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }
}