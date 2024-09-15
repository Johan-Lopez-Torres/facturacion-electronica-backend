package com.apps_nube_producto.facturacion_electronica.model;


import com.apps_nube_producto.facturacion_electronica.model.enums.TipoComprobante;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss a")
    private LocalDateTime fecha;

    @Enumerated(EnumType.STRING)
    private TipoComprobante tipoComprobante;

    private BigDecimal total;
    private BigDecimal igv;

    private Integer cantidad;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    private Usuario usuario;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "comprobante")
    private List<ComprobanteProducto> comprobanteProductos;

}
