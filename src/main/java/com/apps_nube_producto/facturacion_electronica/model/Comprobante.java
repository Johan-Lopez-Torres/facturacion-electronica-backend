package com.apps_nube_producto.facturacion_electronica.model;


import com.apps_nube_producto.facturacion_electronica.model.enums.TipoComprobante;
import com.apps_nube_producto.facturacion_electronica.model.relations.ComprobanteProducto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comprobante extends BaseEntity {


    @Enumerated(EnumType.STRING)
    private TipoComprobante tipoComprobante;

    private BigDecimal subtotal;
    private BigDecimal total;
    private BigDecimal igv;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "comprobante")
    private List<ComprobanteProducto> comprobanteProductos;

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss a")
    private LocalDateTime fecha;

}
