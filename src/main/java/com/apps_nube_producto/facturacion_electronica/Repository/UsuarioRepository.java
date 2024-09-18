package com.apps_nube_producto.facturacion_electronica.Repository;

import com.apps_nube_producto.facturacion_electronica.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

     Usuario findByTipoDocumento_Valor(String dni);

}
