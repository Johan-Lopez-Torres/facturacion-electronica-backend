package com.apps_nube_producto.facturacion_electronica.Repository;

import com.apps_nube_producto.facturacion_electronica.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
