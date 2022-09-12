package com.coderhouse.TrabajoFinal.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "PRODUCTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto implements Serializable {
    private static final long serialversionUID = 1L;
    @Column(name = "ID_PRODUCTO")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "stock")
    @Pattern(regexp="^[0-9]{1,254}", message = "El campo stock debe ser un numero")
    private String stock;

    @Column(name = "nombre")
    @Pattern(regexp="^[a-zA-Z\\s]{1,254}",message = "El campo nombre debe ser una palabra")
    private String nombre;

    @Column(name = "marca")
    @Pattern(regexp="^[a-zA-Z\\s]{1,254}",message = "El campo marca debe ser una palabra")
    @JsonIgnore
    private String marca;

    @Column(name = "detalle")
    @Pattern(regexp="^[a-zA-Z\\s]{1,254}",message = "El campo detalle debe ser un texto")
    private String detalle;

    @Column(name = "precio_Unitario")
    @Digits(integer=6, fraction=0, message = "Precio unitario de maximo 6 digitos")
    private Long precioUnitario;

    @JsonBackReference
    @OneToMany(mappedBy = "producto", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<DetalleFactura> detalleFacturas;

}