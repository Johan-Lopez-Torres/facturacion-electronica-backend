package com.apps_nube_producto.facturacion_electronica.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoDocumento extends BaseEntity {

    private String tipo;
    private String valor;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "tipoDocumento")
    private List<Usuario> usuarios;

}
