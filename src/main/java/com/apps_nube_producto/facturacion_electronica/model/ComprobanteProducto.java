package com.apps_nube_producto.facturacion_electronica.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComprobanteProducto extends BaseEntity{


    private Integer cantidad;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "comprobante_id")
    private Comprobante comprobante;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "producto_id")
    private Producto producto;

}
