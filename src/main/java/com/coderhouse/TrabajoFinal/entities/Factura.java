package com.coderhouse.TrabajoFinal.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "FACTURA")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Factura implements Serializable {
    private static final long serialversionUID = 1L;

    @Column(name = "ID_FACTURA")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "fecha_Emision")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "tipo_Factura")
    @Pattern(regexp="^[a-zA-Z]{1}",message = "El campo tipo Factura debe ser una letra")
    private String tipoFactura;

    @Column(name = "total_Factura")
    @Digits(integer=20, fraction=0, message = "Total factura debe ser un numero entero")
    private Long totalFactura;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_Cliente")
    private Cliente cliente;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_Empresa")
    private Empresa empresa;

    @OneToMany(mappedBy = "factura", fetch = FetchType.LAZY)
    private List<DetalleFactura> detalleFacturas;


}
