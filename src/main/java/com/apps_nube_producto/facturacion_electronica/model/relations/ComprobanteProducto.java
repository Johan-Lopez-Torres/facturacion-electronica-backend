package com.apps_nube_producto.facturacion_electronica.model.relations;

import com.apps_nube_producto.facturacion_electronica.model.BaseEntity;
import com.apps_nube_producto.facturacion_electronica.model.Comprobante;
import com.apps_nube_producto.facturacion_electronica.model.Producto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComprobanteProducto extends BaseEntity {


    private Integer cantidad;


    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "comprobante_id")
    private Comprobante comprobante;


    @JsonIncludeProperties({"name"})
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "producto_id")
    private Producto producto;

}
