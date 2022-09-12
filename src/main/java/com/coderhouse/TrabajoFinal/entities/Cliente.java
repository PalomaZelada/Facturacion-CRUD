package com.coderhouse.TrabajoFinal.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "CLIENTE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Column(name = "id_Cliente")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    @Pattern(regexp="^[a-zA-Z]{1,254}",message = "El campo nombre debe ser una palabra")
    private String nombre;

    @Column(name = "apellido")
    @Pattern(regexp="^[a-zA-Z]{1,254}",message = "El campo apellido debe ser una palabra")
    private String apellido;

    @Column(name = "dni")
    @Min(value = 10000000, message ="El dni debe tener un largo minimo de 8")
    @Max(value = 99999999, message = "El dni debe tener un largo maximo de 8")
    private Long dni;

    @Column(name = "direccion")
    @Pattern(regexp="^[a-zA-Z0-9\\s]{1,254}",message = "El campo dirección debe ser un texto alfanumerico")
    private String direccion;

    @Column(name = "telefono")
    @Min(value = 100000000, message ="Telefono debe tener un largo minimo de 9")
    @Max(value = 999999999, message = "Telefono debe tener un largo maximo de 9")
    private Long telefono;
}
