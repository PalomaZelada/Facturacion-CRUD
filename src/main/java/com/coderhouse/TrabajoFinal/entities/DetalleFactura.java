package com.coderhouse.TrabajoFinal.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "DETALLE_FACTURA")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetalleFactura implements Serializable {
    private static final long serialversionUID = 1L;
    //@EmbeddedId
    //@JsonIgnore
    //DetalleFacturaKey detalleFacturaKey;
    @Column(name = "ID_DETALLE_FACTURA")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne
   // @MapsId("facturaId")
    @JoinColumn(name = "ID_FACTURA")
    Factura factura;

    //@JsonBackReference
    @ManyToOne
   //@MapsId("productoId")
    @JoinColumn(name = "ID_PRODUCTO")
    Producto producto;

    @Column(name = "cantidad_Productos")
    Long cantidadProductos;

    @Column(name = "total_Parcial")
    Long totalParcial;
}
