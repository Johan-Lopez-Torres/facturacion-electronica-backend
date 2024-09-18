package com.apps_nube_producto.facturacion_electronica.Repository;

import com.apps_nube_producto.facturacion_electronica.model.Documento;
import com.apps_nube_producto.facturacion_electronica.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {
    boolean existsDocumentoByValor(String dniORuc);
    Usuario findDocumentoByValor(String valorDniORuc);
    Optional<Documento> findByValor(String valor);
}
