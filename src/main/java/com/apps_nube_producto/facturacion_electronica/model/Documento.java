package com.apps_nube_producto.facturacion_electronica.model;

import com.apps_nube_producto.facturacion_electronica.model.enums.TipoDocumento;
import jakarta.persistence.*;
import lombok.*;


import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Documento extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private TipoDocumento tipo;
    private String valor;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "tipoDocumento")
    private List<Usuario> usuarios;

}
