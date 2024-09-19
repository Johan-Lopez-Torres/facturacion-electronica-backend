package com.apps_nube_producto.facturacion_electronica.model;
import com.apps_nube_producto.facturacion_electronica.model.relations.ComprobanteProducto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "producto")
    private List<ComprobanteProducto> comprobanteProductos;
}
