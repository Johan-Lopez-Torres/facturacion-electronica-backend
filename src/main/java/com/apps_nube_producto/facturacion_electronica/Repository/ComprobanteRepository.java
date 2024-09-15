package com.apps_nube_producto.facturacion_electronica.Repository;

import com.apps_nube_producto.facturacion_electronica.model.Comprobante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComprobanteRepository extends JpaRepository<Comprobante, Long> {

    List<Comprobante> findByUsuarioId(Long usuarioId);
}
