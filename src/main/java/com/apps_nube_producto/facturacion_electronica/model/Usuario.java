package com.apps_nube_producto.facturacion_electronica.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario extends BaseEntity {


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    private TipoDocumento tipoDocumento;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<Comprobante> comprobantes;
}
