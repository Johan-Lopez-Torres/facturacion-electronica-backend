package com.apps_nube_producto.facturacion_electronica.model;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Producto extends BaseEntity {


    private String name;
    private BigDecimal precio;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "producto")
    private List<ComprobanteProducto> comprobanteProductos;
}
